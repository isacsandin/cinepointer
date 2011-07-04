package br.com.cinepointer.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class Dialogs {
	
	static String  escolhido = "";
	
	public static void imprimirMensagem(Context con,String title, String msg){
		AlertDialog.Builder dialogo; 
        dialogo = new AlertDialog.Builder(con);
		dialogo.setTitle(title);
		dialogo.setMessage(msg);
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
		}
	
	public static void imprimirTexto(Context con,String msg){
		Toast.makeText(con,msg,Toast.LENGTH_LONG).show();
	}
	
	public static void listDialog(final Context con,String title, final String[] items){
		
		AlertDialog.Builder dialogo; 
		dialogo = new AlertDialog.Builder(con);
		
        DialogInterface.OnClickListener ocl = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	escolhido = items[which];
            }
        };
		
        dialogo.setTitle(title);
        dialogo.setItems(items,ocl);
        dialogo.create();
        dialogo.show();  
	}

}
