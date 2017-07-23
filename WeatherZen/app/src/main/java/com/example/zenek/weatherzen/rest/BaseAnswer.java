package com.example.zenek.weatherzen.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class BaseAnswer {
    protected boolean success;
    protected transient HashMap<String, List<String>> errorsMap;
    protected String message;

    @Expose
    private Object errors;

    public boolean isSuccess() {
        return success;
    }

    public LinkedTreeMap getErrorsAsTree() {
        if (isErrorListATree()) {
            return (LinkedTreeMap) errors;
        }
        return null;
    }

    public String getErrorString() {
        if (isErrorListAString()) {
            return (String) errors;
        }

        if (isErrorListATree()) {
            LinkedTreeMap errors = getErrorsAsTree();
            if (errors != null && errors.size() > 0) {
                try {
                    return getFirstRawErrorFor((String) errors.keySet().iterator().next());
                } catch (Exception ex) {
                    return null;
                }
            }
        }
        return null;
    }

    public boolean isErrorListATree() {
        return errors != null && errors instanceof LinkedTreeMap;
    }

    public boolean isErrorListAString() {
        return errors != null && errors instanceof String;
    }

    public String getFirstRawErrorFor(String key) {
        LinkedTreeMap errors = getErrorsAsTree();
        if (errors != null) {
            Object error = errors.get(key);
            if (error == null) {
                return null;
            }

            if (error instanceof String) {
                return (String) error;
            }

            if (error instanceof Iterable) {
                Iterator iterator = ((Iterable) error).iterator();
                if (iterator.hasNext()) {
                    return String.valueOf(iterator.next());
                }
            }
        }
        return null;
    }

    public Object getErrors() {
        return errors;
    }

    public HashMap<String, List<String>> getErrorsMap() {
        return errorsMap;
    }

    public String getMessage() {
        return message;
    }
}
