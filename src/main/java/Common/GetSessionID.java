package Common;

import helpers.Headers;
import helpers.RequestBodies;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetSessionID {

    // Метод для получения sessionID
    public static String getSessionID(String Url) throws Exception {
        // URL для аутентификации
        URL url = new URL(Url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //Ставим хедер
        Headers.configureConnectionSession(connection);

        // SOAP-тело для SessionLoginEx
        String soapRequest = RequestBodies.getSessionID();

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

        return null;
    }
}
