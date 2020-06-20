package e_medikart.com.e_medikart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import e_medikart.com.e_medikart.network.ApiManager;
import e_medikart.com.e_medikart.network.RequestCode;
import e_medikart.com.e_medikart.network.RequestParam;
import e_medikart.com.e_medikart.utils.CommonUtil;
import e_medikart.com.e_medikart.utils.PreferenceManager;
import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;

/**
 * Created by Pujan on 09-02-2018.
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener,OkHttpInterface {
    private EditText mFirstname, mLastname, mEmail, mPassword, mConfirmpassword, mMobilenumber;
    private Button mRegister;
    private static String TAG = RegistrationActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mFirstname = (EditText) findViewById(R.id.edt_firstname);
        mLastname = (EditText) findViewById(R.id.edt_lastname);
        mEmail = (EditText) findViewById(R.id.edt_email);
        mPassword = (EditText) findViewById(R.id.edt_password);
        mConfirmpassword = (EditText) findViewById(R.id.edt_confirm_password);
        mMobilenumber = (EditText) findViewById(R.id.edt_mobile_number);
        mRegister = (Button) findViewById(R.id.btn_register);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_register:

                checkValidation();

               /* Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(i);*/

                break;


        }

    }


    private void checkValidation() {
        if (CommonUtil.isNullString(mFirstname.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_firstname), Toast.LENGTH_SHORT).show();
            //ToastUtil.show(RegistrationActivity.this,getResources().getString(R.string.toast_no_firstname));
        } else if (mFirstname.length() < 2 || mFirstname.length() > 20) {
            Toast.makeText(this, getResources().getString(R.string.toast_first_name_length_not_valid), Toast.LENGTH_SHORT).show();
        } else if (!CommonUtil.checkFirstLastName(mFirstname.getText().toString().trim())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_valid_firstname), Toast.LENGTH_SHORT).show();
        } else if (CommonUtil.isNullString(mLastname.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_lastname), Toast.LENGTH_SHORT).show();

        } else if (mLastname.length() < 2 || mLastname.length() > 20) {
            Toast.makeText(this, getResources().getString(R.string.toast_first_name_length_not_valid), Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.checkFirstLastName(mLastname.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_valid_lastname), Toast.LENGTH_SHORT).show();

        } else if (CommonUtil.isNullString(mEmail.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_email), Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.checkEmail(mEmail.getText().toString())) {

            Toast.makeText(this, getResources().getString(R.string.toast_email_format_wrong), Toast.LENGTH_SHORT).show();

        } else if (CommonUtil.isNullString(mPassword.getText().toString())) {

            Toast.makeText(this, getResources().getString(R.string.toast_no_password), Toast.LENGTH_SHORT).show();

        } else if (mPassword.length() < 8 || mPassword.length() > 15) {
            Toast.makeText(this, getResources().getString(R.string.toast_password_length_invalid), Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.checkPassword(mPassword.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_valid_password), Toast.LENGTH_SHORT).show();

        } else if (CommonUtil.isNullString(mConfirmpassword.getText().toString())) {

            Toast.makeText(this, getResources().getString(R.string.toast_no_confirm_password), Toast.LENGTH_SHORT).show();

        } else if (mConfirmpassword.length() < 8 || mConfirmpassword.length() > 15) {
            Toast.makeText(this, getResources().getString(R.string.toast_password_length_invalid), Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.checkPassword(mConfirmpassword.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_confirm_password), Toast.LENGTH_SHORT).show();

        } else if (!(mConfirmpassword.getText().toString()).equals(mPassword.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_password_mismatch), Toast.LENGTH_SHORT).show();

        } else if (CommonUtil.isNullString(mMobilenumber.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_contact), Toast.LENGTH_SHORT).show();

        } else {
            callApiForSignUp();
        }
    }

    private void callApiForSignUp() {

        new OkHttpRequest(RegistrationActivity.this,
                OkHttpRequest.Method.POST,
                ApiManager.USER_SIGNUP,
                RequestParam.userSignUp("" + mFirstname.getText().toString(), "" + mLastname.getText().toString(), "" + mEmail.getText().toString(), "" + mConfirmpassword.getText().toString(),""+mMobilenumber.getText().toString()),
                RequestParam.getNull(),
                RequestCode.CODE_USER_SIGNUP,
                true, this);

    }

    @Override
    public void onOkHttpStart(int requestId) {

    }

    @Override
    public void onOkHttpSuccess(int requestId, int statusCode, String response) {

        Log.e(TAG,"RESPONSE"+response);

        switch (requestId) {
            case RequestCode.CODE_USER_SIGNUP:
                try {
                    JSONObject root = new JSONObject("" + response);

                    int responseCode = root.getInt("success");
                    final String responseMessage = root.getString("message");

                    if (responseCode == RequestCode.SUCCESS_CODE) {

                        if (root.has("user_id")) {

                            String mStrUserId = root.getString("user_id");
                            Log.e(TAG, "mStrUserId==" + mStrUserId);
                            PreferenceManager.setPref(RegistrationActivity.this, mStrUserId, "USER_ID");

                        }

                        if (root.has("first_name")) {
                            String mStrFirstName = root.getString("first_name");
                            Log.e(TAG, "strFirstName==" + mStrFirstName);
                            PreferenceManager.setPref(RegistrationActivity.this, mStrFirstName, "USER_FIRSTNAME");

                        }
                        if (root.has("last_name")) {
                            String mStrLastName = root.getString("last_name");
                            Log.e(TAG, "strLastName==" + mStrLastName);
                            PreferenceManager.setPref(RegistrationActivity.this, mStrLastName, "USER_LASTNAME");

                        }
                        if (root.has("email_id")) {

                            String mStrEmail = root.getString("email_id");
                            Log.e(TAG, "mStrEmail==" + mStrEmail);
                            PreferenceManager.setPref(RegistrationActivity.this, mStrEmail, "USER_EMAIL");

                        }


                        this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegistrationActivity.this, "" + responseMessage, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                                finish();
                            }
                        });


                        //mPreferenceManager.saveLoginData(id,first_name,last_name,email_id);

                        // keep this flag true if user login with social account

                        // Redirect to dashboard or HomeActivity

                    } else {
                        Toast.makeText(RegistrationActivity.this, "" + responseMessage, Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onOkHttpFailure(int requestId, int statusCode, String response, Throwable error) {

    }

    @Override
    public void onOkHttpFinish(int requestId) {

    }
}
