package com.narosco.testrecoveryjson.base;

import com.narosco.testrecoveryjson.infrastructure.JacksonUltils;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest {

    private static final String DATA = "data";

    protected String getMockMapListObject(PayloadTest payloadTest) {
        try{
            return IOUtils.toString(
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(payloadTest.mockPath())),
                    Charset.defaultCharset()
            );
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    protected <S, T> Map<T, List<S>> getMockMapListObject(PayloadTest payloadTest, Class<T> keyClass, Class<S> valueClass) {
        return JacksonUltils.convertFromWrapperList(getMockMapListObject(payloadTest), keyClass, valueClass);

    }

    protected <S, T> Map<T, S> getMockMapSingleObject(PayloadTest payloadTest, Class<T> keyClass, Class<S> valueClass) {
        return JacksonUltils.convertFromWrapper(getMockMapListObject(payloadTest), keyClass, valueClass);
    }

    protected <S> Map<String, S> getMockMapSingleObject(PayloadTest payloadTest, Class<S> valueClass) {
        return getMockMapSingleObject(payloadTest, String.class, valueClass);
    }

    protected <S> Map<String, List<S>> getMockMapListObject(PayloadTest payloadTest, Class<S> valueClass) {
        return getMockMapListObject(payloadTest, String.class, valueClass);
    }

    protected <S> S getMockSingleExtractData(PayloadTest payloadTest, Class<S> valueClass){
        Map<String, S> mockMapSingleObject = getMockMapSingleObject(payloadTest, String.class, valueClass);
        return mockMapSingleObject.get(DATA);
    }

    protected <S> List<S> getMockListExtractData(PayloadTest payloadTest, Class<S> valueClass){
        Map<String, List<S>> mockMapListeObject = getMockMapListObject(payloadTest, String.class, valueClass);
        return mockMapListeObject.get(DATA);
    }

    protected <S> S getMockSingleObject(PayloadTest payloadTest, Class<S> valueClass){
        return JacksonUltils.convertSimpleObject(getMockMapListObject(payloadTest), valueClass);
    }

}
