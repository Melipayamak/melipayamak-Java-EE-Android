package ir.mp.java.mpjava;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.util.Collections;


public class SoapClientAsync extends AsyncTask<MPTaskParams, Integer, SoapObject> {

    private static String NAMESPACE = "http://tempuri.org/";
    private static String SEND_URL = "http://api.payamak-panel.com/post/send.asmx";
    private static String RECEIVE_URL = "http://api.payamak-panel.com/post/receive.asmx";
    private static String USERS_URL = "http://api.payamak-panel.com/post/Users.asmx";
    private static String CONTACTS_URL = "http://api.payamak-panel.com/post/contacts.asmx";
    private static String ACTIONS_URL = "http://api.payamak-panel.com/post/actions.asmx";
    private static String SCHEDULE_URL = "http://api.payamak-panel.com/post/Schedule.asmx";
    private static String VOICE_URL = "http://api.payamak-panel.com/post/Voice.asmx";

    private String Username, Password;

    private static final String TAG = "melipayamak";

    //constructor
    public SoapClientAsync(String username, String password){
        Username = username;
        Password = password;
    }

    @Override
    protected SoapObject doInBackground(MPTaskParams... params) {
        // Create envelope
        //Declare the version of the SOAP request
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        // Set output SOAP object
        envelope.setOutputSoapObject(params[0].request);

        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(params[0].url);
        androidHttpTransport.debug = true;

        SoapObject response;

        System.setProperty("http.keepAlive", "false");

        // Invoke web service
        try {
            androidHttpTransport.call(params[0].soapAction, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        // Get the response
        response = (SoapObject) envelope.bodyIn;
        if(response != null)
            Log.d(TAG, response.toString());

        return response;
    }



    protected void getXMLResult(String url, String soapAction, SoapObject request) {
        this.execute(new MPTaskParams[]{new MPTaskParams(url, soapAction, request)});
    }

    //send webservice
    public void SendSimpleSMS2(String to, String from, String text, boolean isFlash) {

        String METHOD_NAME = "SendSimpleSMS2";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("to", to);
        request.addProperty("text", text);
        request.addProperty("isflash", String.valueOf(isFlash));

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SEND_URL, SOAP_ACTION, request);
    }

    public void SendByBaseNumber(String[] text, String to, long bodyId) {

        String METHOD_NAME = "SendByBaseNumber";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        String _text = "<string>" + TextUtils.join("</string><string>", text) + "</string>";
        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("text", _text);
        request.addProperty("to", to);
        request.addProperty("bodyId", String.valueOf(bodyId));

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SEND_URL, SOAP_ACTION, request);
    }

