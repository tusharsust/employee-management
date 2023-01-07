package com.softcode.employeemanagement.api;

import com.softcode.employeemanagement.model.Employee;
import com.softcode.employeemanagement.model.PutEmployeeRequest;
import com.softcode.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-24T21:30:51.511249+06:00[Asia/Dhaka]")
@Controller
@RequestMapping("${openapi.softcodeTestProject.base-path:/v1}")
public class EmployeesApiController implements EmployeesApi {

    private final NativeWebRequest request;

    private EmployeeService employeeService;

    @Autowired
    public EmployeesApiController(NativeWebRequest request, EmployeeService employeeService) {
        this.request = request;
        this.employeeService = employeeService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


    @Override
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PutEmployeeRequest> postEmployee(@Valid Employee putEmployeeRequest) {
        return new ResponseEntity<>(employeeService.createEmployee(putEmployeeRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> putEmployee(@Valid Employee putEmployeeRequest) {
        employeeService.updateEmployee(putEmployeeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> getEmployee(Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
