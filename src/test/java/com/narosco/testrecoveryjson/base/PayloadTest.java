package com.narosco.testrecoveryjson.base;

public enum PayloadTest {

    CREATE_USER("/request/create_user.json"),
    CREATE_USER_MOCK("/create_user.json");

    private String path;

    PayloadTest(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String mockPath() {
        return "mock".concat(getPath());
    }
}
