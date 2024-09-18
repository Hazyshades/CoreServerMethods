package ApprovalCycle;

import helpers.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CreateApprovalCycleWithTemplate {

    public static String createApprovalCycleWithTemplate(
            String sessionID,
            String UrlServer,
            String regCardId,
            String templateId,
            Boolean copyAppendix) throws Exception {
        URL url = new URL(UrlServer);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        Headers.configureConnection(connection);

        try (OutputStream outputStream = connection.getOutputStream()) {
            String requestBody = RequestBodies.createApprovalCycle_regCardId_templateId_copyAppendix(sessionID, regCardId, templateId, copyAppendix);
            outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
        }

        ResponseData response = getResponseData(connection);

        return TransformXML.extractApprovalCycleRowId(response.getResponseBody());
    }

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
