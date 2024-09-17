package helpers;

import java.net.HttpURLConnection;
import java.util.Base64;

public class Headers {
    // Константы для HTTP-запросов
    private static final String REQUEST_METHOD = "POST";
    private static final String CONTENT_TYPE = "text/xml; charset=utf-8";
    private static final String ACCEPT = "text/xml";
    private static final String SOAP_ACTION= "http://www.digdes.com/docsvision/ExtensionExecuteMethod";
    private static final String SOAP_ACTION_SESSION = "http://www.digdes.com/docsvision/SessionLoginEx";
    private static final String CONNECTION = "Keep-Alive";
    private static final String COOKIE = "DVCLIENT=117PC0033_125e1798";
    private static final String USER_AGENT = "DVHttpClient";
    private static final String AUTH_CREDENTIALS = "DIGDES\\sbrf01:P@ssw0rd01"; // Замените на ваши учетные данные


    public static void configureConnection(HttpURLConnection connection) throws Exception {
        connection.setRequestMethod(REQUEST_METHOD);
        connection.setRequestProperty("Content-Type", CONTENT_TYPE);
        connection.setRequestProperty("Accept", ACCEPT);
        connection.setRequestProperty("SOAPAction", SOAP_ACTION);
        connection.setRequestProperty("Connection", CONNECTION);
        connection.setRequestProperty("Cookie", COOKIE);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Authorization", getBasicAuthHeader());
        connection.setDoOutput(true);
    }

    public static void configureConnectionSession(HttpURLConnection connection) throws Exception {
        connection.setRequestMethod(REQUEST_METHOD);
        connection.setRequestProperty("Content-Type", CONTENT_TYPE);
        connection.setRequestProperty("Accept", ACCEPT);
        connection.setRequestProperty("SOAPAction", SOAP_ACTION_SESSION);
        connection.setRequestProperty("Connection", CONNECTION);
        connection.setRequestProperty("Cookie", COOKIE);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Authorization", getBasicAuthHeader());
        connection.setDoOutput(true);

    }

    private static String getBasicAuthHeader() {
        return "Basic " + Base64.getEncoder().encodeToString(Headers.AUTH_CREDENTIALS.getBytes());
    }
}
