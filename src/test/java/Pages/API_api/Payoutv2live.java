package Pages.API_api;

import Utils.PropFileHandler;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.Random;


public class Payoutv2live {
    static PropFileHandler propFileHandler = new PropFileHandler();
    static String token;
    static String txnId;
    static String transferId;
    static String refId;
    String apiBaseURI = propFileHandler.readProperty("apiBaseURI");

    public void getToken() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        request.header("Content-Type", "application/json");
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("salt", "B9TDPIcBfviCTrH0qLvLPVz7kKaEzQE9");

        request.body(requestParams.toString());
        Response response = request.post("/authorize");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "SUCCESS");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "Token generated");
        token = jsonPathEvaluator.get("data.token");

    }

    public void getBalance() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);

        request.body(requestParams.toString());
        Response response = request.post("/account/getBalance");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "SUCCESS");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "Balance for the account");


    }

    public void test() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random randNum = new Random();
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum.nextInt(1000));
        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
//        requestParams.put("vpa", token);
//        requestParams.put("bankAccount", "794578784574");
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");

        request.body(requestParams.toString());
        System.out.println(requestParams.toString());
        Response response2 = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response2.getBody().asString());
        Assert.assertEquals(response2.getStatusCode(), 200);

        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String status2 = jsonPathEvaluator2.get("status");
        Assert.assertEquals(status2, "ERROR");
        String message2 = jsonPathEvaluator2.get("message");
        Assert.assertEquals(message2, "bankAccount/vpa can't be null");
    }

    public void addBeneficiaryVerifyRequired() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random randNum = new Random();
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
//        requestParams.put("beneId", "BENE_71987" + randNum.nextInt(1000));
        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("bankAccount", "794578784574" + randNum.nextInt(1000));
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");

        request.body(requestParams.toString());
        Response response = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "This field is required.");

        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum.nextInt(1000));
//        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("bankAccount", "794578784574" + randNum.nextInt(1000));
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");

        request.body(requestParams.toString());
        response = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        jsonPathEvaluator = response.jsonPath();
        status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "This field is required.");


        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum.nextInt(1000));
        requestParams.put("name", "Siddharth");
//        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("bankAccount", "794578784574" + randNum.nextInt(1000));
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");

        request.body(requestParams.toString());
        response = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        jsonPathEvaluator = response.jsonPath();
        status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "This field is required.");


        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum.nextInt(1000));
        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
//        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("bankAccount", "794578784574" + randNum.nextInt(1000));
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");

        request.body(requestParams.toString());
        response = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

        jsonPathEvaluator = response.jsonPath();
        status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "This field is required.");


        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum.nextInt(1000));
        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
//        requestParams.put("address1", "ithum tower noida");
        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("bankAccount", "794578784574" + randNum.nextInt(1000));
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");


        request.body(requestParams.toString());
        response = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

        jsonPathEvaluator = response.jsonPath();
        status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "This field is required.");

        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum.nextInt(1000));
        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
//        requestParams.put("vpa", "siddharth@ybl");
//        requestParams.put("bankAccount", "794578784574"+randNum.nextInt(1000));
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");

        request.body(requestParams.toString());
        Response response2 = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response2.getBody().asString());
        Assert.assertEquals(response2.getStatusCode(), 200);

        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String status2 = jsonPathEvaluator2.get("status");
        Assert.assertEquals(status2, "ERROR");
        String message2 = jsonPathEvaluator2.get("message");
        Assert.assertEquals(message2, "bankAccount/vpa can't be null");

        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum.nextInt(1000));
        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
//        requestParams.put("vpa", "siddharth@ybl");
        requestParams.put("bankAccount", "794578784574" + randNum.nextInt(1000));
//        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");

        request.body(requestParams.toString());
        response = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

        jsonPathEvaluator = response.jsonPath();
        status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "bankAccount/vpa can't be null");


    }

    public void verifyAccNumberDuplicate() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random random = new Random();
        int randNum = random.nextInt(1000);
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum);
        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
