package org.warpten.permissionmonitor;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends Activity implements ListView.OnItemClickListener {

    protected void GenerateRunningApplicationList()
    {

        Storage.getInstance().Applications.clear();
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> processes = manager.getRunningAppProcesses();
        for (RunningAppProcessInfo process : processes)
        {
            ApplicationInfo info = new ApplicationInfo();
            info.Name = process.processName;
            info.PID = process.pid;
            info.UID = process.uid;
            for (String packageName : process.pkgList)
                try {
                    info.Packages.add(new PkgInfo(getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)));
                } catch (PackageManager.NameNotFoundException pmnnfe) {
                    Log.d("PermissionMonitor", "Package " + packageName + " not found (Application " + info.Name + "!");
                }
            Storage.getInstance().Applications.add(info);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GenerateRunningApplicationList();

        ListView list = (ListView) findViewById(R.id.runningAppListView);
        if (list != null) {
            list.setAdapter(new ApplicationListAdapter(this, Storage.getInstance().Applications));
            list.setOnItemClickListener(this);
        }
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
        if (id == R.id.action_refresh) {
            GenerateRunningApplicationList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent startIntent = new Intent(this, DetailsActivity.class);
        startIntent.putExtra("appIndex", position);
        startActivity(startIntent);
    }
}
