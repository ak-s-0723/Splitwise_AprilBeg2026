package org.example.splitwise_april2026beg.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User extends BaseModel {
    private String name;
    private String phoneNumber;
    private String password;
    private List<Group> groups;
}
