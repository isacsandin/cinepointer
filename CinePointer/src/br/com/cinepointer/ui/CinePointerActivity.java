package br.com.cinepointer.ui;

import java.io.IOException;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import br.com.cinepointer.R;
import br.com.cinepointer.database.CalendarData;
import br.com.cinepointer.datatypes.Variables;
import br.com.cinepointer.htmlparser.HtmlParser;

public class CinePointerActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.inicial);

		Spinner s = (Spinner) findViewById(R.id.spinner1);
		String [] array_spinner = Variables.getInstance().getAllStates();
		setSpinnerOptions(array_spinner,s);
		
		s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		    	String estado = (String) parent.getItemAtPosition(pos);
				String [] array_spinner = Variables.getInstance().getCitiesFrom(Variables.getInstance().getStateCode(estado));
				Spinner spi = (Spinner) findViewById(R.id.spinner2);
				if(spi !=null){
					setSpinnerOptions(array_spinner,spi);
					spi.setEnabled(true);
					}
				else{
				   Dialogs.imprimirMensagem(parent.getContext(),"ERRO","spinner e nulo");
				}	
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});


		
//		String estado = (String) parent.getItemAtPosition(pos);
//        Dialogs.imprimirMensagem(getApplicationContext(),"debug",estado);

		
		s = (Spinner) findViewById(R.id.spinner2);
		s.setEnabled(false);
		
		s = (Spinner) findViewById(R.id.spinner3);
		array_spinner = Variables.getInstance().getAllGenres();
		setSpinnerOptions(array_spinner,s);


//		CalendarData.addToCalendar(this,"Compromisso1",
//		new GregorianCalendar(2011,6,30,17,30).getTimeInMillis(),
//		new GregorianCalendar(2011,6,30,19,30).getTimeInMillis()
//		);
		
		try {
			HtmlParser.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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