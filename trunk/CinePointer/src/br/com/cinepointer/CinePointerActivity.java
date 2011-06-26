package br.com.cinepointer;

import br.com.cinepointer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CinePointerActivity extends Activity {
    private static final int ADD_NEW = 0;
	private static final int REMOVE = 1;
	/** Called when the activity is first created. */
	
	AlertDialog.Builder dialogo; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogo = new AlertDialog.Builder(CinePointerActivity.this);
        setContentView(R.layout.inicial);
	
        
        String [] array_spinner =new String[5];
        array_spinner[0]="Todas";
        array_spinner[1]="Cidade 1";
        array_spinner[2]="Cidade 2";
        array_spinner[3]="Cidade 3";
        array_spinner[4]="Cidade 4";
        
        Spinner s = (Spinner) findViewById(R.id.spinner1);
      
        setSpinnerOptions(array_spinner,s);
 
		array_spinner =new String[5];
		array_spinner[0]="Todos";
        array_spinner[1]="Gênero 1";
        array_spinner[2]="Gênero 2";
        array_spinner[3]="Gênero 3";
        array_spinner[4]="Gênero 4";
        
        s = (Spinner) findViewById(R.id.spinner2);
        
        setSpinnerOptions(array_spinner,s);

		array_spinner =new String[4];
		array_spinner[0]="Todos";
        array_spinner[1]="Manhã";
        array_spinner[2]="Tarde";
        array_spinner[3]="Noite";
        
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
	
	public void imprimirMensagem(String title, String msg){
		dialogo.setTitle(title);
		dialogo.setMessage(msg);
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
		}
	
	
}