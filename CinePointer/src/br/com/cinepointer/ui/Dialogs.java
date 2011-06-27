package br.com.cinepointer.ui;

import android.app.AlertDialog;
import android.content.Context;

public class Dialogs {
	
	public static void imprimirMensagem(Context con,String title, String msg){
		AlertDialog.Builder dialogo; 
        dialogo = new AlertDialog.Builder(con);
		dialogo.setTitle(title);
		dialogo.setMessage(msg);
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
		}
}
