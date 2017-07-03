package com.app.elixir.brewz_rock_cafe.utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AppFunctions {

    private String TAG = "UserFunctions";

    private JSONParser jsonParser;

    // private static String APP_URL = "http://undha.com/wedding/API/";
    private static String APP_URL = "http://192.168.3.105:8081/AppApi";
    private static String SIGNUP = APP_URL + "/GetProductCategories";
    private static String SIGNIN = APP_URL + "/login";
    private static String REGISTERFB = APP_URL + "/registerFb";

    private static String FORGOTPASSWORD = APP_URL + "/forgotPassword";
    private static String ALLSYNCDATA = APP_URL + "/allSyncData";

    private static String LOGOUT = APP_URL + "/logout";
    private static String ADDGUEST = APP_URL + "/addGuest";
    private static String ADDVENDOR = APP_URL + "/addVendor";
    private static String EDITVENDOR = APP_URL + "/editVendor";
    private static String DELETEVENDOR = APP_URL + "/deleteVendor";
    private static String EDITGUEST = APP_URL + "/editGuest";

    private static String ADDBUDGET = APP_URL + "/addBudget";
    private static String EDITBUDGET = APP_URL + "/editBudget";

    private static String CHANGEPASSWORD = APP_URL + "/changePassword";

    private static String DELETEBUDGET = APP_URL + "/deleteBudget";
    private static String DELETESAMPLE = APP_URL + "/deleteSample";
    private static String INVITATIONRESPONSE = APP_URL + "/invitationResponse";

    private static String ADDTODO = APP_URL + "/addTodo";
    private static String ADDSAMPLE = APP_URL + "/addSample";
    private static String ADDOFFLINEDATA = APP_URL + "/addOfflineData";
    private static String EDITTODO = APP_URL + "/editTodo";
    private static String DELETETODO = APP_URL + "/deleteTodo";
    private static String COMPLETETODOTASK = APP_URL + "/completeTodoTask";

    private static String EDITSAMPLE = APP_URL + "/editSample";
    private static String DELETEGUEST = APP_URL + "/deleteGuest";
    private static String MERGEWEDDINGDATA = APP_URL + "/mergeWeddingData";

    private static String EDITPROFILE = APP_URL + "/editProfile";
    private static String INVITEUSER = APP_URL + "/inviteUser";
    private static String NOTIFICATION = APP_URL + "/viewNotification";
    public static String VIEWCOUNTRYLIST = APP_URL + "/viewCountryList";
    public static String VIEWFOURM = APP_URL + "/viewFourm";
    public static String VIEWBLOG = APP_URL + "/viewBlog";

    private static String GET_CARD_DETAILS = APP_URL + "/viewCardDetails";
    private static String GET_CATEGORY_DETAILS = APP_URL
            + "/viewcategoryDetails";
    // private static String GET_OFFER_DETAILS = APP_URL + "/viewOfferDetails";
    private static String apikey = "a1b2c3wedding123weding7897";

    // http://192.168.1.23/whatsapp/checkusername.php?query=checkusername
    public AppFunctions() {
        jsonParser = new JSONParser();

    }

    public JSONObject getFormDetail(String userId, String accessToken,
                                    String regId, String deviceType, String udid) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", udid));
        android.util.Log.e("params", "" + params.toString());
        JSONObject json = jsonParser.getJSONFromUrl(VIEWFOURM, params);
        return json;
    }

    public JSONObject getBlogDetail(String userId, String accessToken,
                                    String regId, String deviceType, String udid) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", udid));
        android.util.Log.e("params", "" + params.toString());
        JSONObject json = jsonParser.getJSONFromUrl(VIEWBLOG, params);
        return json;
    }

    public JSONObject getedtNotificationList(String accessToken, String userId,
                                             String regId, String deviceType, String deviceUdid) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        android.util.Log.e("params", "" + params.toString());
        JSONObject json = jsonParser.getJSONFromUrl(NOTIFICATION, params);
        return json;
    }

