package View.SignUpService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shymaaothman.hajj_pay4me.R;

import static com.google.android.gms.analytics.internal.zzy.m;
import static com.google.android.gms.analytics.internal.zzy.v;



public class RegisterActivity extends AppCompatActivity {
    RadioGroup gender_radio;
    private String gender;
    private TextView reister_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initilize();
    }

    private void initilize() {
        gender_radio = (RadioGroup) findViewById(R.id.myRadioGroup);
        reister_btn = (TextView) findViewById(R.id.reister_btn);
        reister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//
//        gender_radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // find which radio button is selected
//                if (checkedId == R.id.femal) {
//                    gender = "f";
//
//                } else if (checkedId == R.id.male) {
//                    gender = "m";
//
//                }
//            }
//
//        });

        final RadioButton femal = (RadioButton) findViewById(R.id.femal);
        final RadioButton male = (RadioButton) findViewById(R.id.main_container);

        reister_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId = gender_radio.getCheckedRadioButtonId();

                // find which radioButton is checked by id
                if (selectedId == male.getId()) {
                    gender = "m";
                } else if (selectedId == femal.getId()) {
                    gender = "f";
                }
            }
        });
    }


}
