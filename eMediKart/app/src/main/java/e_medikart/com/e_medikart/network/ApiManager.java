package e_medikart.com.e_medikart.network;

/**
 * Created by arti on 2/9/2016.
 */
public class ApiManager {

    // Base url
    public static String BASE_URL = "http://192.168.43.201/ws/jsonController.php?";
    public static String IMAGE_URL = "http://192.168.43.201/ws/img/subcategory/";
    public static String IMAGE_BASE_URL = "";

    // Custom api url
    public static String USER_LOGIN = BASE_URL + "ws_signin";
    public static String USER_SIGNUP = BASE_URL + "ws_signup";
    public static String USER_CHANGE_PASSWORD = BASE_URL + "ws_changepassword";
    public static String USER_FORGOT_PASSWORD = BASE_URL + "ws_forgotpwd";
    public static String USER_UPDATE = BASE_URL + "ws_updateuser";
    public static String DISPLAY_CATEGORY = BASE_URL + "view_category";
    public static String DISPLAY_MANUFACTURER= BASE_URL + "view_manufacturer";
    public static String DISPLAY_PRODUCT= BASE_URL + "view_product";
    public static String DISPLAY_PRODUCT_MANUFACTURER = BASE_URL+"view_manufacturer_product";
    public static String ADD_TO_KART = BASE_URL+"ws_addtokart";
    public static String DISPLAY_KART = BASE_URL+"display_kart";
}