//    @SuppressWarnings("deprecation")
//    public JSONObject uploadUserImage(Context context, String title,
//                                      String note, String mediaName, String accesseToken,
//                                      String deviceType, String deviceUdid, String regId, String userId,
//                                      String offlineSanpleId) {
//
//        JSONObject json = null;
//        String content = null;
//        MultipartEntity entity = new MultipartEntity(
//                HttpMultipartMode.BROWSER_COMPATIBLE);
//
//        try {
//
//            entity.addPart("apiKey", new StringBody(apikey));
//            entity.addPart("userID", new StringBody(userId));
//            entity.addPart("title", new StringBody(title));
//            entity.addPart("note", new StringBody(note));
//            entity.addPart("accessToken", new StringBody(accesseToken));
//            entity.addPart("deviceToken", new StringBody(regId));
//            entity.addPart("deviceType", new StringBody(deviceType));
//
//            Bitmap mediaImageName = BitmapUtils.StringToBitMap(mediaName);
//            if (mediaImageName == null) {
//
//                entity.addPart("sampleImage", new StringBody(""));
//            } else {
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                mediaImageName.compress(CompressFormat.PNG, 100, bos);
//                byte[] data = bos.toByteArray();
//                ByteArrayBody bab = new ByteArrayBody(data, "image/jpeg",
//                        "image.png");
//                entity.addPart("sampleImage", bab);
//            }
//            entity.addPart("intUdId", new StringBody(deviceUdid));
//            entity.addPart("offlineSampleID", new StringBody(offlineSanpleId));
//
//            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//            entity.writeTo(bytes);
//            content = bytes.toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        json = jsonParser.getMediaResult(ADDSAMPLE, entity);
//        android.util.Log.e("params", "" + content);
//        return json;
//    }

