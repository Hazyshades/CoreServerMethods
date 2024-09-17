package ApprivakCycle;

import helpers.*;
import Common.GetSessionID;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static helpers.Urls.GREEN_5050;

public class CreateApprovalWithCycleSourceApprovalCycle {

    public static void main(String[] args) throws Exception {
        // Получаем результат запроса на создание Approval Cycle с использованием шаблона
        ResponseData response = createApprovalCycleWithSourceApprovalCycleRowID(
                GetSessionID.getSessionID(GREEN_5050),
                Data.regCardIdGREEN, CreateApprovalCycle.createApprovalCycle(
                        GetSessionID.getSessionID(GREEN_5050),
                        Data.regCardIdGREEN),
                true           // Пример использования copyAppendix
        );

        // Извлечение значения approvalCycleRowId из XML
        String approvalCycleRowId = TransformXML.extractApprovalCycleRowId(response.getResponseBody());
        System.out.println("Approval Cycle Row ID: " + approvalCycleRowId);
    }

    // Метод для создания Approval Cycle с использованием шаблона
    public static ResponseData createApprovalCycleWithSourceApprovalCycleRowID(
            String sessionID,
            String regCardId,
            String sourceApprovalCycleRowID,
            Boolean copyAppendix) throws Exception {
        URL url = new URL(Urls.GREEN_5050);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Установка метода и заголовков
        Headers.configureConnection(connection);

        // Отправка запроса с дополнительными параметрами templateId и copyAppendix
        try (OutputStream outputStream = connection.getOutputStream()) {
            String requestBody = RequestBodies.createApprovalCycle_regCardId_sourceApprovalCycleRowId_copyAppendix(sessionID, regCardId, sourceApprovalCycleRowID, copyAppendix);
            outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
        }

        // Получение и возврат данных ответа
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

