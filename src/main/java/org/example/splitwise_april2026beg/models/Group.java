package org.example.splitwise_april2026beg.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Group extends BaseModel {
    private String name;
    private String description;
    private Date createdOn;
    private User admin;
    private List<User> members;
    private List<Expense> expenses;
}
