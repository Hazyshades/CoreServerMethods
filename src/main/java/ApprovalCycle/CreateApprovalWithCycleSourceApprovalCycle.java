package ApprovalCycle;

import helpers.Headers;
import helpers.RequestBodies;
import helpers.ResponseData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CreateApprovalWithCycleSourceApprovalCycle {

    public static ResponseData createApprovalCycleWithSourceApprovalCycleRowID(
            String sessionID,
            String UrlServer,
            String regCardId,
            String sourceApprovalCycleRowID,
            Boolean copyAppendix) throws Exception {
        URL url = new URL(UrlServer);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        Headers.configureConnection(connection);

        try (OutputStream outputStream = connection.getOutputStream()) {
            String requestBody = RequestBodies.createApprovalCycle_regCardId_sourceApprovalCycleRowId_copyAppendix(sessionID, regCardId, sourceApprovalCycleRowID, copyAppendix);
            outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
        }

        return getResponseData(connection);
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

