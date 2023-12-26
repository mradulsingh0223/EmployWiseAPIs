package com.assesment.employwise_api.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "employeeData")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @org.springframework.data.annotation.Id
    private String employeeId;
    @NotNull
    @NotBlank(message="Please enter your name")
    private String name;
    @NotNull
    @NotBlank(message="Please enter your Mobile Number")
    @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10)
    private int mobileNum;
    @NotNull
    @NotBlank(message="Please enter your Mail")
    @Email(message="Enter valid Email Id.")
    private String mail;
    @NotNull
    @NotBlank(message="Please enter the Id of manager you have to report to")
    private String reportId;
    @NotNull
    @NotBlank(message="Please enter your Image url")
    private String image;
}
