package br.com.cinepoiter;

import java.util.ArrayList;
import java.util.List;

import android.R.array;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class CinePointerActivity extends Activity {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial);
	
        
        String [] array_spinner =new String[5];
        array_spinner[0]="Cidade 1";
        array_spinner[1]="Cidade 2";
        array_spinner[2]="Cidade 3";
        array_spinner[3]="Cidade 4";
        array_spinner[4]="Cidade 5";
        
        Spinner s = (Spinner) findViewById(R.id.spinner1);
      
        setSpinnerOptions(array_spinner,s);
 
		array_spinner =new String[5];
        array_spinner[0]="Gênero 1";
        array_spinner[1]="Gênero 2";
        array_spinner[2]="Gênero 3";
        array_spinner[3]="Gênero 4";
        array_spinner[4]="Gênero 5";
        
        s = (Spinner) findViewById(R.id.spinner2);
        
        setSpinnerOptions(array_spinner,s);

		array_spinner =new String[3];
        array_spinner[0]="Manhã";
        array_spinner[1]="Tarde";
        array_spinner[2]="Noite";
        
        s = (Spinner) findViewById(R.id.spinner3);
        
        setSpinnerOptions(array_spinner,s);
       

 
    }
    
	private void setSpinnerOptions(String [] array_spinner, Spinner s) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
	}
}