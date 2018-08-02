package View.SignUpService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import View.UIManager;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.hajjhack.pay4me.R;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

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
}
