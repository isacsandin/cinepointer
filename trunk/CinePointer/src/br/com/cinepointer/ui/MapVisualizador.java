package br.com.cinepointer.ui;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Window;
import br.com.cinepointer.R;

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
		mapView.getController().setZoom(18);
		mlo = new MyLocationOverlay(this, mapView) ;
		mlo.enableCompass();
		mlo.enableMyLocation();
		mapView.getOverlays().add(mlo);
		this.encontrarLocal("são paulo - sp");
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public void encontrarLocal(String local){
		Geocoder geoCoder = new Geocoder(this, Locale.getDefault());    
	    MapController mc = mapView.getController();
		try {
	         List<Address> addresses = geoCoder.getFromLocationName(local, 5);
	            if (addresses.size() > 0) {
	                GeoPoint geoPoint = new GeoPoint(
	                        (int) (addresses.get(0).getLatitude() * 1E6), 
	                        (int) (addresses.get(0).getLongitude() * 1E6));
	                mc.animateTo(geoPoint);    
	                mapView.invalidate();
	            }
	            else{
	            	Dialogs.imprimirMensagem(this,"ERRO","Local não encontrado!");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	
	//	@Override
	//	public void onActivityResult(int requestCode, int resultCode,
	//			Intent data) {
	//		
	//	}
}


