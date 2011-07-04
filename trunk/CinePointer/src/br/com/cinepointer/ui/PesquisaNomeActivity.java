package br.com.cinepointer.ui;

import java.io.IOException;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import br.com.cinepointer.R;
import br.com.cinepointer.database.CalendarData;
import br.com.cinepointer.datatypes.Variables;
import br.com.cinepointer.htmlparser.HtmlParser;

public class PesquisaNomeActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pesquisa_nome);
		
		
		Spinner s = (Spinner) findViewById(R.id.pesquisaNomeSpinner);
		String [] array_spinner = Variables.getInstance().getAllGenres();
		setSpinnerOptions(array_spinner,s);
		
		s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

		    	Spinner spi = (Spinner) findViewById(R.id.pesquisaNomeSpinner);
		    	String estado = (String) parent.getItemAtPosition(pos);
				String [] array_spinner = Variables.getInstance().getCitiesFrom(Variables.getInstance().getStateCode(estado));
		    	
		         ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,array_spinner);
		         AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.pesquisaNomeTextView);
		         textView.setAdapter(adapter);

				
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

		
		Button b = (Button) findViewById(R.id.pesquisaNomeBotao);

		
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(PesquisaNomeActivity.this,ListFilmView.class);
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