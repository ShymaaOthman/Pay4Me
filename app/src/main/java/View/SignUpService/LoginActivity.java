package View.SignUpService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hajjhack.pay4me.R;

import View.UIManager;

import org.w3c.dom.Text;



public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initilize();
    }

    private void initilize() {
        EditText user_name_edit = (EditText) findViewById(R.id.user_name_edit);
        EditText user_pass_edit = (EditText) findViewById(R.id.user_pass_edit);
        TextView login_btn = (TextView) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.startInterestsActivity(LoginActivity.this,true);
            }
        });
    }
}
