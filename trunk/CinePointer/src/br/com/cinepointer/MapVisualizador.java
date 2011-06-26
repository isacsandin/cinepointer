package br.com.cinepointer;

import android.content.Intent;
import android.os.Bundle;
import br.com.cinepointer.R;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;


public class MapVisualizador extends MapActivity {

	MapView mapView;
	MyLocationOverlay mlo;
	GeoPoint GeoP;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		if (mapView == null) {
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
		}
		
		//		Button buttonLogoff = (Button) findViewById(R.id.buttonLogoff);
		//		buttonLogoff.setOnClickListener(new View.OnClickListener(){
		//			public void onClick(View arg0) {
		//				finish();
		//			}
		//		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	//	@Override
	//	public void onActivityResult(int requestCode, int resultCode,
	//			Intent data) {
	//		
	//	}
}


