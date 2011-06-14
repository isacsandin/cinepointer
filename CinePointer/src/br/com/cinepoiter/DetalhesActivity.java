	package br.com.cinepoiter;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
	
public class DetalhesActivity extends Activity{
		AlertDialog.Builder dialogo; 
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        dialogo = new AlertDialog.Builder(this);
	        setContentView(R.layout.detailed_information);
	    }
	        public void imprimirMensagem(String title, String msg){
			dialogo.setTitle(title);
			dialogo.setMessage(msg);
			dialogo.setNeutralButton("OK", null);
			dialogo.show();
			}
		
}
