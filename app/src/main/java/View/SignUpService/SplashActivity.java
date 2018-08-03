package View.SignUpService;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import View.UIManager;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import presenter.storage.SharedPreferencesManager;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.hajjhack.pay4me.R;

import java.util.ArrayList;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int RequestPermissionCode = 1;
    private ImageView logoImage;
    private RelativeLayout con;
    private TextView login;
    private TextView signup;
    private AlphaAnimation fadeOut;
    private LinearLayout homeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initilize();
    }

    private void initilize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            checkPermissions();
        }
        if (ContextCompat.checkSelfPermission(SplashActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (SharedPreferencesManager.getInstance(SplashActivity.this).getBoolean("notaccepted")) {

                showSettingsAlert();

            } else if (ContextCompat.checkSelfPermission(SplashActivity.this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,
                        Manifest.permission.CAMERA)) {
                    showAlert();
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(SplashActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            RequestPermissionCode);
                }
            }
        }

        SharedPreferencesManager.getInstance(SplashActivity.this).setString("loggedUser", null);
        logoImage = (ImageView) findViewById(R.id.logo_image);
        homeLayout = (LinearLayout) findViewById(R.id.home_layout);
        con = (RelativeLayout) findViewById(R.id.con);
        login = (TextView) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signup);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        Animation fadeIn = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.mainfadein);

//        try {
//            saveLogin = SharedPreferencesManager.getInstance(this).getBoolean(getResources().getString(R.string.saveLogin_pref), false);
//        } catch (Exception e) {
//            SharedPreferencesManager.getInstance(this).setBoolean(getResources().getString(R.string.saveLogin_pref), false);
//        }
//        Animation fadeIn = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.mainfadein);
//
//        if (!BuildConfig.UnTAXI) {
            logoImage.startAnimation(fadeIn);
//        } else {
//            fadeOut = new AlphaAnimation(1.0f, 0.3f);
//            fadeOut.setDuration(1000);
//            fadeOut.setFillAfter(true);
//            footer_image.startAnimation(fadeOut);
//            fadeOut.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart(Animation animation) {
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    Animation bottomUp = AnimationUtils.loadAnimation(SplashActivity.this,
//                            R.anim.bottom_up);
//
//                    if (SharedPreferencesManager.getInstance(SplashActivity.this).getBoolean(getResources().getString(R.string.update_now_pref), false)) {
//                        con.startAnimation(bottomUp);
//                        update_layout.setVisibility(View.VISIBLE);
////                    footer_image.setVisibility(View.INVISIBLE);
//                    } else {
//                        if (saveLogin == true && AppController.getInstance().isNetworkStatusAvialable(getApplicationContext())) {
//                            UIManager.startlogin(SplashActivity.this, true);
//                            finish();
//                        } else {
//
//                            con.startAnimation(bottomUp);
//                            homeLayout.setVisibility(View.VISIBLE);
////                        footer_image.setVisibility(View.INVISIBLE);
//
//                        }
//                    }
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//                }
//            });
//        }

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation bottomUp = AnimationUtils.loadAnimation(SplashActivity.this,
                        R.anim.bottom_up);

//                if (SharedPreferencesManager.getInstance(SplashActivity.this).getBoolean(getResources().getString(R.string.update_now_pref), false)) {
//                    con.startAnimation(bottomUp);
//                    update_layout.setVisibility(View.VISIBLE);
//                } else {
//                    if (saveLogin == true && AppController.getInstance().isNetworkStatusAvialable(getApplicationContext())) {
//                        UIManager.startlogin(SplashActivity.this, true);
//                        finish();
//                    } else {

                        con.startAnimation(bottomUp);
                        homeLayout.setVisibility(View.VISIBLE);
//                    }
//                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.signup:

                UIManager.startRegisteration(SplashActivity.this,true,null);
                break;
            case R.id.login:
                UIManager.startlogin(SplashActivity.this, true);

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 111) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String text = data.getStringExtra(OcrCaptureActivity.TextBlockObject);
                    UIManager.startRegisteration(SplashActivity.this, true, text);
                } else {
                    Toast.makeText(SplashActivity.this, getResources().getString(R.string.error_msg), Toast.LENGTH_LONG).show();
                    UIManager.startRegisteration(SplashActivity.this, true, null);

                }
            } else {
                Toast.makeText(SplashActivity.this, getResources().getString(R.string.error_msg), Toast.LENGTH_LONG).show();
                UIManager.startRegisteration(SplashActivity.this, true,null);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(SplashActivity.this).create();
        alertDialog.setTitle("تنبيه");
        alertDialog.setMessage("التطبيق يريد السماح باستخدام الكاميرا.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "الرفض",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "الاعدادات",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startInstalledAppDetailsActivity(SplashActivity.this);

                    }
                });
        alertDialog.show();
    }


    @Override
    public void onRequestPermissionsResult
            (int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale
                                        (SplashActivity.this, permission);
                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            // user denied flagging NEVER ASK AGAIN
                            // you can either enable some fall back,
                            // disable features of your app
                            // or open another dialog explaining
                            // again the permission and directing to
                            // the app setting
                            SharedPreferencesManager.getInstance(SplashActivity.this).setBoolean("notaccepted",true);
                        }
                    }
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request

        }
    }

    public static void startInstalledAppDetailsActivity(final Activity context) {
        if (context == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }


    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(SplashActivity.this).create();
        alertDialog.setTitle("تنبيه");
        alertDialog.setMessage("التطبيق يريد السماح باستخدام الكاميرا.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "الرفض",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
//                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "السماح",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(SplashActivity.this ,
                                new String[]{Manifest.permission.CAMERA},
                                RequestPermissionCode);

                    }
                });
        alertDialog.show();
    }
    private void checkPermissions() {

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

            }


        };


        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleTitle(R.string.rationale_title)
                .setRationaleMessage(R.string.rationale_message) // "we need permission for read Camera and find your location"
                .setDeniedTitle(R.string.Permission_denied_title)
                .setDeniedMessage(R.string.permission_settings_msg)
                .setGotoSettingButtonText(getResources().getString(R.string.openSettings))
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();

    }

}
