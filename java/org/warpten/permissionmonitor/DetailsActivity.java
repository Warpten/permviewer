package org.warpten.permissionmonitor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


public class DetailsActivity extends Activity implements Spinner.OnItemSelectedListener {

    private ApplicationInfo _appInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            _appInfo = Storage.getInstance().Applications.get(extras.getInt("appIndex"));

        setContentView(R.layout.activity_details);

        Spinner spinner = (Spinner) findViewById(R.id.display_spinner);
        if (spinner != null)
            spinner.setOnItemSelectedListener(this);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }*/

    /*@Override
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
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ListView lview = (ListView) findViewById(R.id.permission_list);
        if (lview == null)
            return;

        switch (position)
        {
            case 1:
                lview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, _appInfo.GetPermissions()));
                break;
            case 0:
                lview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, _appInfo.GetRequestedPermissions()));
                break;
            default:
                return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
