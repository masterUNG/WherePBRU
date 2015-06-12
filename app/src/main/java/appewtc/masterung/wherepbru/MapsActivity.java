package appewtc.masterung.wherepbru;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private double latCenterADouble, lngCenterADouble;
    private LatLng centerLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Create LatLng
        createLatLng();



        setUpMapIfNeeded();
    }   // onCreate

    private void createLatLng() {

        //About Center Map
        latCenterADouble = getIntent().getExtras().getDouble("lat");
        lngCenterADouble = getIntent().getExtras().getDouble("lng");
        centerLatLng = new LatLng(latCenterADouble, lngCenterADouble);

    }   //createLatLng

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }   // setupMapIfNeeded

    private void setUpMap() {

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 17));

    }   // setupMap

}   // Main Class
