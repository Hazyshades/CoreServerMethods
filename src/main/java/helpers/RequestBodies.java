package helpers;

public class RequestBodies {

    public static String getSessionID_LINUX() {
        return "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                        "xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
                        "<soap:Body>" +
                        "<SessionLoginEx xmlns=\"http://www.digdes.com/docsvision/\">" +
                        "<baseName></baseName>" +
                        "<sessionSettings>&lt;Settings&gt;...&lt;/Settings&gt;</sessionSettings>" +
                        "<flags>15</flags>" +
                        "</SessionLoginEx>" +
                        "</soap:Body>" +
                        "</soap:Envelope>";
    }

    public static String createApprovalCycle_regCardId(String sessionID, String regCardId) {
        return "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                "xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
                "<soap:Body>" +
                "<ExtensionExecuteMethod xmlns=\"http://www.digdes.com/docsvision/\">" +
                "<sessionID>" + sessionID + "</sessionID>" +
                "<methodCall>" +
                "&lt;MethodCall ObjectName=\"DDMExtension\" MethodName=\"CreateApprovalCycle\"&gt;" +
                "&lt;Parameter Name=\"regCardId\" Type=\"uniqueidentifier\" " +
                "xmlns:dt=\"urn:schemas-microsoft-com:datatypes\" dt:dt=\"string\"&gt;" +
                regCardId +
                "&lt;/Parameter&gt;" +
                "&lt;/MethodCall&gt;" +
                "</methodCall>" +
                "<compressed>false</compressed>" +
                "</ExtensionExecuteMethod>" +
                "</soap:Body>" +
                "</soap:Envelope>";

    }

    public static String createApprovalCycle_regCardId_sourceApprovalCycleRowId_copyAppendix(String sessionID, String regCardId, String sourceApprovalCycleRowId, boolean copyAppendix) {
        return "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                "xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
                "<soap:Body>" +
                "<ExtensionExecuteMethod xmlns=\"http://www.digdes.com/docsvision/\">" +
                "<sessionID>" + sessionID + "</sessionID>" +
                "<methodCall>" +
                "&lt;MethodCall ObjectName=\"DDMExtension\" MethodName=\"CreateApprovalCycle\"&gt;" +
                "&lt;Parameter Name=\"regCardId\" Type=\"uniqueidentifier\" " +
                "xmlns:dt=\"urn:schemas-microsoft-com:datatypes\" dt:dt=\"string\"&gt;" +
                regCardId +
                "&lt;/Parameter&gt;" +
                "&lt;Parameter Name=\"templateId\" Type=\"uniqueidentifier\" " +
                "xmlns:dt=\"urn:schemas-microsoft-com:datatypes\" dt:dt=\"string\"&gt;" +
                sourceApprovalCycleRowId +
                "&lt;/Parameter&gt;" +
                "&lt;Parameter Name=\"copyAppendix\" Type=\"bool\" " +
                "xmlns:dt=\"urn:schemas-microsoft-com:datatypes\" dt:dt=\"boolean\"&gt;" +
                (copyAppendix ? "1" : "0") +
                "&lt;/Parameter&gt;" +
                "&lt;/MethodCall&gt;" +
                "</methodCall>" +
                "<compressed>false</compressed>" +
                "</ExtensionExecuteMethod>" +
                "</soap:Body>" +
                "</soap:Envelope>";
    }



    public static String createApprovalCycle_regCardId_templateId_copyAppendix(String sessionID, String regCardId, String templateId, boolean copyAppendix) {
        return "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                "xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
                "<soap:Body>" +
                "<ExtensionExecuteMethod xmlns=\"http://www.digdes.com/docsvision/\">" +
                "<sessionID>" + sessionID + "</sessionID>" +
                "<methodCall>" +
                "&lt;MethodCall ObjectName=\"DDMExtension\" MethodName=\"CreateApprovalCycle\"&gt;" +
                "&lt;Parameter Name=\"regCardId\" Type=\"uniqueidentifier\" " +
                "xmlns:dt=\"urn:schemas-microsoft-com:datatypes\" dt:dt=\"string\"&gt;" +
                regCardId +
                "&lt;/Parameter&gt;" +
                "&lt;Parameter Name=\"templateId\" Type=\"uniqueidentifier\" " +
                "xmlns:dt=\"urn:schemas-microsoft-com:datatypes\" dt:dt=\"string\"&gt;" +
                templateId +
                "&lt;/Parameter&gt;" +
                "&lt;Parameter Name=\"copyAppendix\" Type=\"bool\" " +
                "xmlns:dt=\"urn:schemas-microsoft-com:datatypes\" dt:dt=\"boolean\"&gt;" +
                (copyAppendix ? "1" : "0") +
                "&lt;/Parameter&gt;" +
                "&lt;/MethodCall&gt;" +
                "</methodCall>" +
                "<compressed>false</compressed>" +
                "</ExtensionExecuteMethod>" +
                "</soap:Body>" +
                "</soap:Envelope>";
    }



}


