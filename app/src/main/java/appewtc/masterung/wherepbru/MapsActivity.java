package appewtc.masterung.wherepbru;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private double latCenterADouble, lngCenterADouble;
    private LatLng centerLatLng, engineerLatLng, computerLatLng,
                    accountLatLng, humentLatLng, architectLatLng,
                    cardLatLng, section1LatLng, section2LatLng;

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

        //Setup for Maker
        engineerLatLng = new LatLng(13.07019806, 99.97874022);
        computerLatLng = new LatLng(13.07263311, 99.97739911);
        accountLatLng = new LatLng(13.07315564, 99.97946978);
        humentLatLng = new LatLng(13.07220462, 99.98032808);
        architectLatLng = new LatLng(13.06967551, 99.9786973);
        cardLatLng = new LatLng(13.06927838, 99.97543573);
        section1LatLng = new LatLng(13.070362, 99.976589);
        section2LatLng = new LatLng(13.070691, 99.978491);

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

        //Create MapType
        createMapType();

        //Create Maker
        createMaker();

    }   // setupMap

    private void createMapType() {

        //Receive mapType from MainActivity
        int intMap = getIntent().getExtras().getInt("mapType");

        switch (intMap) {
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case 4:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            default:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
        }

    }   //createMapType

    private void createMaker() {

        //From my Image
        mMap.addMarker(new MarkerOptions()
                .position(engineerLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build1))
                .title("วิศวะ")
                .snippet("คือที่ ที่เราเรียนวิศวะ กัน"));

        mMap.addMarker(new MarkerOptions()
                .position(computerLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build2))
                .title("คณะคอมพิวเตอร์")
                .snippet("ศูนย์คอมพิวเตอร์"));

        mMap.addMarker(new MarkerOptions()
                .position(accountLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build3))
                .title("บัญชี")
                .snippet("ที่ที่เราเรียนบัญชีกัน"));

        mMap.addMarker(new MarkerOptions()
                .position(humentLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build4)));

        mMap.addMarker(new MarkerOptions()
                .position(architectLatLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.build5)));

        //From default
        mMap.addMarker(new MarkerOptions().position(cardLatLng));

        //Change Color
        mMap.addMarker(new MarkerOptions()
                        .position(section1LatLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mMap.addMarker(new MarkerOptions()
                        .position(section2LatLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));


    }   // createMaker

}   // Main Class
