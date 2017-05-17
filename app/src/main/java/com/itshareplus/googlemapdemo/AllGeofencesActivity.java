package com.itshareplus.googlemapdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.GooglePlayServicesUtil;

public class AllGeofencesActivity extends AppCompatActivity {

  // region Overrides
  private Context context;


    public static AllGeofencesActivity GeofenceActive;

    public static AllGeofencesActivity get() {
      if (GeofenceActive == null) {
          GeofenceActive = new AllGeofencesActivity();
      }
      return GeofenceActive;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();

        //gson = new Gson();
        //namedGeofences = new ArrayList<>();
        //namedGeofencesToRemove = new ArrayList<>();
        //prefs = this.context.getSharedPreferences(Constants.SharedPrefs.Geofences, Context.MODE_PRIVATE);

        //loadGeofences();
        GeofenceController.getInstance().init(context);
    }
/*
  public static GeofenceController.GeofenceControllerListener access(){
    return GeofenceController.getInstance().;
  }*/



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_all_geofences);

    setTitle(R.string.app_title);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
              .add(R.id.container, new AllGeofencesFragment())
              .commit();
    }

    //access();
  }

    public void access(NamedGeofence geofence){
        GeofenceController.getInstance().addGeofence(geofence);
    }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_all_geofences, menu);

    MenuItem item = menu.findItem(R.id.action_delete_all);

    if (GeofenceController.getInstance().getNamedGeofences().size() == 0) {
      item.setVisible(false);
    }

    return true;
  }

  @Override
  protected void onResume() {
    super.onResume();

    int googlePlayServicesCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    Log.i(AllGeofencesActivity.class.getSimpleName(), "googlePlayServicesCode = " + googlePlayServicesCode);

    if (googlePlayServicesCode == 1 || googlePlayServicesCode == 2 || googlePlayServicesCode == 3) {
      GooglePlayServicesUtil.getErrorDialog(googlePlayServicesCode, this, 0).show();
    }
  }

  // endregion
}