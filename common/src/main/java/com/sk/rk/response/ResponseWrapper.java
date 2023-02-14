package com.sk.rk.response;

import com.sk.rk.response.model.BaseResponse;
import com.sk.rk.response.model.ErrorResponse;
import com.sk.rk.response.model.SuccessResponse;
import com.sk.rk.util.CommonUtil;
import com.sk.rk.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice(basePackages = "com.sk.rk")
public class ResponseWrapper implements ResponseBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Class converterType) {
        String classPackage = methodParameter.getContainingClass().getPackage().getName();
        return
               !classPackage.startsWith("org.springdoc")
                && !classPackage.startsWith("org.springframework.boot.actuate")
                && !converterType.getName().endsWith("ResourceHttpMessageConverter");
    }

    @Override
    public Object beforeBodyWrite(Object responseObject, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        BaseResponse respond = null;
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();

        int statusCode = servletResponse.getStatus();

        if(responseObject instanceof ErrorResponse) {
            return (ErrorResponse)responseObject;
        } else {

            if((statusCode>=200 && statusCode<300)) {
                long duration = this.getDuration();
                SuccessResponse successResponse = new SuccessResponse();

                successResponse.setDuration(duration);
                successResponse.setIsSuccess(true);
                successResponse.setData(responseObject);
                respond = successResponse;
            } else {
                Map<String, Object> responseMap = (Map<String, Object>)responseObject;
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setIsSuccess(false);
                errorResponse.setPath(responseMap.get("path").toString());
                errorResponse.setException(responseMap.get("message").toString());

                if(responseMap.get("message")!=null && responseMap.get("message").toString().equalsIgnoreCase("Token expire")) {
                    errorResponse.setUserMessage(Collections.singletonList(responseMap.get("error").toString()));
                    errorResponse.setCode(401);
                } else {
                    errorResponse.setUserMessage(Collections.singletonList(responseMap.get("error").toString()));
                    errorResponse.setCode(statusCode);
                }

                errorResponse.setStackTrace("");

                respond = errorResponse;
            }
            respond.setRequestAt(Objects.isNull(servletRequest.getAttribute(Constants.START_TIME)) ? new Date(): new Date((Long) servletRequest.getAttribute(Constants.START_TIME)));
        }

        return respond;
    }

    private long getDuration() {
        HttpServletRequest currentRequest = CommonUtil.getCurrentRequest();
        if (currentRequest.getAttribute(Constants.START_TIME) == null) {
            return 0L;
        } else {
            long startTime = (Long)currentRequest.getAttribute(Constants.START_TIME);
            log.trace("Request URL::" + currentRequest.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());
            return System.currentTimeMillis() - startTime;
        }
    }
}
