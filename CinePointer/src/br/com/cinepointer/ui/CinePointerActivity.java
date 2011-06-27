package br.com.cinepointer.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import br.com.cinepointer.R;

public class CinePointerActivity extends Activity {

		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.inicial);
	
        
        String [] array_spinner = getResources().getStringArray(R.array.arrayCidades);
        
        Spinner s = (Spinner) findViewById(R.id.spinner1);
      
        setSpinnerOptions(array_spinner,s);
 
		array_spinner = getResources().getStringArray(R.array.arrayGeneros);
        
        s = (Spinner) findViewById(R.id.spinner2);
        
        setSpinnerOptions(array_spinner,s);

        array_spinner = getResources().getStringArray(R.array.arrayHorarios);
        
        s = (Spinner) findViewById(R.id.spinner3);
        
        setSpinnerOptions(array_spinner,s);
        
        Button b = (Button) findViewById(R.id.botaoPesquisar);
                
        b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(CinePointerActivity.this,ListFilmView.class);
				startActivity(i);
			}
		});
    }
    
	private void setSpinnerOptions(String [] array_spinner, Spinner s) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
	}
	

	
	
}