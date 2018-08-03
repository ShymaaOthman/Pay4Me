package View.SignUpService;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hajjhack.pay4me.R;

import View.UIManager;

import org.w3c.dom.Text;
import View.PayApplication;


public class LoginActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initilize();
    }

    private void initilize() {
      
        EditText user_name_edit = (EditText) findViewById(R.id.user_name_edit);
        EditText user_pass_edit = (EditText) findViewById(R.id.user_pass_edit);
        if(PayApplication.getInstance().password!=null)
        {
            user_name_edit.setText(PayApplication.getInstance().passport_id);
            user_pass_edit.setText(PayApplication.getInstance().password);
        }else
        {
            user_name_edit.setText(" ");
            user_pass_edit.setText(" ");
        }
        TextView login_btn = (TextView) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PayAsyncTask().execute();
            }
        });
    }
    private class PayAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(LoginActivity.this,"Login","Please wait ..",true);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            UIManager.StartHome(LoginActivity.this,true);


        }
    }

}
