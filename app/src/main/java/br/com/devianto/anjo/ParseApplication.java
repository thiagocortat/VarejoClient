package br.com.devianto.anjo;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.github.mmin18.layoutcast.LayoutCast;
import com.pixplicity.easyprefs.library.Prefs;


public class ParseApplication extends Application {

  private static Context mContext;

  @Override
  public void onCreate() {
    super.onCreate();

    setAppContext(getApplicationContext());

      if (BuildConfig.DEBUG) {
          LayoutCast.init(this);
      }

    // Initialize the Prefs class
    new Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(getPackageName())
            .setUseDefaultSharedPreference(true)
            .build();

    // Initialize Crash Reporting.
//    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
//    Parse.enableLocalDatastore(this);

//    ParseObject.registerSubclass(Tatto.class);
//    ParseObject.registerSubclass(Message.class);

      // Add your initialization code here
//      Parse.initialize(this, "G2lw9bOOI3T4SI42ULhUb4fcHDKISDbOJIRnZbRU", "FF4GFPPeBdpnvzt7eiNlqtEr1HIgGwazX4StO1Al");

      // Save the current Installation to Parse.
//      ParseInstallation.getCurrentInstallation().saveInBackground();

  }

  public static void setAppContext(Context context){
    mContext = context;
  }

  public static Context getAppContext(){
    return mContext;
  }


}
