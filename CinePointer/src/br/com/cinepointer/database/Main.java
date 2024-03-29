package br.com.cinepointer.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.com.cinepointer.R;
import br.com.cinepointer.R.id;
import br.com.cinepointer.R.layout;

import java.util.List;

public class Main extends Activity {

   private static final String NAME = "NAME";

   private static final int MENU_MANAGE = 0;
   
   private EditText input;
   private Button saveButton;
   private Button deleteButton;
   private TextView output;

   private MyApplication application;

   @Override
   public void onCreate(final Bundle savedInstanceState) {
      Log.d(MyApplication.APP_NAME, "onCreate");
      super.onCreate(savedInstanceState);

      this.setContentView(R.layout.main2);

      // get "Application" object for shared state or creating of expensive resources - like DataHelper
      // (this is not recreated as often as each Activity)
      this.application = (MyApplication) this.getApplication();

      // inflate views
      this.input = (EditText) this.findViewById(R.id.in_text);
      this.saveButton = (Button) this.findViewById(R.id.save_button);
      this.deleteButton = (Button) this.findViewById(R.id.del_button);
      this.output = (TextView) this.findViewById(R.id.out_text);

      // initially populate "output" view from database
      new SelectDataTask().execute();

      // save new data to database (when save button is clicked)
      this.saveButton.setOnClickListener(new OnClickListener() {
         public void onClick(final View v) {
            new InsertDataTask().execute(Main.this.input.getText().toString());
            Main.this.input.setText("");
         }
      });

      // delete all data from database (when delete button is clicked)
      this.deleteButton.setOnClickListener(new OnClickListener() {
         public void onClick(final View v) {
            new DeleteDataTask().execute();
         }
      });     
   }

   @Override
   public void onSaveInstanceState(final Bundle b) {
      Log.d(MyApplication.APP_NAME, "onSaveInstanceState");
      if ((this.input.getText().toString() != null) && (this.input.getText().toString().length() > 0)) {
         b.putString(Main.NAME, this.input.getText().toString());
      }
      super.onSaveInstanceState(b);
   }

   @Override
   public void onRestoreInstanceState(final Bundle b) {
      super.onRestoreInstanceState(b);
      Log.d(MyApplication.APP_NAME, "onRestoreInstanceState");
      String name = b.getString(Main.NAME);
      if (name != null) {
         // use onSaveInstanceState/onRestoreInstance state to manage state when orientation is changed (and whenever restarted)
         // put some text in input box, then rotate screen, text should remain
         // COMMENT this out, and try again, text won't be there - you need to maintain this state - esp for orientation changes
         // (you can rotate the screen in the emulator by pressing 9 on numeric keypad)
         this.input.setText(name);
      }
   }
   
   @Override
   public boolean onCreateOptionsMenu(final Menu menu) {
      menu.add(0, Main.MENU_MANAGE, 1, "Manage Database").setIcon(android.R.drawable.ic_menu_manage);
      return super.onCreateOptionsMenu(menu);
   }

   @Override
   public boolean onOptionsItemSelected(final MenuItem item) {
      switch (item.getItemId()) {      
      case MENU_MANAGE:
          return true;
      default:
         return super.onOptionsItemSelected(item);
      }
   }

   private class InsertDataTask extends AsyncTask<String, Void, Void> {
      private final ProgressDialog dialog = new ProgressDialog(Main.this);

      // can use UI thread here
      protected void onPreExecute() {
         this.dialog.setMessage("Inserting data...");
         this.dialog.show();
      }

      // automatically done on worker thread (separate from UI thread)
      protected Void doInBackground(final String... args) {
         Main.this.application.getDataHelper().insert(args[0]);
         return null;
      }

      // can use UI thread here
      protected void onPostExecute(final Void unused) {
         if (this.dialog.isShowing()) {
            this.dialog.dismiss();
         }
         // reset the output view by retrieving the new data
         // (note, this is a naive example, in the real world it might make sense
         // to have a cache of the data and just append to what is already there, or such
         // in order to cut down on expensive database operations)
         new SelectDataTask().execute();
      }
   }

   private class SelectDataTask extends AsyncTask<String, Void, String> {
      private final ProgressDialog dialog = new ProgressDialog(Main.this);

      // can use UI thread here
      protected void onPreExecute() {
         this.dialog.setMessage("Selecting data...");
         this.dialog.show();
      }

      // automatically done on worker thread (separate from UI thread)
      protected String doInBackground(final String... args) {
         List<String> names = Main.this.application.getDataHelper().selectAll();
         StringBuilder sb = new StringBuilder();
         for (String name : names) {
            sb.append(name + "\n");
         }
         return sb.toString();
      }

      // can use UI thread here
      protected void onPostExecute(final String result) {
         if (this.dialog.isShowing()) {
            this.dialog.dismiss();
         }
         Main.this.output.setText(result);
      }
   }

   private class DeleteDataTask extends AsyncTask<String, Void, Void> {
      private final ProgressDialog dialog = new ProgressDialog(Main.this);

      // can use UI thread here
      protected void onPreExecute() {
         this.dialog.setMessage("Deleting data...");
         this.dialog.show();
      }

      // automatically done on worker thread (separate from UI thread)
      protected Void doInBackground(final String... args) {
         Main.this.application.getDataHelper().deleteAll();
         return null;
      }

      // can use UI thread here
      protected void onPostExecute(final Void unused) {
         if (this.dialog.isShowing()) {
            this.dialog.dismiss();
         }
         // reset the output view by retrieving the new data
         // (note, this is a naive example, in the real world it might make sense
         // to have a cache of the data and just append to what is already there, or such
         // in order to cut down on expensive database operations)
         new SelectDataTask().execute();
      }
   }
}