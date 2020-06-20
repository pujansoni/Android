package e_medikart.com.e_medikart;

import android.content.Intent;
import android.net.Uri;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OkHttpInterface {
    private EditText mEmail, mPassword;
    private CheckBox mRememberme;
    private Button mLogin, mSignup;
    private static String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = (EditText) findViewById(R.id.edt_email);
        mPassword = (EditText) findViewById(R.id.edt_password);
        mRememberme = (CheckBox) findViewById(R.id.chk_rememberMe);
        mLogin = (Button) findViewById(R.id.btn_login);
        mSignup = (Button) findViewById(R.id.btn_signup);
        mLogin.setOnClickListener(this);
        mSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_login:

                checkValidation();
                /*Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                */
                break;

            case R.id.btn_signup:

                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

                break;

        }
    }

    private void checkValidation() {
        if (CommonUtil.isNullString(mEmail.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_email), Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.checkEmail(mEmail.getText().toString())) {

            Toast.makeText(this, getResources().getString(R.string.toast_email_format_wrong), Toast.LENGTH_SHORT).show();

        } else if (CommonUtil.isNullString(mPassword.getText().toString())) {

            Toast.makeText(this, getResources().getString(R.string.toast_no_password), Toast.LENGTH_SHORT).show();

        } else if (mPassword.length() < 8 || mPassword.length() > 15) {
            Toast.makeText(this, getResources().getString(R.string.toast_password_length_invalid), Toast.LENGTH_SHORT).show();

        } else if (!CommonUtil.checkPassword(mPassword.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.toast_no_valid_password), Toast.LENGTH_SHORT).show();

        }
        else {
            callApiForLogin();
        }
    }

    private void callApiForLogin() {
        new OkHttpRequest(LoginActivity.this,
                OkHttpRequest.Method.POST,
                ApiManager.USER_LOGIN,
                RequestParam.userLogin("" + mEmail.getText().toString(), "" + mPassword.getText().toString()),
                RequestParam.getNull(),
                RequestCode.CODE_USER_LOGIN,
                true, this);
    }

    @Override
    public void onOkHttpStart(int requestId) {

    }

    @Override
    public void onOkHttpSuccess(int requestId, int statusCode, String response) {
        Log.e(TAG,"RESPONSE"+response);
        switch (requestId) {
            case RequestCode.CODE_USER_LOGIN:
                try {
                    JSONObject root = new JSONObject("" + response);

                    int responseCode = root.getInt("success");
                    final String responseMessage = root.getString("message");

                    if (responseCode == RequestCode.SUCCESS_CODE) {

                        if (root.has("user_id")) {

                            String mStrUserId = root.getString("user_id");
                            Log.e(TAG, "mStrUserId==" + mStrUserId);
                            PreferenceManager.setPref(LoginActivity.this,mStrUserId,"USER_ID");

                        }

                        if (root.has("first_name")) {
                            String mStrFirstName = root.getString("first_name");
                            Log.e(TAG, "strFirstName==" + mStrFirstName);
                            PreferenceManager.setPref(LoginActivity.this,mStrFirstName,"USER_FIRSTNAME");

                        }
                        if (root.has("last_name")) {
                            String mStrLastName = root.getString("last_name");
                            Log.e(TAG, "strLastName==" + mStrLastName);
                            PreferenceManager.setPref(LoginActivity.this,mStrLastName,"USER_LASTNAME");

                        }
                        if (root.has("email_id")) {

                            String mStrEmail = root.getString("email_id");
                            Log.e(TAG, "mStrEmail==" + mStrEmail);
                            PreferenceManager.setPref(LoginActivity.this,mStrEmail,"USER_EMAIL");

                        }



                        this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "" + responseMessage,Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        });


                        //mPreferenceManager.saveLoginData(id,first_name,last_name,email_id);

                        // keep this flag true if user login with social account

                        // Redirect to dashboard or HomeActivity

                    } else {
                        Toast.makeText(LoginActivity.this, "" + responseMessage,Toast.LENGTH_SHORT).show();

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
