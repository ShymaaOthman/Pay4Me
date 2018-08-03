package View.SignUpService;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import View.UIManager;
import View.PayApplication;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.hajjhack.pay4me.R;
import com.hajjhack.pay4me.scanItem.ScanFragment;

import java.io.ByteArrayOutputStream;

import presenter.storage.SharedPreferencesManager;

import static android.content.ContentValues.TAG;




public class RegisterActivity extends AppCompatActivity {
    RadioGroup gender_radio;
    private String gender;
    private TextView reister_btn;
    LinearLayout add_img;
    public static final int RequestPermissionCode = 1;
    private Dialog dialog;
    private Bitmap bitmap;
    private TextView upload;
    private ProgressDialog progressDialog;
    private ImageView ocr;
    private TextView passport_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        initilize();
    }

    private void initilize() {
        ocr=(ImageView)findViewById(R.id.ocr);
        gender_radio = (RadioGroup) findViewById(R.id.myRadioGroup);
        reister_btn = (TextView) findViewById(R.id.reister_btn);
        passport_id = (TextView) findViewById(R.id.passport_id);
        reister_btn = (TextView) findViewById(R.id.reister_btn);
        add_img = (LinearLayout) findViewById(R.id.add_img);
        upload = (TextView) findViewById(R.id.upload);

        ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startOcrScreen(RegisterActivity.this);
            }
        });
        add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (SharedPreferencesManager.getInstance(RegisterActivity.this).getBoolean("notaccepted")) {

                        showSettingsAlert();

                    } else if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this,
                                Manifest.permission.CAMERA)) {
                            openCamera();
                        } else {
                            // No explanation needed, we can request the permission.
                            ActivityCompat.requestPermissions(RegisterActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestPermissionCode);
                        }
                    }
                } else {
                    showOptionDialog();
                }


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
        final RadioButton male = (RadioButton) findViewById(R.id.male);

        reister_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                int selectedId = gender_radio.getCheckedRadioButtonId();
//
//                if (selectedId == male.getId()) {
//                    gender = "m";
//                } else if (selectedId == femal.getId()) {
//                    gender = "f";
//                }
              PayApplication.getInstance().passport_id=passport_id.getText().toString();
              PayApplication.getInstance().password="02390";
               new PayAsyncTask().execute();


            }
        });

    }
    private class PayAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(RegisterActivity.this,"Make New Account","Please wait ..",true);
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

           UIManager.startInterestsActivity(RegisterActivity.this,true);
           Toast.makeText(RegisterActivity.this,"Your Password is 02390",Toast.LENGTH_LONG).show();

        }
    }
    void createBusinessErrorDialog(Activity context, String title, final String message) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.confirm_dialog);
        Button yes_btn = (Button) dialog.findViewById(R.id.yes_btn);
        TextView title_txt = (TextView) dialog.findViewById(R.id.title_txt);
        title_txt.setText(title);
        TextView message_txt = (TextView) dialog.findViewById(R.id.message_txt);
        message_txt.setText(message);
        dialog.setCancelable(true);
        dialog.show();
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    private void showOptionDialog() {

        dialog = new Dialog(RegisterActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.option_dilaog_popup);

        Button camera = (Button) dialog.findViewById(R.id.camera_btn);
        Button media = (Button) dialog.findViewById(R.id.media_btn);

        // if button is clicked, close the custom dialog
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    showCameraAlert();
                } else {
                    openCamera();
                }

                dialog.dismiss();
            }
        });
        media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showMediaAlert();

                dialog.dismiss();

            }
        });
        dialog.show();


    }

    private void showMediaAlert() {

        Log.d(TAG, "galley_call: ");

        Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent2.setType("image/*");

        startActivityForResult(intent2, 1);

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

    private void openCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 7);
    }

    private void showCameraAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Application Wants To Use Camera");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Deny",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Allow",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(RegisterActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                RequestPermissionCode);
                        openCamera();

                    }
                });
        alertDialog.show();
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Application Wants To Acess Camera.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Deny",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startInstalledAppDetailsActivity(RegisterActivity.this);

                    }
                });
        alertDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String file_name;

        if (requestCode == 7 && resultCode == RESULT_OK) {


            Bitmap bmp = (Bitmap) data.getExtras().get("data");
//            product_img.setImageBitmap(bmp);
            upload.setTextColor(Color.parseColor("#40b77c"));
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // convert byte array to Bitmap

            bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                    byteArray.length);
        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                BitmapFactory.decodeStream(RegisterActivity.this.getContentResolver().openInputStream(selectedImage), null, options);
                options.inSampleSize = calculateInSampleSize(options, 300, 300);
                options.inJustDecodeBounds = false;
//                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, options);
//                product_img.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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
                                        (RegisterActivity.this, permission);
                        if (showRationale) {
                            showOptionDialog();
                        } else if (!showRationale) {
                            // user denied flagging NEVER ASK AGAIN
                            // you can either enable some fall back,
                            // disable features of your app
                            // or open another dialog explaining
                            // again the permission and directing to
                            // the app setting
                            SharedPreferencesManager.getInstance(RegisterActivity.this).setBoolean("notaccepted", true);
                        }
                    }
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request

        }
    }

}
