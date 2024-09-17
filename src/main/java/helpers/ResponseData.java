package helpers;

public class ResponseData {
    private final int responseCode;
    private final String responseBody;

    public ResponseData(int responseCode, String responseBody) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
