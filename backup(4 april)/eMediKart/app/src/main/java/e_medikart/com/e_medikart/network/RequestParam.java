package e_medikart.com.e_medikart.network;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arati on 2/10/2016.
 */
public class RequestParam {

    private static String TAG = "RequestParam";
    private static String DEVICE_TYPE = "android";

    public static Map<String, String> getNull() {
        Map<String, String> mParam = new HashMap<String, String>();
        return mParam;
    }

    public static Map<String, String> userSignUp(String first_name, String last_name, String email_id, String password, String mobile) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("first_name", first_name.trim());
        requestBody.put("last_name", last_name.trim());
        requestBody.put("email_id", email_id.trim());
        requestBody.put("password", password.trim());
        requestBody.put("mobile", mobile.trim());

        return requestBody;
    }

    public static Map<String, String> userLogin(String email_id, String password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email_id", email_id.trim());
        requestBody.put("password", password.trim());
        return requestBody;
    }

    public static Map<String,String> manufacturer(String manufacturer){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("manufacturer_id", manufacturer.trim());
        return requestBody;
    }

    public static Map<String,String> addtokart(String user,String product){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id",user.trim());
        requestBody.put("product_id",product.trim());
        return requestBody;
    }

    public static Map<String,String> displaykart(String user){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id",user.trim());
        return requestBody;
    }

    public static Map<String, String> userChangePassword(String user_id, String old_password, String new_password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", user_id.trim());
        requestBody.put("old_password", old_password.trim());
        requestBody.put("new_password", new_password.trim());
        return requestBody;
    }

    public static Map<String, String> userForgotPassword(String email_id) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email_id", email_id.trim());
        return requestBody;
    }

    public static Map<String, String> userUpdate(String user_id, String first_name, String last_name, String email_id, String phone_no) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", user_id.trim());
        requestBody.put("first_name", first_name.trim());
        requestBody.put("last_name", last_name.trim());
        requestBody.put("email_id", email_id.trim());
        requestBody.put("phone_no", phone_no.trim());
        return requestBody;
    }

    public static Map<String, String> getAllCategories(String user_id) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_id", user_id.trim());
        return requestBody;
    }

    public static RequestBody updateUser(File updatedFile, String strUerID, String strFirstName, String strLastName, String strEmail, String ph_no) {
        MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
        if (updatedFile != null) {
            multipartBuilder.addFormDataPart("user_id", strUerID.trim())
                    .addFormDataPart("first_name", strFirstName.trim())
                    .addFormDataPart("last_name", strLastName.trim())
                    .addFormDataPart("email_id", strEmail.trim())
                    .addFormDataPart("phone_no", ph_no.trim())
                    .addFormDataPart("image", "test.png", RequestBody.create(MediaType.parse("image/*"), updatedFile));
        }
        RequestBody requestBody = multipartBuilder.build();
        return requestBody;

    }

    public enum SOCIAL_TYPE {
        google, facebook, twitter
    }


    public enum FAVOURITE_TYPE {
        add, remove
    }
}