    public void SendByBaseNumber2(String text, String to, long bodyId) {

        String METHOD_NAME = "SendByBaseNumber2";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("text", text);
        request.addProperty("to", to);
        request.addProperty("bodyId", String.valueOf(bodyId));

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SEND_URL, SOAP_ACTION, request);
    }

    public void GetCredit() {

        String METHOD_NAME = "GetCredit";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SEND_URL, SOAP_ACTION, request);
    }

    public void GetDeliveries(long[] recIds) {

        String METHOD_NAME = "GetDeliveries";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        String _recIds = "<long>" + TextUtils.join("</long><long>", Collections.singleton(recIds)) + "</long>";
        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("recIds", _recIds);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SEND_URL, SOAP_ACTION, request);
    }

    public void GetSmsPrice(int irancellCount, int mtnCount, String from, String text) {

        String METHOD_NAME = "GetSmsPrice";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("irancellCount", irancellCount);
        request.addProperty("mtnCount", mtnCount);
        request.addProperty("from", from);
        request.addProperty("text", text);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SEND_URL, SOAP_ACTION, request);
    }

    public void SendSimpleSMS(String[] to, String from, String text, boolean isFlash) {

        String METHOD_NAME = "SendSimpleSMS";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        String _to = "<string>" + TextUtils.join("</string><string>", to) + "</string>";
        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("to", _to);
        request.addProperty("text", text);
        request.addProperty("isflash", String.valueOf(isFlash));

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SEND_URL, SOAP_ACTION, request);
    }

    public void SendSms(String to, String from, String text, boolean isFlash, String udh, long[] recIds, Base64 status) {

        String METHOD_NAME = "SendSms";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        String _recIds = "<long>" + TextUtils.join("</long><long>", Collections.singleton(recIds)) + "</long>";
        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("to", to);
        request.addProperty("text", text);
        request.addProperty("isflash", String.valueOf(isFlash));
        request.addProperty("udh", udh);
        request.addProperty("recId", _recIds);
        request.addProperty("status", status);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SEND_URL, SOAP_ACTION, request);
    }
    public void getMessages(int location, String from, int index, int count) {

        String METHOD_NAME = "getMessages";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("location", location);
        request.addProperty("index", index);
        request.addProperty("count", count);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SEND_URL, SOAP_ACTION, request);
    }


    //receive webservice
    public void GetMessagesReceptions(int msgId, int fromRows) {

        String METHOD_NAME = "GetMessagesReceptions";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("msgId", msgId);
        request.addProperty("fromRows", fromRows);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(RECEIVE_URL, SOAP_ACTION, request);
    }

    public void RemoveMessages2(int location, String msgIds) {

        String METHOD_NAME = "RemoveMessages2";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("location", location);
        request.addProperty("msgIds", msgIds);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(RECEIVE_URL, SOAP_ACTION, request);
    }

    public void GetMessagesByDate(int location, String from, int index, int count, Date dateFrom, Date dateTo) {

        String METHOD_NAME = "GetMessagesByDate";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("location", location);
        request.addProperty("from", from);
        request.addProperty("index", index);
        request.addProperty("count", count);
        request.addProperty("dateFrom", dateFrom);
        request.addProperty("dateTo", dateTo);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(RECEIVE_URL, SOAP_ACTION, request);
    }

    public void GetUsersMessagesByDate(int location, String from, int index, int count, Date dateFrom, Date dateTo) {

        String METHOD_NAME = "GetUsersMessagesByDate";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("location", location);
        request.addProperty("from", from);
        request.addProperty("index", index);
        request.addProperty("count", count);
        request.addProperty("dateFrom", dateFrom);
        request.addProperty("dateTo", dateTo);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(RECEIVE_URL, SOAP_ACTION, request);
    }


    //users webservice
    public void GetUserNumbers() {

        String METHOD_NAME = "GetUserNumbers";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void GetUserTransactions(String targetUsername, String creditType, String keyword, Date dateFrom, Date dateTo) {

        String METHOD_NAME = "GetUserTransactions";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("targetUsername", targetUsername);
        request.addProperty("creditType", creditType);
        request.addProperty("keyword", keyword);
        request.addProperty("dateFrom", dateFrom);
        request.addProperty("dateTo", dateTo);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void GetUsers() {

        String METHOD_NAME = "GetUsers";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void HasFilter(String text) {

        String METHOD_NAME = "HasFilter";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("text", text);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void RemoveUser(String targetUsername) {

        String METHOD_NAME = "RemoveUser";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("targetUsername", targetUsername);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void GetProvinces() {

        String METHOD_NAME = "GetProvinces";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void GetCities(int provinceId) {

        String METHOD_NAME = "GetCities";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("provinceId", provinceId);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void GetExpireDate() {

        String METHOD_NAME = "GetExpireDate";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void AddPayment(String name, String family, String bankName, String code, double amount, String cardNumber) {

        String METHOD_NAME = "AddPayment";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("name", name);
        request.addProperty("family", family);
        request.addProperty("bankName", bankName);
        request.addProperty("code", code);
        request.addProperty("amount", amount);
        request.addProperty("cardNumber", cardNumber);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void AuthenticateUser() {

        String METHOD_NAME = "AuthenticateUser";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void ChangeUserCredit(double amount, String description, String targetUsername, boolean GetTax) {

        String METHOD_NAME = "ChangeUserCredit";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("targetUsername", targetUsername);
        request.addProperty("description", description);
        request.addProperty("amount", amount);
        request.addProperty("GetTax", String.valueOf(GetTax));

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void AuthenticateUser(String mobileNumber, String emailAddress, String targetUsername) {

        String METHOD_NAME = "AuthenticateUser";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("mobileNumber", mobileNumber);
        request.addProperty("emailAddress", emailAddress);
        request.addProperty("targetUsername", targetUsername);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void GetUserBasePrice(String targetUsername) {

        String METHOD_NAME = "GetUserBasePrice";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("targetUsername", targetUsername);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void GetUserCredit(String targetUsername) {

        String METHOD_NAME = "GetUserCredit";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("targetUsername", targetUsername);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void GetUserDetails(String targetUsername) {

        String METHOD_NAME = "GetUserDetails";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("targetUsername", targetUsername);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }

    public void GetUserDetails(String targetUsername, String creditType, Date dateFrom, Date dateTo, String keyword) {

        String METHOD_NAME = "GetUserDetails";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("targetUsername", targetUsername);
        request.addProperty("creditType", creditType);
        request.addProperty("dateFrom", dateFrom);
        request.addProperty("dateTo", dateTo);
        request.addProperty("keyword", keyword);


        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(USERS_URL, SOAP_ACTION, request);
    }


    //contacts webservice
    public void AddGroup(String groupName, String Descriptions, boolean showToChilds) {

        String METHOD_NAME = "AddGroup";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("groupName", groupName);
        request.addProperty("Descriptions", Descriptions);
        request.addProperty("showToChilds", String.valueOf(showToChilds));

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(CONTACTS_URL, SOAP_ACTION, request);
    }
    public void CheckMobileExistInContact(String mobileNumber) {

        String METHOD_NAME = "CheckMobileExistInContact";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("mobileNumber", mobileNumber);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(CONTACTS_URL, SOAP_ACTION, request);
    }

    public void GetContacts(int groupId, String keyword, int from, int count) {

        String METHOD_NAME = "GetContacts";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("groupId", groupId);
        request.addProperty("keyword", keyword);
        request.addProperty("count", count);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(CONTACTS_URL, SOAP_ACTION, request);
    }

    public void GetGroups() {

        String METHOD_NAME = "GetGroups";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(CONTACTS_URL, SOAP_ACTION, request);
    }

    public void RemoveContact(String mobilenumber) {

        String METHOD_NAME = "RemoveContact";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("mobilenumber", mobilenumber);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(CONTACTS_URL, SOAP_ACTION, request);
    }

    public void GetContactEvents(int contactId) {

        String METHOD_NAME = "GetContactEvents";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("contactId", contactId);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(CONTACTS_URL, SOAP_ACTION, request);
    }


    //actions webservice
    public void GetBranchs(int owner) {

        String METHOD_NAME = "GetBranchs";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("owner", owner);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void AddBranch(int owner, String branchName) {

        String METHOD_NAME = "AddBranch";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("owner", owner);
        request.addProperty("branchName", branchName);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void AddNumber(int branchId, String[] mobileNumbers) {

        String METHOD_NAME = "AddNumber";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        String _numbers = "<string>" + TextUtils.join("</string><string>", mobileNumbers) + "</string>";
        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("branchId", branchId);
        request.addProperty("mobileNumbers", _numbers);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void RemoveBranch(int branchId) {

        String METHOD_NAME = "RemoveBranch";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("branchId", branchId);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void AddBulk(String from, int branch, Byte bulkType, String title, String message,
                          String rangeFrom, String rangeTo, Date DateToSend, int requestCount, int rowFrom) {

        String METHOD_NAME = "AddBulk";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("branch", branch);
        request.addProperty("bulkType", bulkType);
        request.addProperty("title", title);
        request.addProperty("message", message);
        request.addProperty("rangeFrom", rangeFrom);
        request.addProperty("rangeTo", rangeTo);
        request.addProperty("DateToSend", DateToSend);
        request.addProperty("requestCount", requestCount);
        request.addProperty("rowFrom", rowFrom);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void GetBulkCount(int branch, String rangeFrom, String rangeTo) {

        String METHOD_NAME = "GetBulkCount";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("branch", branch);
        request.addProperty("rangeFrom", rangeFrom);
        request.addProperty("rangeTo", rangeTo);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void GetBulkReceptions(int bulkId, int fromRows) {

        String METHOD_NAME = "GetBulkReceptions";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("bulkId", bulkId);
        request.addProperty("fromRows", fromRows);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void GetBulkStatus(int bulkId, int sent, int failed, byte status) {

        String METHOD_NAME = "GetBulkStatus";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("bulkId", bulkId);
        request.addProperty("sent", sent);
        request.addProperty("failed", failed);
        request.addProperty("status", status);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void GetTodaySent() {

        String METHOD_NAME = "GetTodaySent";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void GetTotalSent() {

        String METHOD_NAME = "GetTotalSent";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void RemoveBulk(int bulkId) {

        String METHOD_NAME = "RemoveBulk";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("bulkId", bulkId);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void SendMultipleSMS(String[] to, String from, String[] text, boolean isflash, String udh,
                                  long[] recIds) {

        String METHOD_NAME = "SendMultipleSMS";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        String _to = "<string>" + TextUtils.join("</string><string>", to) + "</string>";
        String _text = "<string>" + TextUtils.join("</string><string>", text) + "</string>";
        String _recIds = "<long>" + TextUtils.join("</long><long>", Collections.singleton(recIds)) + "</long>";
        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("to", _to);
        request.addProperty("from", from);
        request.addProperty("text", _text);
        request.addProperty("isflash", String.valueOf(isflash));
        request.addProperty("udh", udh);
        request.addProperty("recId", _recIds);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }

    public void UpdateBulkDelivery(int bulkId) {

        String METHOD_NAME = "UpdateBulkDelivery";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("bulkId", bulkId);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(ACTIONS_URL, SOAP_ACTION, request);
    }


    //schedule webservice
    public void AddSchedule(String to, String from, String text, boolean isFlash, Date scheduleDateTime,
                              String period) {

        String METHOD_NAME = "AddSchedule";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("to", to);
        request.addProperty("text", text);
        request.addProperty("isflash", String.valueOf(isFlash));
        request.addProperty("scheduleDateTime", scheduleDateTime);
        request.addProperty("period", period);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SCHEDULE_URL, SOAP_ACTION, request);
    }

    public void AddMultipleSchedule(String[] to, String from, String[] text, boolean isFlash, Date[] scheduleDateTime, String period) {

        String METHOD_NAME = "AddMultipleSchedule";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        String _to = "<string>" + TextUtils.join("</string><string>", to) + "</string>";
        String _text = "<string>" + TextUtils.join("</string><string>", text) + "</string>";
        String _date = "<dateTime>" + TextUtils.join("</dateTime><dateTime>", scheduleDateTime) + "</dateTime>";
        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("to", _to);
        request.addProperty("text", _text);
        request.addProperty("isflash", String.valueOf(isFlash));
        request.addProperty("scheduleDateTime", _date);
        request.addProperty("period", period);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SCHEDULE_URL, SOAP_ACTION, request);
    }

    public void AddNewUsance(String to, String from, String text, boolean isFlash, int countrepeat,
                               Date scheduleEndDateTime, String periodType) {

        String METHOD_NAME = "AddNewUsance";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("to", to);
        request.addProperty("text", text);
        request.addProperty("isflash", String.valueOf(isFlash));
        request.addProperty("countrepeat", countrepeat);
        request.addProperty("scheduleEndDateTime", scheduleEndDateTime);
        request.addProperty("periodType", periodType);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SCHEDULE_URL, SOAP_ACTION, request);
    }

    public void GetScheduleStatus(int scheduleId) {

        String METHOD_NAME = "GetScheduleStatus";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("scheduleId", scheduleId);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SCHEDULE_URL, SOAP_ACTION, request);
    }

    public void RemoveSchedule(int scheduleId) {

        String METHOD_NAME = "RemoveSchedule";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("scheduleId", scheduleId);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(SCHEDULE_URL, SOAP_ACTION, request);
    }



    //voice webservice
    public void GetSendSMSWithSpeechTextStatus(long recId) {

        String METHOD_NAME = "GetSendSMSWithSpeechTextStatus";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("recId", recId);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(VOICE_URL, SOAP_ACTION, request);
    }

    public void SendBulkSpeechText(String title, String body, String receivers, String DateToSend, int repeatCount) {

        String METHOD_NAME = "SendBulkSpeechText";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("title", title);
        request.addProperty("body", body);
        request.addProperty("receivers", receivers);
        request.addProperty("DateToSend", DateToSend);
        request.addProperty("repeatCount", repeatCount);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(VOICE_URL, SOAP_ACTION, request);
    }

    public void SendBulkVoiceSMS(String title, int voiceFileId, String receivers, String DateToSend, int repeatCount) {

        String METHOD_NAME = "SendBulkVoiceSMS";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("title", title);
        request.addProperty("voiceFileId", voiceFileId);
        request.addProperty("receivers", receivers);
        request.addProperty("DateToSend", DateToSend);
        request.addProperty("repeatCount", repeatCount);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(VOICE_URL, SOAP_ACTION, request);
    }

    public void SendSMSWithSpeechText(String smsBody, String speechBody, String from, String to) {

        String METHOD_NAME = "SendSMSWithSpeechText";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("to", to);
        request.addProperty("smsBody", smsBody);
        request.addProperty("speechBody", speechBody);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(VOICE_URL, SOAP_ACTION, request);
    }

    public void SendSMSWithSpeechTextBySchduleDate(String smsBody, String speechBody, String from, String to,
                                                     Date scheduleDate) {

        String METHOD_NAME = "SendSMSWithSpeechTextBySchduleDate";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("from", from);
        request.addProperty("to", to);
        request.addProperty("smsBody", smsBody);
        request.addProperty("speechBody", speechBody);
        request.addProperty("scheduleDate", scheduleDate);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(VOICE_URL, SOAP_ACTION, request);
    }

    public void UploadVoiceFile(String title, String base64StringFile) {

        String METHOD_NAME = "UploadVoiceFile";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        //Use this to add parameters
        request.addProperty("username", Username);
        request.addProperty("password", Password);
        request.addProperty("title", title);
        request.addProperty("base64StringFile", base64StringFile);

        String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        getXMLResult(VOICE_URL, SOAP_ACTION, request);
    }

}



class MPTaskParams{
    String url;
    String soapAction;
    SoapObject request;

    MPTaskParams(String _url, String _action, SoapObject _req){
        url = _url;
        soapAction = _action;
        request = _req;
    }
}