//        requestParams.put("vpa", token);
        requestParams.put("bankAccount", "794578784574");
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");

        request.body(requestParams.toString());
        System.out.println(requestParams.toString());
        Response response2 = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response2.getBody().asString());
        Assert.assertEquals(response2.getStatusCode(), 200);

        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String status2 = jsonPathEvaluator2.get("status");
        Assert.assertEquals(status2, "ERROR");
        String message2 = jsonPathEvaluator2.get("message");
        Assert.assertEquals(message2, "Entered bankAccount number is already registered");

        requestParams.clear();

        request.body(requestParams.toString());
        System.out.println(requestParams.toString());
        response2 = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response2.getBody().asString());
        Assert.assertEquals(response2.getStatusCode(), 200);

        jsonPathEvaluator2 = response2.jsonPath();
        status2 = jsonPathEvaluator2.get("status");
        Assert.assertEquals(status2, "ERROR");
        message2 = jsonPathEvaluator2.get("message");
        Assert.assertEquals(message2, "Entered bankAccount number is already registered");

    }

    public void verifyBeneIdDuplicate() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random random = new Random();
        int randNum = random.nextInt(1000);
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum);
        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
//        requestParams.put("vpa", token);
        requestParams.put("bankAccount", "794578784574" + random.nextInt(1000));
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");

        request.body(requestParams.toString());
        System.out.println(requestParams.toString());
        Response response2 = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response2.getBody().asString());
        Assert.assertEquals(response2.getStatusCode(), 200);

        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String status2 = jsonPathEvaluator2.get("status");
        Assert.assertEquals(status2, "SUCCESS");
        String message2 = jsonPathEvaluator2.get("message");
        Assert.assertEquals(message2, "Beneficiary added successfully");

        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987" + randNum);
        requestParams.put("name", "Siddharth");
        requestParams.put("email", "siddharth@gmail.com");
        requestParams.put("phone", "7485687458");
        requestParams.put("address1", "ithum tower noida");
//        requestParams.put("vpa", token);
        requestParams.put("bankAccount", "794578784574" + random.nextInt(1000));
        requestParams.put("ifsc", "IDFC0NOIDAA");
        requestParams.put("address2", "Sector 62");


        request.body(requestParams.toString());
        System.out.println(requestParams.toString());
        response2 = request.post("/beneficiary/addBeneficiary");
        System.out.println("Response body: " + response2.getBody().asString());
        Assert.assertEquals(response2.getStatusCode(), 200);

        jsonPathEvaluator2 = response2.jsonPath();
        status2 = jsonPathEvaluator2.get("status");
        Assert.assertEquals(status2, "ERROR");
        message2 = jsonPathEvaluator2.get("message");
        Assert.assertEquals(message2, "Beneficiary Id already exist");

    }

    public void requestImps() {
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random random = new Random();
        int randNum = random.nextInt(10000);
        transferId = "test87654" + randNum;
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987372");
        requestParams.put("amount", "90");
        requestParams.put("transferId", transferId);
        requestParams.put("transferMode", "imps");


        request.body(requestParams.toString());
        Response response = request.post("/transfer/requestImps");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "SUCCESS");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "Transfer Initiated");
        refId = jsonPathEvaluator.get("data.referenceId");
        String transferIdResponse = jsonPathEvaluator.get("data.transferId");

        Assert.assertEquals(transferIdResponse, transferId);

    }
    public void getTransferStatus(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random random = new Random();
        int randNum = random.nextInt(10000);
//        transferId = "test87654" + randNum;
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("transferId", transferId);
        requestParams.put("referenceId", refId);

        System.out.println("Transfer id " +transferId);
        System.out.println("Ref id " +refId);

        request.body(requestParams.toString());
        Response response = request.post("/transfer/getTransferStatus");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "SUCCESS");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "Details of transfer with referenceId "+refId);
        refId = jsonPathEvaluator.get("data.referenceId");
        String transferIdResponse = jsonPathEvaluator.get("data.transfer.transferId");

        Assert.assertEquals(transferIdResponse, transferId);
    }

    public void verifyTransferIdNotExist(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random random = new Random();
        int randNum = random.nextInt(10000);
        transferId = "test87654" + randNum;
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("transferId", transferId+"8765467");
        requestParams.put("referenceId", "UA240701155003986002");


        request.body(requestParams.toString());
        Response response = request.post("/transfer/getTransferStatus");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "transferId/referenceId does not exist");

        requestParams.clear();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("transferId", "test202403381123");
        requestParams.put("referenceId", refId + "8765467");


        request.body(requestParams.toString());
         response = request.post("/transfer/getTransferStatus");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


         jsonPathEvaluator = response.jsonPath();
         status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
         message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "transferId/referenceId does not exist");


    }

    public void refrenceTransferIdcantBeNull(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random random = new Random();
        int randNum = random.nextInt(10000);
        transferId = "test87654" + randNum;
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("transferId", transferId+"8765467");
//        requestParams.put("referenceId", refId);


        request.body(requestParams.toString());
        Response response = request.post("/transfer/getTransferStatus");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "referenceId or transferId can't be null");

        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
