package com.narosco.testrecoveryjson.user;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private Integer age;
    private String sex;
    private String email;

    public UserDTO(String name, Integer age, String sex, String email) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
    }

    public UserDTO() {
    }
}
