public class RestClient {

  private final String endpoint = "https://rest.payamak-panel.com/api/SendSMS/";
  private final String sendOp = "SendSMS";

  private final String getDeliveryOp = "GetDeliveries2";
  private final String getMessagesOp = "GetMessages";
  private final String getCreditOp = "GetCredit";
  private final String getBasePriceOp = "GetBasePrice";
  private final String getUserNumbersOp = "GetUserNumbers";

  private String UserName;
  private String Password;

  public RestClient(String username, String password)  {
      UserName = username;
      Password = password;
  }

  public String Send(String to, String from, String message, boolean isflash) throws IOException {

      Hashtable<String, String> values = new Hashtable<>();
      values.put("username", UserName);
      values.put("password", Password);
      values.put("to" , to);
      values.put( "from" , from);
      values.put("text" , message);
      values.put("isFlash" , String.valueOf(isflash));

      URL url = new URL(endpoint + sendOp);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");

      StringBuilder result = new StringBuilder();
      String line;

      try {
          conn.setDoOutput(true);
          conn.setChunkedStreamingMode(0);

          //consider encoding
          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
          writer.write(getPostParamString(values));
          writer.flush();
          writer.close();

          //you can deserialize response as it is json
          BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          while ((line = r.readLine()) != null) {
              result.append(line).append('\n');
          }

      } finally {
          conn.disconnect();
      }

      return String.valueOf(result);
  }

  public String GetDelivery(long recid) throws IOException {

      Hashtable<String, String> values = new Hashtable<>();
      values.put("username", UserName);
      values.put("password", Password);
      values.put("recID" , String.valueOf(recid));

      URL url = new URL(endpoint + getDeliveryOp);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");

      StringBuilder result = new StringBuilder();
      String line;

      try {
          conn.setDoOutput(true);
          conn.setChunkedStreamingMode(0);

          //consider encoding
          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
          writer.write(getPostParamString(values));
          writer.flush();
          writer.close();

          //you can deserialize response as it is json
          BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          while ((line = r.readLine()) != null) {
              result.append(line).append('\n');
          }

      } finally {
          conn.disconnect();
      }

      return String.valueOf(result);
  }


  public String GetMessages(int location, String from, String index, int count) throws IOException {

      Hashtable<String, String> values = new Hashtable<>();
      values.put("username", UserName);
      values.put("password", Password);
      values.put("Location", String.valueOf(location));
      values.put("From", from);
      values.put("Index", index);
      values.put("Count", String.valueOf(count));

      URL url = new URL(endpoint + getMessagesOp);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");

      StringBuilder result = new StringBuilder();
      String line;

      try {
          conn.setDoOutput(true);
          conn.setChunkedStreamingMode(0);

          //consider encoding
          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
          writer.write(getPostParamString(values));
          writer.flush();
          writer.close();

          //you can deserialize response as it is json
          BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          while ((line = r.readLine()) != null) {
              result.append(line).append('\n');
          }

      } finally {
          conn.disconnect();
      }

      return String.valueOf(result);
  }

  public String GetCredit() throws IOException {

      Hashtable<String, String> values = new Hashtable<>();
      values.put("username", UserName);
      values.put("password", Password);

      URL url = new URL(endpoint + getCreditOp);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");

      StringBuilder result = new StringBuilder();
      String line;

      try {
          conn.setDoOutput(true);
          conn.setChunkedStreamingMode(0);

          //consider encoding
          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
          writer.write(getPostParamString(values));
          writer.flush();
          writer.close();

          //you can deserialize response as it is json
          BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          while ((line = r.readLine()) != null) {
              result.append(line).append('\n');
          }

      } finally {
          conn.disconnect();
      }

      return String.valueOf(result);
  }

  public String GetBasePrice() throws IOException {

      Hashtable<String, String> values = new Hashtable<>();
      values.put("username", UserName);
      values.put("password", Password);

      URL url = new URL(endpoint + getBasePriceOp);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");

      StringBuilder result = new StringBuilder();
      String line;

      try {
          conn.setDoOutput(true);
          conn.setChunkedStreamingMode(0);

          //consider encoding
          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
          writer.write(getPostParamString(values));
          writer.flush();
          writer.close();

          //you can deserialize response as it is json
          BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          while ((line = r.readLine()) != null) {
              result.append(line).append('\n');
          }

      } finally {
          conn.disconnect();
      }

      return String.valueOf(result);
  }

  public String GetUserNumbers() throws IOException {

      Hashtable<String, String> values = new Hashtable<>();
      values.put("username", UserName);
      values.put("password", Password);

      URL url = new URL(endpoint + getUserNumbersOp);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");

      StringBuilder result = new StringBuilder();
      String line;

      try {
          conn.setDoOutput(true);
          conn.setChunkedStreamingMode(0);

          //consider encoding
          OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
          writer.write(getPostParamString(values));
          writer.flush();
          writer.close();

          //you can deserialize response as it is json
          BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          while ((line = r.readLine()) != null) {
              result.append(line).append('\n');
          }

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
}
