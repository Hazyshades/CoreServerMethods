package org.example.linux;

import org.example.ResponseData;
import org.example.helpers.Data;
import org.example.helpers.Headers;
import org.example.helpers.RequestBodies;
import org.example.helpers.Urls;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Withdraw {

    public static void main(String[] args) throws Exception {
        // Получаем результат запроса на создание Approval Cycle
        ResponseData response = createApprovalCycle(GetSessionID_LINUX.getSessionID(), Data.regCardIdGREEN);

        // Извлечение значения approvalCycleRowId из XML
        String approvalCycleRowId = extractApprovalCycleRowId(response.getResponseBody());
        System.out.println("Approval Cycle Row ID: " + approvalCycleRowId);
    }

    // Метод для создания A pproval Cycle
    public static ResponseData createApprovalCycle(String sessionID, String regCardId) throws Exception {
        URL url = new URL(Urls.GREEN_5050);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Установка метода и заголовков
        Headers.configureConnection(connection);

        // Отправка запроса
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(RequestBodies.createApprovalCycle_regCardId(sessionID, regCardId).getBytes("UTF-8"));
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

    // Метод для извлечения approvalCycleRowId из XML
    public static String extractApprovalCycleRowId(String responseBody) throws Exception {
        // Удаление BOM и очистка XML от нежелательных символов
        responseBody = removeBOM(responseBody);
        String cleanedXML = cleanXML(responseBody);
        System.out.println("Cleaned XML: " + cleanedXML);

        // Парсинг основного XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(cleanedXML.getBytes(StandardCharsets.UTF_8));
        Document document = builder.parse(inputStream);
        document.getDocumentElement().normalize();

        // Извлечение содержимого тега <result>
        NodeList resultList = document.getElementsByTagName("result");
        if (resultList.getLength() > 0) {
            String resultXML = resultList.item(0).getTextContent().trim();

            // Замена HTML-сущностей на символы XML
            resultXML = resultXML.replaceAll("&lt;", "<")
                    .replaceAll("&gt;", ">")
                    .replaceAll("&amp;", "&")
                    .replaceAll("&quot;", "\"");

            System.out.println("Decoded result XML: " + resultXML);

            // Удаление всех XML-деклараций
            resultXML = removeMultipleXMLDeclarations(resultXML);

            // Парсинг вложенного XML
            ByteArrayInputStream resultInputStream = new ByteArrayInputStream(resultXML.getBytes(StandardCharsets.UTF_8));
            Document resultDocument = builder.parse(resultInputStream);
            resultDocument.getDocumentElement().normalize();

            // Извлечение значения из тега <Value>
            NodeList valueList = resultDocument.getElementsByTagName("Value");
            if (valueList.getLength() > 0) {
                Element valueElement = (Element) valueList.item(0);
                return valueElement.getTextContent();
            } else {
                throw new Exception("Value tag not found in the result XML.");
            }
        } else {
            throw new Exception("Result tag not found in the main XML.");
        }
    }

    public static String removeMultipleXMLDeclarations(String xml) {
        // Удаление всех XML-деклараций
        return xml.replaceAll("(?s)\\<\\?xml[^\\?]*\\?\\>", "");
    }

    public static String cleanXML(String xml) {
        // Удаление всех символов до первого <, включая BOM и пробелы
        return xml.replaceAll("(?s)^[\\s\\xFEFF]*<", "<");
    }

    public static String removeBOM(String str) {
        if (str.startsWith("\uFEFF")) {
            str = str.substring(1);
        }
        return str;
    }


}