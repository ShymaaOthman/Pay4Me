package View;

import android.app.Activity;
import android.content.Intent;

import com.shymaaothman.hajj_pay4me.R;

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

    }


    /**
     * start LOGIN SCREEN
     *
     * @param activity
     */
    public static void startRegisteration(Activity activity, boolean startAnimation,String userData) {
        Intent mainIntent = new Intent(activity, RegisterActivity.class);
        mainIntent.putExtra("userData",userData);
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



    public static void startInterestsActivity(LoginActivity loginActivity, boolean startAnimation) {
        Intent mainIntent = new Intent(loginActivity, InterestsActivity.class);
        loginActivity.startActivity(mainIntent);
        if (startAnimation)
            loginActivity.overridePendingTransition(R.anim.enter, R.anim.exit);
        else
            loginActivity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }
}
