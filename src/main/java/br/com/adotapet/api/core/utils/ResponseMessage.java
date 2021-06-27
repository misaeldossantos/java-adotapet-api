/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adotapet.api.core.utils;

/**
 *
 * @author misae
 */
public class ResponseMessage {
    private String message;
    private Type type;

    public ResponseMessage(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public static ResponseMessage build(String message, Type type) {
        return new ResponseMessage(message, type);
    }
    
    public static enum Type {
        ERROR, OK;
    }
    
}
