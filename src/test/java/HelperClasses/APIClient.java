package HelperClasses;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class APIClient {


    public APIClient() {

    }

    public static void runTest() throws Exception{
        String url = "https://restful-booker.herokuapp.com/";

        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.basePath = url;

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/string");
        request.body("admin");
        request.body("password123");

        Response response = request.post();

        assertEquals(200, response.getStatusCode());
    }

   /* public Object sentPOST(String uri, Object data) {
        Map m = new HashMap();
        m.put("username", "admin");
        m.put("password", "password123");

        return this.sendRequest("POST", uri, data);
    }

    private Object sendRequest(String method, String url, Object data) throws IOException, APIException {
        if (method == "POST") {
            if (data != null) {
                byte[] block = JSONValue.toJSONString(data).getBytes();

                co
            }
        }
    }*/
}
