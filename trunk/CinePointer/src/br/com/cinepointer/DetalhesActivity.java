package br.com.cinepointer;


import br.com.cinepointer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DetalhesActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailed_information);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Create and add new menu items.
		MenuItem itemArmazenarBusca = menu.add(0,0, Menu.NONE,R.string.botaoArmazenarBusca);
		MenuItem itemAgendarCompromisso = menu.add(0,1, Menu.NONE, R.string.botaoCriarCompromisso);
		MenuItem itemGoogleMaps = menu.add(0,2, Menu.NONE, R.string.botaoIrMaps);

		// Assign icons
		//itemAdd.setIcon(R.drawable.add);
		//itemRem.setIcon(R.drawable.remove);

		// Allocate shortcuts to each of them.
		itemArmazenarBusca.setShortcut('0', 'b');
		itemAgendarCompromisso.setShortcut('1', 'c');
		itemGoogleMaps.setShortcut('2', 'm');
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {      
		case 0:
			Dialogs.imprimirMensagem(this,"titulo","clicou Busca ");
			break;
		case 1:
			Dialogs.imprimirMensagem(this,"titulo","clicou Compromisso");	
			break;
		case 2:
			Intent i = new Intent(this,MapVisualizador.class);
			startActivity(i);	
			break;
		}
		return true;
	}

}
