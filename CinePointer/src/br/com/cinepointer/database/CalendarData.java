package br.com.cinepointer.database;

import br.com.cinepointer.ui.Dialogs;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

public class CalendarData {
	/**
	 * Adds the event to a calendar. It lets the user choose the calendar
	 * @param ctx Context ( Please use the application context )
	 * @param title title of the event
	 * @param dtstart Start time: The value is the number of milliseconds since Jan. 1, 1970, midnight GMT.
	 * @param dtend End time: The value is the number of milliseconds since Jan. 1, 1970, midnight GMT.
	 */
	public static void addToCalendar(Context ctx, final String title, final long dtstart, final long dtend) {
	    final ContentResolver cr = ctx.getContentResolver();
	    Cursor cursor = null ;
	    ///if (Integer.parseInt(Build.VERSION.SDK) >= 8 )
	    //Uri uri = Uri.parse("content://com.android.calendar/calendars");
	    Uri uri = Uri.parse("content://calendar/calendars");
	    if(uri != null){
	    	Dialogs.imprimirMensagem(ctx,"erro",uri.toString());
		    cursor = cr.query(uri, new String[]{ "_id", "displayname" }, null, null, null);
	    }
	    else{
	    	Dialogs.imprimirMensagem(ctx,"erro","uri e nulo");
	    }
	    //else
	        //cursor = cr.query(Uri.parse("content://calendar/calendars"), new String[]{ "_id", "displayname" }, null, null, null);
	    if(cursor == null ){
	    	Dialogs.imprimirMensagem(ctx,"erro","cursor e nulo");
	    	
	    }
	    else{
	    if ( cursor.moveToFirst() ) {
	        final String[] calNames = new String[cursor.getCount()];
	        final int[] calIds = new int[cursor.getCount()];
	        for (int i = 0; i < calNames.length; i++) {
	            calIds[i] = cursor.getInt(0);
	            calNames[i] = cursor.getString(1);
	            cursor.moveToNext();
	        }
	 
	        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
	        builder.setSingleChoiceItems(calNames, -1, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                ContentValues cv = new ContentValues();
	                cv.put("calendar_id", calIds[which]);
	                cv.put("title", title);
	                cv.put("dtstart", dtstart );
	                cv.put("hasAlarm", 1);
	                cv.put("dtend", dtend);
	 
	                Uri newEvent ;
	                if (Integer.parseInt(Build.VERSION.SDK) == 8 )
	                    newEvent = cr.insert(Uri.parse("content://com.android.calendar/events"), cv);
	                else
	                    newEvent = cr.insert(Uri.parse("content://com.android.calendar/events"), cv);
	 
	                if (newEvent != null) {
	                    long id = Long.parseLong( newEvent.getLastPathSegment() );
	                    ContentValues values = new ContentValues();
	                    values.put( "event_id", id );
	                    values.put( "method", 1 );
	                    values.put( "minutes", 15 ); // 15 minuti
	                    if (Integer.parseInt(Build.VERSION.SDK) == 8 )
	                        cr.insert( Uri.parse( "content://com.android.calendar/reminders" ), values );
	                    else
	                        cr.insert( Uri.parse( "content://calendar/reminders" ), values );
	 
	                }
	                dialog.cancel();
	            }
	 
	        });
	 
	        builder.create().show();
	    }
	    cursor.close();
	    }
	}
}
