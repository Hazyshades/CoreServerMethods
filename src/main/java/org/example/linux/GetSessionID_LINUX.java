package org.example.linux;

import org.example.helpers.Headers;
import org.example.helpers.RequestBodies;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.example.helpers.Urls.GREEN_5050;

public class GetSessionID_LINUX {

    public static void main(String[] args) {
        try {
            // Получаем sessionID
            String sessionID = getSessionID();
            if (sessionID != null) {
                System.out.println("Session ID: " + sessionID);
            } else {
                System.out.println("Session ID not found in the response.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для получения sessionID
    public static String getSessionID() throws Exception {
        // URL для аутентификации
        URL url = new URL(GREEN_5050);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Установка метода и заголовков
        Headers.configureConnectionSession(connection);

        // SOAP-тело для SessionLoginEx
        String soapRequest = RequestBodies.getSessionID_LINUX();

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
