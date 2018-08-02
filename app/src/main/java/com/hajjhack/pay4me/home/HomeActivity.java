package com.hajjhack.pay4me.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.hajjhack.pay4me.R;
import com.hajjhack.pay4me.dashboard.DashboardFragment;
import com.hajjhack.pay4me.nearstservices.NearbyServiceFragment;
import com.hajjhack.pay4me.packages.PackagesFragment;
import com.hajjhack.pay4me.scanItem.ScanFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_scan:
                    // mTextMessage.setText(R.string.title_home);
                    ScanFragment scanFragment = new ScanFragment();
                    replaceFragment(scanFragment, true,"scan");

                    return true;

                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_home);
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    replaceFragment(dashboardFragment, true,"dashboard");

                   return true;

                case R.id.navigation_packages:
                  //  mTextMessage.setText(R.string.title_packages);

                    PackagesFragment packagesFragment = new PackagesFragment();
                    replaceFragment(packagesFragment, true,"packages");

                    return true;
                case R.id.navigation_nearby:
               //     mTextMessage.setText(R.string.title_nearby);

                     NearbyServiceFragment nearbyServiceFragment = new NearbyServiceFragment();
                     replaceFragment(nearbyServiceFragment,true,"nearby");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        checkPermissions();

    }

    private void checkPermissions() {

        if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);

            // MY_PERMISSIONS_REQUEST_READ_CAMERA is an app-defined int constant. The callback method gets theresult of the request.

        }
    }

    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 66;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    ScanFragment scanFragment = new ScanFragment();
                    replaceFragment(scanFragment,true,"scan");

                } else {
                    //Toast.makeText(this,getString(R.string.camer_permission),Toast.LENGTH_SHORT).show();


                }
                return;

            }
        }
    }

        public void replaceFragment(Fragment fragment, boolean addToBackstack,String name) {
        if (addToBackstack) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(name).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
        }
    }


}