//    @SuppressWarnings("deprecation")
//    public JSONObject getedtSampleDataDetail(String title, String note,
//                                             String offlinesample, String sampleid, String accessToken,
//                                             String userId, String regId, String deviceType, String deviceUdid,
//                                             String mediaName) {
//
//        JSONObject json = null;
//        String content = null;
//        MultipartEntity entity = new MultipartEntity(
//                HttpMultipartMode.BROWSER_COMPATIBLE);
//
//        try {
//
//            entity.addPart("apiKey", new StringBody(apikey));
//            entity.addPart("userID", new StringBody(checkString(userId)));
//            entity.addPart("accessToken", new StringBody(
//                    checkString(accessToken)));
//            entity.addPart("deviceToken", new StringBody(checkString(regId)));
//            entity.addPart("type", new StringBody(checkString(deviceType)));
//            entity.addPart("sampleID", new StringBody(checkString(sampleid)));
//            entity.addPart("note", new StringBody(checkString(note)));
//            entity.addPart("title", new StringBody(checkString(title)));
//            entity.addPart("note", new StringBody(checkString(note)));
//
//            Bitmap mediaImageName = BitmapUtils.StringToBitMap(mediaName);
//            if (mediaImageName == null) {
//                entity.addPart("sampleImage", new StringBody(""));
//            } else {
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                mediaImageName.compress(CompressFormat.PNG, 100, bos);
//                byte[] data = bos.toByteArray();
//                ByteArrayBody bab = new ByteArrayBody(data, "image/jpeg",
//                        "image.png");
//                entity.addPart("sampleImage", bab);
//            }
//            entity.addPart("deviceType", new StringBody(deviceType));
//            entity.addPart("intUdId", new StringBody(deviceUdid));
//            entity.addPart("offlineSampleID", new StringBody(offlinesample));
//            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//            entity.writeTo(bytes);
//            content = bytes.toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        android.util.Log.e("params", "" + content);
//
//        json = jsonParser.getMediaResult(EDITSAMPLE, entity);
//
//        return json;
//    }

    public JSONObject getedtGuestDataDetail(String name, String guestNumber,
                                            String tableNumber, String guestType, String guestinvitationStatus,
                                            String memberCount, String note, String contactNumber,
                                            String email, String address, String accessToken, String userId,
                                            String regId, String deviceType, String udid,
                                            String offlineGuestId, String guestId) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("guestID", guestId));
        params.add(new BasicNameValuePair("guestName", checkString(name)));
        params.add(new BasicNameValuePair("guestNumber",
                checkString(guestNumber)));
        params.add(new BasicNameValuePair("tableNumber",
                checkString(tableNumber)));
        params.add(new BasicNameValuePair("guestType", checkString(guestType)));
        params.add(new BasicNameValuePair("invitationStatus",
                checkString(guestinvitationStatus)));
        params.add(new BasicNameValuePair("note", note));

        params.add(new BasicNameValuePair("contactNumber",
                checkString(contactNumber)));
        params.add(new BasicNameValuePair("email", checkString(email)));
        params.add(new BasicNameValuePair("address", checkString(address)));

        params.add(new BasicNameValuePair("offlineGuestID", offlineGuestId));
        params.add(new BasicNameValuePair("memberCount",
                checkString(memberCount)));

        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", udid));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(EDITGUEST, params);

        return json;
    }

    public JSONObject getedtTodoDataDetail(String name, String note,
                                           String offlineTodoId, String todoId, String accessToken,
                                           String userId, String regId, String deviceType, String deviceUdid) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("todoID", todoId));
        params.add(new BasicNameValuePair("name", checkString(name)));
        params.add(new BasicNameValuePair("note", checkString(note)));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("offlineTodoID", offlineTodoId));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(EDITTODO, params);

        return json;
    }

    public JSONObject getOfflineSyncDataDetail(String userId,
                                               String accessToken, String value, String regId, String deviceType,
                                               String deviceUdid) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("dataval", checkString(value)));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(ADDOFFLINEDATA, params);

        return json;
    }

    public JSONObject getBudgetDetail(String productName,
                                      String allocatedAmpount, String spentAmpunt, String makeNote,
                                      String accessToken, String regId, String deviceType, String udid,
                                      String userId, String offlineBudgetId) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("productName",
                checkString(productName)));
        params.add(new BasicNameValuePair("allocatedAmount",
                checkString(allocatedAmpount)));
        params.add(new BasicNameValuePair("spentAmount",
                checkString(spentAmpunt)));
        params.add(new BasicNameValuePair("note", checkString(makeNote)));

        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", udid));
        params.add(new BasicNameValuePair("offlineBudgetID", offlineBudgetId));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(ADDBUDGET, params);

        return json;
    }

    public JSONObject getTodoDataDetail(String producetName, String note,
                                        String accessToken, String deviceType, String deviceUdid,
                                        String regId, String userId, String OfflineTodoID) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("name", checkString(producetName)));
        params.add(new BasicNameValuePair("note", checkString(note)));

        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("offlineTodoID", OfflineTodoID));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(ADDTODO, params);

        return json;
    }

    public JSONObject getAllSyncDataDetail(String userId, String accessToken,
                                           String lastUpdated, String deviceToken, String deviceType,
                                           String udid) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("lastDateTime",
                checkString(lastUpdated)));
        params.add(new BasicNameValuePair("deviceToken", deviceToken));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", udid));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(ALLSYNCDATA, params);

        return json;
    }

    public JSONObject getChangePassDetail(String userId, String accessToken,
                                          String currentPass, String newPass, String confiPass, String regId,
                                          String deviceType, String deviceUdid) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("currentPassword",
                checkString(currentPass)));
        params.add(new BasicNameValuePair("newPassword", checkString(newPass)));
        params.add(new BasicNameValuePair("confirmPassword",
                checkString(confiPass)));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(CHANGEPASSWORD, params);

        return json;
    }

    public JSONObject getBudgetDetail(String productName,
                                      String allocatdAmount, String spentAmount, String note,
                                      String accessToken, String userId, String regId, String deviceType,
                                      String deviceUdid, String offlineBudgetID, String edtbudgetId) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("nCompanyID", "1"));
        params.add(new BasicNameValuePair("cAPIPass", "a"));
        params.add(new BasicNameValuePair("cAPIKey", "a"));
        params.add(new BasicNameValuePair("cAppStoreCode", "r-1"));
        android.util.Log.e("params", "" + params.toString());
        JSONObject json = jsonParser.getJSONFromUrl(SIGNUP, params);

        return json;
    }

    public JSONObject getDeletedDetail(String edtvenderId, String userId,
                                       String accessToken, String deviceType, String deviceUdid,
                                       String regId, String offlineVenderId) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("vendorID", checkString(edtvenderId)));
        params.add(new BasicNameValuePair("offlineVendorID", offlineVenderId));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(DELETEVENDOR, params);

        return json;
    }

    public JSONObject getTodoTaskCompletDetail(String edtvenderId,
                                               String userId, String accessToken, String deviceType,
                                               String deviceUdid, String regId, String checkStatus) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("todoID", edtvenderId));
        params.add(new BasicNameValuePair("todoStatus", checkStatus));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(COMPLETETODOTASK, params);

        return json;
    }

    public JSONObject getTodoDeletedDetail(String edtvenderId, String userId,
                                           String accessToken, String deviceType, String deviceUdid,
                                           String regId) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("todoID", edtvenderId));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(DELETETODO, params);

        return json;
    }

    public JSONObject getSampleDeletedDetail(String edtvenderId, String userId,
                                             String accessToken, String deviceType, String deviceUdid,
                                             String regId, String offlineSampleId) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("sampleID", edtvenderId));
        params.add(new BasicNameValuePair("offlineSampleID", offlineSampleId));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(DELETESAMPLE, params);

        return json;
    }

    public JSONObject getBudgetDeletedDetail(String edtvenderId, String userId,
                                             String accessToken, String deviceType, String deviceUdid,
                                             String regId, String offlineBudgetId) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("budgetID", edtvenderId));
        params.add(new BasicNameValuePair("offlineBudgetID", offlineBudgetId));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(DELETEBUDGET, params);

        return json;
    }

    public JSONObject getUserInviteDetailResponce(String userId,
                                                  String accessToken, String email, String regId, String deviceType,
                                                  String deviceUdid) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("email", checkString(email)));
        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(INVITEUSER, params);

        return json;
    }

    public JSONObject getMergeDetail(String userId, String accessToken,
                                     String regId, String deviceType, String deviceUdid) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(MERGEWEDDINGDATA, params);

        return json;
    }

    public JSONObject getGuestDeletedDetail(String edtvenderId, String userId,
                                            String accessToken, String deviceType, String deviceUdid,
                                            String regId, String offlineGuestId) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("guestID", edtvenderId));
        params.add(new BasicNameValuePair("offlineGuestID", edtvenderId));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(DELETEGUEST, params);

        return json;
    }

    public JSONObject getInviteGuestDetail(String guestname,
                                           String uniqueNumber, String tableNumber, String contactNumber,
                                           String email, String postalAddress, String guestNote,
                                           String accessToken, String deviceType, String deviceUdid,
                                           String regId, String userId, String guestType, String memberCount,
                                           String invitationStatus, String OfflineGuestID) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));

        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("guestName", checkString(guestname)));
        params.add(new BasicNameValuePair("guestNumber",
                checkString(uniqueNumber)));
        params.add(new BasicNameValuePair("tableNumber",
                checkString(tableNumber)));
        params.add(new BasicNameValuePair("note", checkString(guestNote)));
        params.add(new BasicNameValuePair("contactNumber",
                checkString(contactNumber)));
        params.add(new BasicNameValuePair("email", checkString(email)));
        params.add(new BasicNameValuePair("address", checkString(postalAddress)));
        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("memberCount",
                checkString(memberCount)));

        if (!guestType.equals("0")) {

            params.add(new BasicNameValuePair("guestType",
                    checkString(guestType)));
        } else {
            params.add(new BasicNameValuePair("guestType", ""));

        }
        if (!invitationStatus.equals("0")) {
            params.add(new BasicNameValuePair("invitationStatus",
                    checkString(invitationStatus)));
        } else {
            params.add(new BasicNameValuePair("invitationStatus", ""));

        }

        params.add(new BasicNameValuePair("offlineGuestID", OfflineGuestID));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(ADDGUEST, params);

        return json;
    }

    public JSONObject getedtVederDetail(String venderName,
                                        String venderService, String priceQuoted, String noteabout,
                                        String pricePaid, String makeaNote, String conatctNumber,
                                        String email, String accessToken, String deviceType, String regId,
                                        String userId, String deviceUdid, String offlineVendorID,
                                        String venderId) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));

        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("vendorName", checkString(venderName)));
        params.add(new BasicNameValuePair("service", checkString(venderService)));
        params.add(new BasicNameValuePair("price", checkString(priceQuoted)));
        params.add(new BasicNameValuePair("noteAboutWork",
                checkString(noteabout)));
        params.add(new BasicNameValuePair("amountPaid", checkString(pricePaid)));
        params.add(new BasicNameValuePair("noteAboutAmount",
                checkString(makeaNote)));
        params.add(new BasicNameValuePair("contactNumber",
                checkString(conatctNumber)));
        params.add(new BasicNameValuePair("email", checkString(email)));

        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("offlineVendorID", offlineVendorID));
        params.add(new BasicNameValuePair("vendorID", venderId));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(EDITVENDOR, params);

        return json;
    }

    public JSONObject getAddVederDetail(String venderName,
                                        String venderService, String priceQuoted, String noteabout,
                                        String pricePaid, String makeaNote, String conatctNumber,
                                        String email, String accessToken, String deviceType, String regId,
                                        String userId, String deviceUdid, String offlineVendorID) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));

        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("vendorName", checkString(venderName)));
        params.add(new BasicNameValuePair("service", checkString(venderService)));
        params.add(new BasicNameValuePair("price", checkString(priceQuoted)));
        params.add(new BasicNameValuePair("noteAboutWork",
                checkString(noteabout)));
        params.add(new BasicNameValuePair("amountPaid", checkString(pricePaid)));
        params.add(new BasicNameValuePair("noteAboutAmount",
                checkString(makeaNote)));
        params.add(new BasicNameValuePair("contactNumber",
                checkString(conatctNumber)));
        params.add(new BasicNameValuePair("email", checkString(email)));

        params.add(new BasicNameValuePair("deviceToken", regId));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", deviceUdid));
        params.add(new BasicNameValuePair("offlineVendorID", offlineVendorID));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(ADDVENDOR, params);

        return json;
    }

    public JSONObject getemailResponse(String email) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("email", email));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(FORGOTPASSWORD, params);

        return json;
    }

    public JSONObject getCategoryDetails(String apikey, String cardID,
                                         String languageID) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("cardID", cardID));
        params.add(new BasicNameValuePair("languageID", languageID));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(GET_CATEGORY_DETAILS,
                params);

        return json;
    }

    public JSONObject getCountrtListDetails() {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(VIEWCOUNTRYLIST, params);

        return json;
    }

