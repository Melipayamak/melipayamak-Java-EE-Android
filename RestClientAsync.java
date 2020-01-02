package ir.mp.java.mpjava;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;

public class RestClientAsync extends AsyncTask<MPRestTaskParams, Integer, String> {

    private final String endpoint = "https://rest.payamak-panel.com/api/SendSMS/";

    private final String sendOp = "SendSMS";
    private final String sendByBaseNumber="BaseServiceNumber";
    private final String getDeliveryOp = "GetDeliveries2";
    private final String getMessagesOp = "GetMessages";
    private final String getCreditOp = "GetCredit";
    private final String getBasePriceOp = "GetBasePrice";
    private final String getUserNumbersOp = "GetUserNumbers";

    private String UserName;
    private String Password;


    public RestClientAsync(String username, String password)  {
        UserName = username;
        Password = password;
    }

    @Override
    protected String doInBackground(MPRestTaskParams... params) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) params[0].url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        StringBuilder result = new StringBuilder();
        String line;

        try {
            conn.setDoOutput(true);
            conn.setChunkedStreamingMode(0);

            //consider encoding
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(getPostParamString(params[0].values));
            writer.flush();
            writer.close();

            //you can deserialize response as it is json
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = r.readLine()) != null) {
                result.append(line).append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return String.valueOf(result);
    }

    private static String getPostParamString(Hashtable<String, String> params) {
        if(params.size() == 0)
            return "";

        StringBuffer buf = new StringBuffer();
        Enumeration<String> keys = params.keys();
        while(keys.hasMoreElements()) {
            buf.append(buf.length() == 0 ? "" : "&");
            String key = keys.nextElement();
            buf.append(key).append("=").append(params.get(key));
        }
        return buf.toString();
    }







    public void Send(String to, String from, String message, boolean isflash) throws IOException {

        Hashtable<String, String> values = new Hashtable<>();
        values.put("username", UserName);
        values.put("password", Password);
        values.put("to" , to);
        values.put( "from" , from);
        values.put("text" , message);
        values.put("isFlash" , String.valueOf(isflash));

        URL url = new URL(endpoint + sendOp);
        execute(new MPRestTaskParams[]{new MPRestTaskParams(url, values)});
    }

    public void SendByBaseNumber(String text, String to, long bodyId) throws IOException {

        Hashtable<String, String> values = new Hashtable<>();
        values.put("username", UserName);
        values.put("password", Password);
        values.put("to" , to);
        values.put("text" , text);
        values.put("bodyId" , String.valueOf(bodyId));

        URL url = new URL(endpoint + sendByBaseNumber);
        execute(new MPRestTaskParams[]{new MPRestTaskParams(url, values)});
    }

    public void GetDelivery(long recid) throws IOException {

        Hashtable<String, String> values = new Hashtable<>();
        values.put("username", UserName);
        values.put("password", Password);
        values.put("recID" , String.valueOf(recid));

        URL url = new URL(endpoint + getDeliveryOp);
        execute(new MPRestTaskParams[]{new MPRestTaskParams(url, values)});
    }


    public void GetMessages(int location, String from, String index, int count) throws IOException {

        Hashtable<String, String> values = new Hashtable<>();
        values.put("username", UserName);
        values.put("password", Password);
        values.put("Location", String.valueOf(location));
        values.put("From", from);
        values.put("Index", index);
        values.put("Count", String.valueOf(count));

        URL url = new URL(endpoint + getMessagesOp);
        execute(new MPRestTaskParams[]{new MPRestTaskParams(url, values)});
    }

    public void GetCredit() throws IOException {

        Hashtable<String, String> values = new Hashtable<>();
        values.put("username", UserName);
        values.put("password", Password);

        URL url = new URL(endpoint + getCreditOp);
        execute(new MPRestTaskParams[]{new MPRestTaskParams(url, values)});
    }

    public void GetBasePrice() throws IOException {

        Hashtable<String, String> values = new Hashtable<>();
        values.put("username", UserName);
        values.put("password", Password);

        URL url = new URL(endpoint + getBasePriceOp);
        execute(new MPRestTaskParams[]{new MPRestTaskParams(url, values)});
    }

    public void GetUserNumbers() throws IOException {

        Hashtable<String, String> values = new Hashtable<>();
        values.put("username", UserName);
        values.put("password", Password);

        URL url = new URL(endpoint + getUserNumbersOp);
        execute(new MPRestTaskParams[]{new MPRestTaskParams(url, values)});
    }


}


class MPRestTaskParams{
    URL url;
    Hashtable<String, String> values;

    MPRestTaskParams(URL _url, Hashtable<String, String> _values){
        url = _url;
        values = _values;
    }
}