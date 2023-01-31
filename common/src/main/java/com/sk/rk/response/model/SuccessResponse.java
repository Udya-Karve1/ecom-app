package com.sk.rk.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sk.rk.util.Constants;

public class SuccessResponse<T> extends BaseResponse{

    @JsonProperty(Constants.DATA_FIELD)
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private T data;

    @JsonProperty(Constants.DURATION_FIELD)
    private Long duration;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
