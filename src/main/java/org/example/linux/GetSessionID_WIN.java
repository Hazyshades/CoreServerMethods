package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import static org.example.helpers.Urls.WIN_CoreAuto;

public class GetSessionID_WIN {

    public static void main(String[] args) {
        try {
            // Получаем sessionID
            String sessionID = getSessionID_WIN();
            if (sessionID != null) {
                System.out.println("Session ID: " + sessionID);
            } else {
                System.out.println("Session ID not found in the response.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для получения sessionID с Basic Authentication
    public static String getSessionID_WIN() throws Exception {
        // URL для аутентификации
        URL url = new URL(WIN_CoreAuto);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Установка метода и заголовков
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setRequestProperty("Accept", "text/xml");
        connection.setRequestProperty("SOAPAction", "http://www.digdes.com/docsvision/SessionLoginEx");
        connection.setRequestProperty("Cookie", "DVCLIENT=117PC0033_11e11798");
        connection.setRequestProperty("User-Agent", "DVHttpClient");

        // Добавление Basic Authentication
        String userCredentials = "DIGDES\\sbrf01:P@ssw0rd01";
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(userCredentials.getBytes());
        connection.setRequestProperty("Authorization", basicAuth);

        connection.setDoOutput(true);

        // SOAP-тело для SessionLoginEx
        String soapRequest =
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" +
                        "<soap:Body>" +
                        "<SessionLoginEx xmlns=\"http://www.digdes.com/docsvision/\">" +
                        "<baseName></baseName>" +
                        "<sessionSettings>&lt;Settings&gt;...&lt;/Settings&gt;</sessionSettings>" +
                        "<flags>15</flags>" +
                        "</SessionLoginEx>" +
                        "</soap:Body>" +
                        "</soap:Envelope>";

        // Отправка запроса
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(soapRequest.getBytes("UTF-8"));
        }

        // Чтение ответа
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        // Парсинг ответа для получения sessionID
        String responseStr = response.toString();
        int start = responseStr.indexOf("<sessionID>") + "<sessionID>".length();
        int end = responseStr.indexOf("</sessionID>");
        if (start > "<sessionID>".length() && end > start) {
            return responseStr.substring(start, end);
        }

        return null; // Если sessionID не найден
    }
}
