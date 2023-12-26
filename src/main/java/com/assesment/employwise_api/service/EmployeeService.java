package com.assesment.employwise_api.service;

import com.assesment.employwise_api.model.Employee;
import com.assesment.employwise_api.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private JavaMailSender mailSender;
    // CRUD Operations

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeId(UUID.randomUUID().toString().split("-")[0]);
        Employee savedEmployee = repository.save(employee);

        notifyLevel1Manager(savedEmployee);

        return savedEmployee;
    }

    private void notifyLevel1Manager(Employee employee) {
        Employee level1Manager = getNthLevelManager(employee.getEmployeeId(),1);

        if (level1Manager != null) {
            String subject = "New Employee Added";
            String message = String.format(
                    "%s will now work under you. Mobile number is %s and email is %s.",
                    employee.getName(), employee.getMobileNum(), employee.getMail()
            );

            sendEmail(level1Manager.getMail(), subject, message);
        }
    }

    private void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    public List<Employee> findAllEmployee(){

        List<Employee> employees = repository.findAll();
        if(employees.isEmpty())
            return Collections.emptyList();
        return employees;
    }

    public List<Employee> findAllEmployeepagination(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Employee> employeePage = repository.findAll(pageable);

        if (employeePage.isEmpty()) {
            return Collections.emptyList();
        }

        return employeePage.getContent();
    }

    public Employee getNthLevelManager(String employeeId, int level) {
        Employee employe = repository.findById(employeeId).orElse(null);

        while (employe != null && level > 0) {
            employe = repository.findById(employe.getReportId()).orElse(null);
            level--;
        }

        return employe;
    }

    public ResponseEntity<?> updateEmployee(Employee employeeRequest, String employeeId) {
        Optional<Employee> optionalExistingEmployee = repository.findById(employeeId);

        if (optionalExistingEmployee.isPresent()) {
            Employee existingEmployee = optionalExistingEmployee.get();

            // Copy non-null properties from employeeRequest to existingEmployee
            BeanUtils.copyProperties(employeeRequest, existingEmployee, getNullPropertyNames(employeeRequest));

            return new ResponseEntity<>(repository.save(existingEmployee), HttpStatus.OK);
        }

        return new ResponseEntity<>("No employee found", HttpStatus.NOT_FOUND);
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || srcValue.equals(0)) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public String deleteEmployee(String employeeId)
    {
        repository.deleteById(employeeId);
        return employeeId+"Employee deleted Successfully";
    }
}
