package appewtc.masterung.wherepbru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }   // onCreate

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
