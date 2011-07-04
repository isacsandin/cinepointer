package br.com.cinepointer.ui;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import br.com.cinepointer.R;
import br.com.cinepointer.htmlparser.HtmlParser;

public class CinePointerActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.escolhe_pesquisa);


		try {
			HtmlParser.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Button b = (Button) findViewById(R.id.escolhePesquisaBotaoPesquisar);


		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
				Intent i;
				switch(rg.getCheckedRadioButtonId()){

				case R.id.radio0:
					i = new Intent(CinePointerActivity.this,PesquisaNomeActivity.class);
					startActivity(i);
					break;
				case R.id.radio1:
					i = new Intent(CinePointerActivity.this,PesquisaLocalizacaoActivity.class);
					startActivity(i);
					break;
				default:
					Dialogs.imprimirMensagem(getApplicationContext(),"ERRO","FERROU");
					break;
				}
			}
		});
	}




}