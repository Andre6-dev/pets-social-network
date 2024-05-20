package com.devandre.petsnetwork.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ControllerResponseHandler {

    public static ResponseEntity<Object> response(HttpStatus status, Object data, boolean result) {
        Map<String, Object> map = new HashMap<>();

        map.put("timestamp", new Date());
        map.put("status", status.value());
        map.put("result", result);

        // Check if the data is a List<Object>
        if (data instanceof List<?>) {
            map.put("data", data);
        } else {
            // If it's not a list, wrap it into a list
            List<Object> dataList = new ArrayList<>();
            dataList.add(data);
            map.put("data", dataList);
        }
        return new ResponseEntity<>(map, status);
    }
}


