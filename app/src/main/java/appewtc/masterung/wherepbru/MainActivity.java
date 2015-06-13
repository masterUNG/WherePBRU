package appewtc.masterung.wherepbru;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private int mapAnInt;
    private TextView latTextView, lngTextView;
    private LocationManager objLocationManager;
    private Criteria objCriteria;
    private boolean gpsABoolean, networkABoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Get Location
        getLocation();


    }   // onCreate


    @Override
    protected void onStart() {
        super.onStart();

        gpsABoolean = objLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsABoolean) {
            networkABoolean = objLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!networkABoolean) {
                Intent objIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(objIntent);
            }
        }

    }   //onStart

    @Override
    protected void onResume() {
        super.onResume();

        setupAll();

    }   // onResume

    private void setupAll() {

        objLocationManager.removeUpdates(objLocationListener);

        String strLat = "Unknow";
        String strLng = "Unknow";

        Location networkLocation = requestUpdateFromProvider(LocationManager.NETWORK_PROVIDER, "Cannot Connected Internet");
        if (networkLocation != null) {
            strLat = String.format("%.7f", networkLocation.getLatitude());
            strLng = String.format("%.7f", networkLocation.getLongitude());
        }


        Location gpsLocation = requestUpdateFromProvider(LocationManager.GPS_PROVIDER, "GPS error");
        if (gpsLocation != null) {
            strLat = String.format("%.7f", gpsLocation.getLatitude());
            strLng = String.format("%.7f", gpsLocation.getLongitude());
        }



        latTextView.setText(strLat);
        lngTextView.setText(strLng);

    }   //setupAll

    @Override
    protected void onStop() {
        super.onStop();

        objLocationManager.removeUpdates(objLocationListener);

    }   // onStop

    //Create Event Change Time/ Change Distance
    public Location requestUpdateFromProvider(final String strProvider, String strError) {

        Location objLocation = null;

        if (objLocationManager.isProviderEnabled(strProvider)) {

            objLocationManager.requestLocationUpdates(strProvider, 1000, 10, objLocationListener);
            objLocation = objLocationManager.getLastKnownLocation(strProvider);

        } else {
            Toast.makeText(MainActivity.this, strError, Toast.LENGTH_SHORT).show();
        }

        return objLocation;
    }


    //Create Listener
    public final LocationListener objLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            latTextView.setText(String.format("%.7f", location.getLatitude()));
            lngTextView.setText(String.format("%.7f", location.getLongitude()));

        }   //onLocationChanged

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };




    private void getLocation() {

        objLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        objCriteria = new Criteria();
        objCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        objCriteria.setAltitudeRequired(false);
        objCriteria.setBearingRequired(false);

    }   //getLocation

    private void bindWidget() {
        latTextView = (TextView) findViewById(R.id.txtLat);
        lngTextView = (TextView) findViewById(R.id.txtLng);
    }

    public void clickArchitech(View view) {
        myIntent(13.06967551, 99.9786973);
    }

    public void clickHument(View view) {
        myIntent(13.07220462, 99.98032808);
    }

    public void clickAccount(View view) {
        myIntent(13.07315564, 99.97946978);
    }

    public void clickComputer(View view) {
        myIntent(13.07263311, 99.97739911);
    }

    public void clickEngineer(View view) {
        myIntent(13.07019806, 99.97874022);
    }

    private void myIntent(double douLat, double douLng) {
        Intent objIntent = new Intent(MainActivity.this, MapsActivity.class);
        objIntent.putExtra("lat", douLat);
        objIntent.putExtra("lng", douLng);
        objIntent.putExtra("mapType", mapAnInt);
        startActivity(objIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemNormal:
                mapAnInt = 1;
                break;
            case R.id.itemSatellite:
                mapAnInt = 2;
                break;
            case R.id.itemTerrain:
                mapAnInt = 3;
                break;
            case R.id.itemHybrid:
                mapAnInt = 4;
                break;
            default:
                mapAnInt = 1;
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
