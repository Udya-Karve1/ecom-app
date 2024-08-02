package com.sk.rk.order.controller;

import com.sk.rk.order.util.TestUtil;
import jakarta.json.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.stream.Stream;


public class JsonTestDataProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Stream.Builder<Arguments> stream = Stream.builder();

        try {
            InputStream fis = new FileInputStream("/testCases.json");
            JsonReader reader = Json.createReader(fis);
            JsonArray testData = reader.readArray();
            reader.close();

            for (JsonValue caseValue : testData) {
                JsonObject testCase = (JsonObject) caseValue;

                stream.accept(Arguments.of(testCase, testCase.getString(TestUtil.TEST_CASE_NAME) + " : "
                        + testCase.getString(TestUtil.METHOD_NAME) + " "
                        + testCase.getString(TestUtil.END_POINT) +" - "+ testCase.getInt(TestUtil.STATUS)));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stream.build();
    }

}
