package com.softcode.employeemanagement.api;

import com.softcode.employeemanagement.model.PostEmployeeDutyRequest;
import com.softcode.employeemanagement.service.EmployeeDutyService;
import com.softcode.employeemanagement.model.EmployeeDuty;
import java.time.OffsetDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-24T21:30:51.511249+06:00[Asia/Dhaka]")
@Controller
@RequestMapping("${openapi.softcodeTestProject.base-path:/v1}")
public class EmployeeDutiesApiController implements EmployeeDutiesApi {

    private final NativeWebRequest request;

    private final EmployeeDutyService employeeDutyService;

    @Autowired
    public EmployeeDutiesApiController(NativeWebRequest request, EmployeeDutyService employeeDutyService) {
        this.request = request;
        this.employeeDutyService = employeeDutyService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<EmployeeDuty>> getEmployeeDuties(@Valid OffsetDateTime dutyStart, @Valid OffsetDateTime dutyEnd) {
        return new ResponseEntity<>(employeeDutyService.getEmployeeDuties(dutyStart, dutyEnd), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostEmployeeDutyRequest> postEmployeeDuty(@Valid EmployeeDuty postEmployeeDutyRequest) {
        return new ResponseEntity<>(employeeDutyService.createEmployeeDuty(postEmployeeDutyRequest), HttpStatus.CREATED);
    }
}