//    public JSONObject getSignUpDetail(String firstName, String lastName,
//                                      String country, String weddingDate, String email, String password,
//                                      String deviceToken, String deviceUuid, String deviceType) {
//
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("apiKey", apikey));
//
//        params.add(new BasicNameValuePair("firstName", checkString(firstName)));
//        params.add(new BasicNameValuePair("lastName", checkString(lastName)));
//        params.add(new BasicNameValuePair("email", checkString(email)));
//        params.add(new BasicNameValuePair("password", checkString(password)));
//        params.add(new BasicNameValuePair("countryID", checkString(country)));
//
//        if (weddingDate != null && !weddingDate.equals("")) {
//            params.add(new BasicNameValuePair("weddingDate", Util
//                    .dateForamnent(weddingDate)));
//
//        } else {
//            params.add(new BasicNameValuePair("weddingDate", ""));
//
//        }
//
//        params.add(new BasicNameValuePair("deviceType", deviceType));
//        params.add(new BasicNameValuePair("deviceToken", deviceToken));
//        params.add(new BasicNameValuePair("intUdId", deviceUuid));
//        params.add(new BasicNameValuePair("registerType", "0"));
//
//        android.util.Log.e("params", "" + params.toString());
//
//        JSONObject json = jsonParser.getJSONFromUrl(SIGNUP, params);
//
//        return json;
//    }

    public JSONObject getfbDetail(String fbId, String firstName,
                                  String lastName, String country, String weddingDate, String email,
                                  String password, String deviceToken, String deviceUuid,
                                  String deviceType) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));

        params.add(new BasicNameValuePair("firstName", checkString(firstName)));
        params.add(new BasicNameValuePair("lastName", checkString(lastName)));

        params.add(new BasicNameValuePair("fbID", checkString(fbId)));
        params.add(new BasicNameValuePair("email", checkString(email)));

        params.add(new BasicNameValuePair("countryID", checkString(country)));

        if (weddingDate != null && !weddingDate.equals("")) {
            params.add(new BasicNameValuePair("weddingDate", weddingDate));

        } else {
            params.add(new BasicNameValuePair("weddingDate", ""));

        }
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("deviceToken", deviceToken));
        params.add(new BasicNameValuePair("intUdId", deviceUuid));
        params.add(new BasicNameValuePair("registerType", "1"));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(REGISTERFB, params);

        return json;
    }

    public JSONObject getInvitationResposeDetails(String invstatus,
                                                  String userId, String accessToken, String regid, String deviceType,
                                                  String uuid, String wediD) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("inviteStatus",
                checkString(invstatus)));
        params.add(new BasicNameValuePair("deviceToken", regid));
        params.add(new BasicNameValuePair("intUdId", uuid));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("weddingID", wediD));
        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(INVITATIONRESPONSE, params);

        return json;

    }

    public JSONObject getSingnInDetails(String email, String pass,
                                        String regit, String uuid, String devictype) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("email", checkString(email)));
        params.add(new BasicNameValuePair("password", checkString(pass)));
        params.add(new BasicNameValuePair("deviceToken", regit));
        params.add(new BasicNameValuePair("intUdId", uuid));
        params.add(new BasicNameValuePair("deviceType", devictype));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(SIGNIN, params);

        return json;

    }

    public JSONObject getlogoutDetail(String userId, String accessToken,
                                      String deviceToken, String deviceType, String udid) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("userID", userId));
        params.add(new BasicNameValuePair("accessToken", accessToken));
        params.add(new BasicNameValuePair("deviceToken", deviceToken));
        params.add(new BasicNameValuePair("deviceType", deviceType));
        params.add(new BasicNameValuePair("intUdId", udid));

        android.util.Log.e("params", "" + params.toString());

        JSONObject json = jsonParser.getJSONFromUrl(LOGOUT, params);

        return json;
    }

