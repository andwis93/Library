package com.library.library.domain.support;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Status {
    AVAILABLE("available"),
    LOST("lost"),
    DESTROYED("destroyed"),
    RENTED("rented");
    private String status;
    private static final Map<String,Status> ENUM_MAP;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    static {
        Map<String,Status> map = new ConcurrentHashMap<String, Status>();
        for (Status instance : Status.values()) {
            map.put(instance.getStatus().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Status getEnumFromString (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

}