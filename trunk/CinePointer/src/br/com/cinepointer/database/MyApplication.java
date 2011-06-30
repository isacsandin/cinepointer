package br.com.cinepointer.database;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

   public static final String APP_NAME = "AndroidExamples";  
   
   private DatabaseData dataHelper;   
   
   @Override
   public void onCreate() {
      super.onCreate();
      Log.d(APP_NAME, "APPLICATION onCreate");
      this.dataHelper = new DatabaseData(this);      
   }
   
   @Override
   public void onTerminate() {
      Log.d(APP_NAME, "APPLICATION onTerminate");      
      super.onTerminate();      
   }

   public DatabaseData getDataHelper() {
      return this.dataHelper;
   }

   public void setDataHelper(DatabaseData dataHelper) {
      this.dataHelper = dataHelper;
   }
}
