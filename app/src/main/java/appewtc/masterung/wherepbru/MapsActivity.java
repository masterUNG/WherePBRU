package appewtc.masterung.wherepbru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private double latCenterADouble, lngCenterADouble;
    private LatLng centerLatLng, engineerLatLng, computerLatLng,
                    accountLatLng, humentLatLng, architectLatLng,
                    cardLatLng, section1LatLng, section2LatLng, section3LatLng, userLatLng;
    private PolylineOptions engineerPolylineOptions, computerPolylineOptions, accountPolylineOptions;
    private Polyline engineerPolyline, computerPolyline, accountPolyline;
    private PolygonOptions pbruPolygonOptions;

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
        section3LatLng = new LatLng(13.073428, 99.978241);

        //user Maker
        double douUserLat = getIntent().getExtras().getDouble("userLat");
        double douUserLng = getIntent().getExtras().getDouble("userLng");
        userLatLng = new LatLng(douUserLat, douUserLng);

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

        //Create Polyline
        createPolyLine();

        //Create OnMapClick
        createOnMapClick();

        //Create PolyGon
        createPolygon();

        //Maker onClick
        makerOnClick();

    }   // setupMap

    private void makerOnClick() {

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                double douLat = marker.getPosition().latitude;
                double douLng = marker.getPosition().longitude;

                Log.d("pbru", "Id of Marker ==> " + String.valueOf(marker.getId()));

                Intent objIntent = new Intent(MapsActivity.this, DetailActivity.class);
                objIntent.putExtra("Lat", douLat);
                objIntent.putExtra("Lng", douLng);
                startActivity(objIntent);

                return true;
            }
        });

    }   //makerOnClick

    private void createPolygon() {

        pbruPolygonOptions = new PolygonOptions();
        pbruPolygonOptions.add(new LatLng(13.069446, 99.977555))
                .add(new LatLng(13.073323, 99.975816))
                .add(new LatLng(13.073261, 99.979593))
                .add(new LatLng(13.069710, 99.980005))
                .add(new LatLng(13.069446, 99.977555))
                .strokeWidth(10)
                .strokeColor(Color.MAGENTA)
                .fillColor(Color.argb(50, 185, 237, 108));
        mMap.addPolygon(pbruPolygonOptions);

    }   // createPolygon

    private void createOnMapClick() {

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                //Remove Polyline
               removeAllPolyLine();

                showMyDialog();
            }   // event
        });

    }   //createOnMapClick

    private void showMyDialog() {

        CharSequence[] objCharSequences = {"Engineer", "Computer", "Account"};
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle("Please Choose Direction");
        objBuilder.setSingleChoiceItems(objCharSequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                switch (i) {
                    case 0:
                       mMap.addPolyline(engineerPolylineOptions);

                        break;
                    case 1:
                       mMap.addPolyline(computerPolylineOptions);

                        break;
                    case 2:
                       mMap.addPolyline(accountPolylineOptions);

                        break;
                }

                dialogInterface.dismiss();

            }   //event
        });
        objBuilder.show();

    }   // showMyDialog

    private void removeAllPolyLine() {

        engineerPolyline = mMap.addPolyline(engineerPolylineOptions);
        computerPolyline = mMap.addPolyline(computerPolylineOptions);
        accountPolyline = mMap.addPolyline(accountPolylineOptions);

        engineerPolyline.remove();
        computerPolyline.remove();
        accountPolyline.remove();

    }

    private void createPolyLine() {

        //Engineer Polyline
        engineerPolylineOptions = new PolylineOptions();
        engineerPolylineOptions.add(userLatLng)
                .add(cardLatLng)
                .add(section1LatLng)
                .add(section2LatLng)
                .add(engineerLatLng)
                .width(10)
                .color(Color.RED);
       // mMap.addPolyline(engineerPolylineOptions);

        //Computer Polyline
        computerPolylineOptions = new PolylineOptions();
        computerPolylineOptions.add(userLatLng)
                .add(cardLatLng)
                .add(section1LatLng)
                .add(section2LatLng)
                .add(new LatLng(13.072613, 99.978273))
                .add(computerLatLng)
                .width(10)
                .color(Color.GREEN);
       // mMap.addPolyline(computerPolylineOptions);

        //Account PolyLine
        accountPolylineOptions = new PolylineOptions();
        accountPolylineOptions.add(userLatLng)
                .add(cardLatLng)
                .add(section1LatLng)
                .add(section2LatLng)
                .add(section3LatLng)
                .add(accountLatLng)
                .width(10)
                .color(Color.YELLOW);
       // mMap.addPolyline(accountPolylineOptions);

    }   // createPolyline

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

        //Maker of User
        mMap.addMarker(new MarkerOptions()
                        .position(userLatLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.friend)));




    }   // createMaker

}   // Main Class