//    public JSONObject getedtUserDetail(String userId, String accessToken,
//                                       String userFirstName, String userlastName, String country,
//                                       String weddingDate, String regid, String deviceType, String udid) {
//
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("apiKey", apikey));
//        params.add(new BasicNameValuePair("userID", userId));
//        params.add(new BasicNameValuePair("accessToken", accessToken));
//        params.add(new BasicNameValuePair("firstName",
//                checkString(userFirstName)));
//        params.add(new BasicNameValuePair("lastName", checkString(userlastName)));
//        params.add(new BasicNameValuePair("countryID", checkString(country)));
//        if (weddingDate != null && !weddingDate.equals("")) {
//            params.add(new BasicNameValuePair("weddingDate", Util
//                    .dateForamnent(weddingDate)));
//
//        } else {
//            params.add(new BasicNameValuePair("weddingDate", ""));
//
//        }
//        params.add(new BasicNameValuePair("deviceToken", regid));
//        params.add(new BasicNameValuePair("deviceType", deviceType));
//        params.add(new BasicNameValuePair("intUdId", udid));
//
//        android.util.Log.e("params", "" + params.toString());
//
//        JSONObject json = jsonParser.getJSONFromUrl(EDITPROFILE, params);
//
//        return json;
//    }

    public JSONObject addDevicetoken(String apikey, String deviceToken,
                                     String deviceType, String offerID, String languageID) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiKey", apikey));
        params.add(new BasicNameValuePair("deviceToken", deviceToken));
        params.add(new BasicNameValuePair("deviceType", deviceType));

        android.util.Log.e("params", "" + params.toString());

        // JSONObject json = jsonParser.getJSONFromUrl(ADD_DEVICE_TOKEN,
        // params);

        return null;
    }

    public String checkString(String value) {

        String val = "";

        if (!value.equals("")) {

            val = value;
        } else {
            val = "";

        }
        return val;
    }

}
