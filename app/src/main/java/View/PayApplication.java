package View;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.widget.Toast;

/**
 */

public class PayApplication extends Application  {


    private static PayApplication mInstance = new PayApplication();
    public static boolean selectAll=false;
    private Context context;
    private ConnectivityManager cm;
    public String data;


    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        context = getApplicationContext();
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

    }



    public static synchronized PayApplication getInstance() {
        return mInstance;
    }

    public static boolean isNetworkStatusAvialable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null)
                if (netInfos.isConnected())
                    return true;
        }
        return false;
    }


    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }



}
