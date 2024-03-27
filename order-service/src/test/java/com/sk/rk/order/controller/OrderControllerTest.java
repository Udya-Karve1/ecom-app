package com.sk.rk.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.rk.order.util.TestUtil;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import net.joshka.junit.json.params.JsonFileSource;
import org.json.JSONException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.Map;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("wfo")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
@Slf4j
public class OrderControllerTest {

    @Autowired
    private ObjectMapper mapper;


    @ParameterizedTest(name = "{displayName}")
    @JsonFileSource(resources = "/src/test/resources/testCases.json")
    @Tag("unit")
    void testActionController(JsonObject object) throws IOException, JSONException {

        Map testCaseMap                     = mapper.readValue(object.toString(), Map.class);
        String  endpoint                    = testCaseMap.get(TestUtil.END_POINT).toString();
        Map     headers                     = mapper.convertValue(testCaseMap.get(TestUtil.HEADERS), Map.class);
        int     expectedStatus              = Integer.parseInt(testCaseMap.get(TestUtil.STATUS).toString());
        String  method                      = testCaseMap.get(TestUtil.METHOD).toString();
        Map     payloadMap                  = mapper.convertValue(testCaseMap.get(TestUtil.PAYLOAD), Map.class);
        String  testCaseName                = testCaseMap.get(TestUtil.TEST_CASE_NAME).toString();
        boolean assertResult                = Boolean.parseBoolean(testCaseMap.get(TestUtil.ASSERT_EXPECTED_RESULT).toString());

        boolean isDynamicPayload            = testCaseMap.get(TestUtil.IS_DYNAMIC_PAYLOAD) == null ? false : Boolean.parseBoolean(testCaseMap.get(TestUtil.IS_DYNAMIC_PAYLOAD).toString());


        if(payloadMap!=null && isDynamicPayload) {
            payloadMap = TestUtil.preparePayloadML(payloadMap);
        }

        endpoint = TestUtil.preparePathQueryParamML(endpoint);

        headers.put(TestUtil.AUTHORIZATION, TestUtil.BEARER + TestUtil.getAccessToken());
        Response response = null;

        log.info("endpoint: {}", endpoint);

        response = TestUtil.getStandardResponse(method, headers, payloadMap, endpoint);

        int status = response.statusCode();
        assertEquals(expectedStatus, status);
        if (status < 300) {
            Object responseData = response.jsonPath().get(TestUtil.DATA);

            if(assertResult) {
                Object expected = ((Map) testCaseMap.get(TestUtil.EXPECTED_RESULT)).get(TestUtil.DATA);
                Object actual = responseData;
                String expectedData = mapper.writeValueAsString(expected);
                String actualData = mapper.writeValueAsString(actual);

                String failureMessage = "\nEXPECTED OUTPUT: " + expectedData + "\nACTUAL OUTPUT:   " + actualData;
                JSONAssert.assertEquals(failureMessage,expectedData, actualData, JSONCompareMode.LENIENT);
            }
        } else if(status != 500 && assertResult){
            Object expectedOutputData = ((Map) testCaseMap.get(TestUtil.EXPECTED_RESULT)).get(TestUtil.USER_MESSAGE);
            Object responseData = response.jsonPath().get(TestUtil.USER_MESSAGE);

            JSONAssert.assertEquals(
                    mapper.writeValueAsString(expectedOutputData), mapper.writeValueAsString(responseData), JSONCompareMode.LENIENT);
        }
    }
}
