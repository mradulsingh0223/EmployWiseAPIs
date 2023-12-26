package com.assesment.employwise_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "employeeData")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @org.springframework.data.annotation.Id
    private String employeeId;
    private String name;
    private int mobileNum;
    private String mail;
    private String reportId;
    private String image;
}