//        requestParams.put("transferId", transferId+"8765467");
        requestParams.put("referenceId", "UA9876545678909876");

        request.body(requestParams.toString());
         response = request.post("/transfer/getTransferStatus");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


         jsonPathEvaluator = response.jsonPath();
         status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
         message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "referenceId or transferId can't be null");

    }
    public void verifyPayidNotNUll(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random random = new Random();
        int randNum = random.nextInt(10000);
        transferId = "test87654" + randNum;
        JSONObject requestParams = new JSONObject();
//        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987372");
        requestParams.put("amount", "90");
        requestParams.put("transferId", transferId);
        requestParams.put("transferMode", "imps");

        request.body(requestParams.toString());
        Response response = request.post("/transfer/requestImps");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "payId can't be null");
    }
    public void withoutToken(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random random = new Random();
        int randNum = random.nextInt(10000);
        transferId = "test87654" + randNum;
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
//        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987372");
        requestParams.put("amount", "90");
        requestParams.put("transferId", transferId);
        requestParams.put("transferMode", "imps");

        request.body(requestParams.toString());
        Response response = request.post("/transfer/requestImps");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "Internal Server Error");
    }

    public void verifyRequiredFileds(){
        RestAssured.baseURI = apiBaseURI;

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Random random = new Random();
        int randNum = random.nextInt(10000);
        transferId = "test87654" + randNum;
        JSONObject requestParams = new JSONObject();
        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
//        requestParams.put("beneId", "BENE_71987372");
        requestParams.put("amount", "90");
        requestParams.put("transferId", transferId);
        requestParams.put("transferMode", "imps");

        request.body(requestParams.toString());
        Response response = request.post("/transfer/requestImps");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
        String message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "This field is required.");

        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987372");
//        requestParams.put("amount", "90");
        requestParams.put("transferId", transferId);
        requestParams.put("transferMode", "imps");

        request.body(requestParams.toString());
         response = request.post("/transfer/requestImps");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


         jsonPathEvaluator = response.jsonPath();
         status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
         message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "This field is required.");

        requestParams.clear();

        requestParams.put("payId", "UM1PGA1CZ25YW7E");
        requestParams.put("authorization", token);
        requestParams.put("beneId", "BENE_71987372");
        requestParams.put("amount", "90");
//        requestParams.put("transferId", transferId);
        requestParams.put("transferMode", "imps");

        request.body(requestParams.toString());
         response = request.post("/transfer/requestImps");
        System.out.println("Response body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);


         jsonPathEvaluator = response.jsonPath();
         status = jsonPathEvaluator.get("status");
        Assert.assertEquals(status, "ERROR");
         message = jsonPathEvaluator.get("message");
        Assert.assertEquals(message, "This field is required.");

//        requestParams.clear();
//
//        requestParams.put("payId", "UM1PGA1CZ25YW7E");
//        requestParams.put("authorization", token);
//        requestParams.put("beneId", "BENE_71987372");
//        requestParams.put("amount", "90");
//        requestParams.put("transferId", transferId+"8765432");
////        requestParams.put("transferMode", "imps");
//
//        request.body(requestParams.toString());
//        response = request.post("/transfer/requestImps");
//        System.out.println("Response body: " + response.getBody().asString());
//        Assert.assertEquals(response.getStatusCode(), 200);
//
//
//        jsonPathEvaluator = response.jsonPath();
//        status = jsonPathEvaluator.get("status");
//        Assert.assertEquals(status, "ERROR");
//        message = jsonPathEvaluator.get("message");
//        Assert.assertEquals(message, "transferMode Not Allowed");

    }


}
