package ApprivakCycle;

import helpers.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CreateApprovalCycle {

    // Метод для создания Approval Cycle
    public static String createApprovalCycle(String sessionID, String regCardId) throws Exception {
        URL url = new URL(Urls.GREEN_5050);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Установка метода и заголовков
        Headers.configureConnection(connection);

        // Отправка запроса
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(RequestBodies.createApprovalCycle_regCardId(sessionID, regCardId).getBytes(StandardCharsets.UTF_8));
        }
        ResponseData response = getResponseData(connection);
       // System.out.println(TransformXML.extractApprovalCycleRowId(response.getResponseBody()));
        // Получение и возврат данных ответа
        return TransformXML.extractApprovalCycleRowId(response.getResponseBody());
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
