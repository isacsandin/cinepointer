package br.com.cinepointer.ui;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import br.com.cinepointer.R;
import br.com.cinepointer.maps.MyOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;


public class MapVisualizador extends MapActivity {

	MapView mapView;
	MyLocationOverlay mlo;
	GeoPoint GeoP;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mapaview);
		mapView = (MapView) findViewById(R.id.mapaView) ;		
		mapView.setClickable(true) ;
		mapView.displayZoomControls(true);
		mapView.setBuiltInZoomControls(true);

		GeoPoint gp1 = getGeopoint("sjdr - mg");
		GeoPoint gp2 = getGeopoint("lavras -mg");
		if (gp1 != null && gp2 !=null){
			GeoPoint o = ondeEstou();
			if(o == null){
				Dialogs.imprimirMensagem(this,"ERRO","nao foi possivel te localizar");
				this.goTo(gp2);
			}
			else{
				this.goTo(o);
			}
			this.DrawPath(gp1, gp2,Color.RED, mapView);
			Dialogs.imprimirMensagem(this,"LEGAL",distancia(gp1, gp2).toString());
		}
		else{
			Dialogs.imprimirMensagem(this,"ERRO","locais inválidos");
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void goTo(GeoPoint gp){
		if(gp != null){
			MapController mc = mapView.getController();
			mc.animateTo(gp);    
			mapView.invalidate();
		}
		else{
			Dialogs.imprimirMensagem(this,"ERRO","Local não encontrado!");

		}
	}

	public GeoPoint getGeopoint(String local){
		Geocoder geoCoder = new Geocoder(this, Locale.getDefault());    
		try {
			List<Address> addresses = geoCoder.getFromLocationName(local, 5);
			if (addresses.size() > 0) {
				GeoPoint geoPoint = new GeoPoint(
						(int) (addresses.get(0).getLatitude() * 1E6), 
						(int) (addresses.get(0).getLongitude() * 1E6));
				return geoPoint;
			}
			else{
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	private void DrawPath(GeoPoint src,GeoPoint dest, int color, MapView mapView)
	{
		// connect to map web service
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.google.com/maps?f=d&hl=en");
		urlString.append("&saddr=");//from
		urlString.append( Double.toString((double)src.getLatitudeE6()/1.0E6 ));
		urlString.append(",");
		urlString.append( Double.toString((double)src.getLongitudeE6()/1.0E6 ));
		urlString.append("&daddr=");//to
		urlString.append( Double.toString((double)dest.getLatitudeE6()/1.0E6 ));
		urlString.append(",");
		urlString.append( Double.toString((double)dest.getLongitudeE6()/1.0E6 ));
		urlString.append("&ie=UTF8&0&om=0&output=kml");
		Log.d("xxx","URL="+urlString.toString());
		// get the kml (XML) doc. And parse it to get the coordinates(direction route).
		Document doc = null;
		HttpURLConnection urlConnection= null;
		URL url = null;
		try
		{
			url = new URL(urlString.toString());
			urlConnection=(HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.connect();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(urlConnection.getInputStream());

			if(doc.getElementsByTagName("GeometryCollection").getLength()>0)
			{
				//String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getNodeName();
				String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue() ;
				Log.d("xxx","path="+ path);
				String [] pairs = path.split(" ");
				String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude lngLat[1]=latitude lngLat[2]=height
				// src
				GeoPoint startGP = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
				mapView.getOverlays().add(new MyOverlay(startGP,startGP,1));
				GeoPoint gp1;
				GeoPoint gp2 = startGP;
				for(int i=1;i<pairs.length;i++) // the last one would be crash
				{
					lngLat = pairs[i].split(",");
					gp1 = gp2;
					// watch out! For GeoPoint, first:latitude, second:longitude
					gp2 = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
					mapView.getOverlays().add(new MyOverlay(gp1,gp2,2,color));
					Log.d("xxx","pair:" + pairs[i]);
				}
				mapView.getOverlays().add(new MyOverlay(dest,dest, 3)); // use the default color
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
	}

	public static Double distancia(GeoPoint gp1,GeoPoint gp2) {
		double lat1 = gp1.getLatitudeE6();
		double lat2 = gp2.getLatitudeE6();
		double lon1 = gp1.getLatitudeE6();
		double lon2 = gp2.getLongitudeE6();
		double raio = 3958.75;
		double dlat = ToRadians(lat2 - lat1);
		double dlon = ToRadians(lon2 - lon1);

		double a = Math.sin(dlat / 2) * Math.sin(dlat / 2)
		+ Math.cos(ToRadians(lat1)) * Math.cos(ToRadians(lat2))
		* Math.sin(dlon / 2) * Math.sin(dlon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		Double d = raio * c;

		double meterConversion = 1609.00;
		return d * meterConversion;
	}

	private static double ToRadians(double degrees) {
		double radians = degrees * 3.1415926535897932385 / 180;
		return radians;
	}

	//Verifica se (lat1, lon1) está dentro do (lat2, lon2)+raio

	public static boolean verificarProximidade(GeoPoint gp1,GeoPoint gp2, int raio) {
		if (distancia(gp1,gp2) > raio) {
			return false;
		}

		//Se estiver dentro do raio*/
		return true;
	}

	public GeoPoint ondeEstou(){
		// Obtem uma instancia do servico gerenciador de localizacao
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (locationManager != null){
			// Define como provedor o GPS do aparelho
			String provider = LocationManager.GPS_PROVIDER;
			// Obtem a localizacao (imediatamente)
			Location location = locationManager.getLastKnownLocation(provider);
			if(location != null){
				return new GeoPoint((int)(location.getLatitude() * 1E6),(int)(location.getLongitude() * 1E6));
			}
			else return null;
		}
		else return null;

	}


	//	@Override
	//	public void onActivityResult(int requestCode, int resultCode,
	//			Intent data) {
	//		
	//	}
}


