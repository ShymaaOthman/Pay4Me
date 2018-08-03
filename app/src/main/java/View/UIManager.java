package View;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;


import com.hajjhack.pay4me.R;
import com.hajjhack.pay4me.home.HomeActivity;
import com.hajjhack.pay4me.packages.MainPackageActivity;
import com.hajjhack.pay4me.packages.SuggestedPackageActivity;

import View.SignUpService.InterestsActivity;
import View.SignUpService.LoginActivity;
import View.SignUpService.OcrActivity;
import View.SignUpService.OcrCaptureActivity;
import View.SignUpService.RegisterActivity;

/**
 * Created by Hagar on 3/18/2017.
 */

public class UIManager {
    /**
     * start LOGIN SCREEN
     *
     * @param activity
     */
    public static void startlogin(Activity activity, boolean startAnimation) {
        Intent mainIntent = new Intent(activity, LoginActivity.class);
        activity.startActivity(mainIntent);
        if (startAnimation)
            activity.overridePendingTransition(R.anim.enter, R.anim.exit);
        else
            activity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        activity.finish();
    }


    /**
     * start LOGIN SCREEN
     *
     * @param activity
     */
    public static void startRegisteration(Activity activity, boolean startAnimation, String userData) {
        Intent mainIntent = new Intent(activity, RegisterActivity.class);
        mainIntent.putExtra("userData", userData);
        activity.startActivity(mainIntent);
        if (startAnimation)
            activity.overridePendingTransition(R.anim.enter, R.anim.exit);
        else
            activity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    /**
     * start OcrScreen
     *
     * @param activity
     */
    public static void startOcrScreen(Activity activity) {
        Intent intent = new Intent(activity, OcrCaptureActivity.class);
        intent.putExtra(OcrCaptureActivity.AutoFocus, true);
        intent.putExtra(OcrCaptureActivity.UseFlash, false);

        activity.startActivityForResult(intent, 111);
        activity.overridePendingTransition(R.anim.enter, R.anim.exit);

    }


    public static void startInterestsActivity(Activity loginActivity, boolean startAnimation) {
        Intent mainIntent = new Intent(loginActivity, InterestsActivity.class);
        loginActivity.startActivity(mainIntent);
        if (startAnimation)
            loginActivity.overridePendingTransition(R.anim.enter, R.anim.exit);
        else
            loginActivity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

        loginActivity.finish();

    }

    public static void startMainPackageActivity(Activity loginActivity, boolean startAnimation) {
        Intent mainIntent = new Intent(loginActivity, MainPackageActivity.class);
        loginActivity.startActivity(mainIntent);
        if (startAnimation)
            loginActivity.overridePendingTransition(R.anim.enter, R.anim.exit);
        else
            loginActivity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    public static void startSugestedPackageActivity(Activity loginActivity, boolean startAnimation) {
        Intent mainIntent = new Intent(loginActivity, SuggestedPackageActivity.class);
        loginActivity.startActivity(mainIntent);
        if (startAnimation)
            loginActivity.overridePendingTransition(R.anim.enter, R.anim.exit);
        else
            loginActivity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        loginActivity.finish();


    }

    public static void StartHome(Activity activity, boolean b) {
        Intent mainIntent = new Intent(activity, HomeActivity.class);
        activity.startActivity(mainIntent);
        activity.overridePendingTransition(R.anim.enter, R.anim.exit);
        activity.finish();

    }
}
