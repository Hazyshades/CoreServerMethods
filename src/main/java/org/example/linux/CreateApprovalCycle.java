package org.example.linux;

import org.example.ResponseData;
import org.example.helpers.Data;
import org.example.helpers.Headers;
import org.example.helpers.RequestBodies;
import org.example.helpers.Urls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateApprovalCycle {

    public static void main(String[] args) throws Exception {
        // Получаем результат запроса на создание Approval Cycle
        ResponseData response = createApprovalCycle(GetSessionID_LINUX.getSessionID(), Data.regCardIdGREEN);
      //  ResponseData response = createApprovalCycleWithTemplate(GetSessionID_LINUX.getSessionID(), Data.regCardIdGREEN,
             //   Data.templateId, true);

        // Выводим код ответа и тело ответа
        System.out.println("Response Code: " + response.getResponseCode());
        System.out.println("Response Body: " + response.getResponseBody());
    }

    // Метод для создания Approval Cycle с параметрами по умолчанию
    public static ResponseData createApprovalCycle(String sessionID, String regCardId) throws Exception {
        URL url = new URL(Urls.GREEN_5050);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Установка метода и заголовков
        Headers.configureConnection(connection);

        // Отправка запроса с параметрами по умолчанию
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(RequestBodies.createApprovalCycle_regCardId(
                    sessionID, regCardId).getBytes("UTF-8"));
        }

        // Получение и возврат данных ответа
        return getResponseData(connection);
    }

    // Метод для создания Approval Cycle с дополнительными параметрами
    public static ResponseData createApprovalCycleWithTemplate(String sessionID, String regCardId, String templateId, boolean copyAppendix)
            throws Exception {
        URL url = new URL(Urls.GREEN_5050);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Установка метода и заголовков
        Headers.configureConnection(connection);

        // Отправка запроса с параметрами шаблона и сопроводительных файлов
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(RequestBodies.createApprovalCycle_regCardId_templateId_copyAppendix(
                    sessionID, regCardId, templateId, copyAppendix).getBytes("UTF-8"));
        }

        // Получение и возврат данных ответа
        System.out.println(connection.getDate());
        return getResponseData(connection);
    }

    // Метод для получения данных ответа от сервера
    private static ResponseData getResponseData(HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        StringBuilder responseBody = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseBody.append(inputLine);
            }
        }

        return new ResponseData(responseCode, responseBody.toString());
    }
}
