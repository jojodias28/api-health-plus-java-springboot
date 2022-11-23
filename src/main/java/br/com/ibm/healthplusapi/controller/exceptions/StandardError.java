package br.com.ibm.healthplusapi.controller.exceptions;

public class StandardError {
    private Integer status;
    private String message;
    private String path;

    public StandardError(Integer status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
