package org.example.helpers;
import java.net.HttpURLConnection;

public class DefaultHeaders {

    public static void setDefaultHeaders(HttpURLConnection connection, String soapAction, String cookie) {
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setRequestProperty("Accept", "text/xml");
        connection.setRequestProperty("SOAPAction", soapAction);
        connection.setRequestProperty("Cookie", cookie);
        connection.setRequestProperty("User-Agent", "DVHttpClient");
    }
}
