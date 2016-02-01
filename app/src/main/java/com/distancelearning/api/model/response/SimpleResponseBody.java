package com.distancelearning.api.model.response;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class SimpleResponseBody {
    private boolean isSucces;
    private int code;
    private String message;

    public SimpleResponseBody() {
    }

    public SimpleResponseBody(boolean isSucces, int code, String message) {
        this.isSucces = isSucces;
        this.code = code;
        this.message = message;
    }

    public boolean isSucces() {
        return isSucces;
    }

    public void setIsSucces(boolean isSucces) {
        this.isSucces = isSucces;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
