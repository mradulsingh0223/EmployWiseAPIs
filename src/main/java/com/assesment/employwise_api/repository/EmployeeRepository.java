package com.assesment.employwise_api.repository;

import com.assesment.employwise_api.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee,String> {
}
