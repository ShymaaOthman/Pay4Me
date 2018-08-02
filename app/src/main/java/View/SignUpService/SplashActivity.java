package View.SignUpService;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import View.UIManager;
import presenter.storage.SharedPreferencesManager;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.hajjhack.pay4me.R;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initilize();
    }

    private void initilize() {
        TextView signUp_btn = (TextView) findViewById(R.id.register_txt);
        TextView login_btn = (TextView) findViewById(R.id.login_txt);
        signUp_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.register_txt:
                UIManager.startOcrScreen(SplashActivity.this);
                break;
            case R.id.login_txt:
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
}
