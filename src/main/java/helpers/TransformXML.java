package helpers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
public class TransformXML {
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
                System.out.println(valueElement.getTextContent());
                return valueElement.getTextContent();
            } else {
                throw new Exception("Value tag not found in the result XML.");
            }
        } else {
            throw new Exception("Result tag not found in the main XML.");
        }
    }

    // Удаление всех XML-деклараций
    public static String removeMultipleXMLDeclarations(String xml) {
        return xml.replaceAll("(?s)\\<\\?xml[^\\?]*\\?\\>", "");
    }

    // Удаление всех символов до первого <, включая BOM и пробелы
    public static String cleanXML(String xml) {
        return xml.replaceAll("(?s)^[\\s\\xFEFF]*<", "<");
    }

    // Удаление BOM
    public static String removeBOM(String str) {
        if (str.startsWith("\uFEFF")) {
            str = str.substring(1);
        }
        return str;
    }
}
