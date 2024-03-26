package com.sk.rk.order.util;

import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TestUtil {

    public static final String TEST_CASE_NAME     		= "test-case-name";
    public static final String END_POINT          		= "end-point";
    public static final String STATUS             		= "status";
    public static final String DATA               		= "data";
    public static final String HEADERS            		= "headers";
    public static final String METHOD_NAME        		= "method-name";
    public static final String METHOD             		= "method";
    public static final String USER_MESSAGE       		= "userMessage";
    public static final String PAYLOAD            		= "payload";
    public static final String AUTHORIZATION      		= "Authorization";
    public static final String BEARER             		= "Bearer ";
    public static Map<String, Object> paramMapML = new HashMap<>();

    public static final String POST                     = "POST";
    public static final String PATCH                    = "PATCH";
    public static final String DELETE                   = "DELETE";
    public static final String PUT                      = "PUT";
    public static final String IS_DYNAMIC_PAYLOAD       = "is-dynamic-payload";
    public static final String ASSERT_EXPECTED_RESULT   = "assert-expected-result";
    public static final String EXPECTED_RESULT          = "expected-result";



    public static String access_token = "";


    public static String getAccessToken() {
        return access_token;
    }



    public static Response getStandardResponse(String method, Map<String, String> headers, Map<String, Object> payloadMap, String endpoint) {
        Response response = null;
        if(method.equals(POST)) {
            response = given().contentType(JSON).headers(headers).body(payloadMap).when()
                    .post(endpoint).thenReturn();
        } else if(method.equals(PATCH)) {
            response = given().contentType(JSON).headers(headers).body(payloadMap).when()
                    .patch(endpoint).thenReturn();
        } else if(method.equals(DELETE)) {
            response = given().contentType(JSON).headers(headers).body(payloadMap).when()
                    .delete(endpoint).thenReturn();
        } else if(method.equals(PUT)) {
            response = given().contentType(JSON).headers(headers).body(payloadMap).when()
                    .put(endpoint).thenReturn();
        } else {
            response = given().contentType(JSON).headers(headers).when().get(endpoint).thenReturn();
        }

        return response;
    }





    public static Map<String, Object> preparePayloadML(Map payload) {
        if(payload.containsKey("projectId")) {
            payload.put("projectId", paramMapML.get("{project-id}"));
        }

        if(payload.containsKey("projectDashboardId")) {
            payload.put("projectDashboardId", paramMapML.get("{project-dashboard-id}"));
        }

        if(payload.containsKey("regressionModel")) {
            payload.put("regressionModel", paramMapML.get("{regression-model}"));
        }
        if(payload.containsKey("regressionModelId")) {
            payload.put("regressionModelId", paramMapML.get("{regression-model-id}"));
        }

        return payload;
    }


    public static String preparePathQueryParamML(String endPoint) {

        if(endPoint.indexOf("{project-dashboard-id}")>0) {
            return endPoint.replaceAll("\\{project-dashboard-id}", paramMapML.get("{project-dashboard-id}").toString());
        }else if(endPoint.indexOf("{project-id}")>0) {
            return endPoint.replaceAll("\\{project-id}", paramMapML.get("{project-id}").toString());
        }else if(endPoint.indexOf("{regression-model-id}")>0) {
            return endPoint.replaceAll("\\{regression-model-id}", paramMapML.get("{regression-model-id}").toString());
        }else {
            return endPoint;
        }
    }



}
