package com.softcode.employeemanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Employee
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-24T21:30:51.511249+06:00[Asia/Dhaka]")
public class Employee implements PutEmployeeRequest {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("email")
  private String email;

  @JsonProperty("joiningDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime joiningDate;

  public Employee id(Integer id) {
    this.id = id;
    return this;
  }

  @JsonProperty("employeeDuties")
  private Set<EmployeeDuty> employeeDuties = new HashSet<>();

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "10000000", required = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Employee name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  
  @Schema(name = "name", example = "John Doe", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Employee phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
  */
  
  @Schema(name = "phone", example = "+46701234567", required = false)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Employee email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  
  @Schema(name = "email", example = "john.doe@softcode.se", required = false)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Employee joiningDate(OffsetDateTime joiningDate) {
    this.joiningDate = joiningDate;
    return this;
  }

  /**
   * Get joiningDate
   * @return joiningDate
  */
  @Valid 
  @Schema(name = "joiningDate", required = false)
  public OffsetDateTime getJoiningDate() {
    return joiningDate;
  }

  public void setJoiningDate(OffsetDateTime joiningDate) {
    this.joiningDate = joiningDate;
  }

  public Set<EmployeeDuty> getEmployeeDuties() {
    return employeeDuties;
  }

  public void setEmployeeDuties(Set<EmployeeDuty> employeeDuties) {
    this.employeeDuties = employeeDuties;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return Objects.equals(this.id, employee.id) &&
        Objects.equals(this.name, employee.name) &&
        Objects.equals(this.phone, employee.phone) &&
        Objects.equals(this.email, employee.email) &&
        Objects.equals(this.joiningDate, employee.joiningDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, phone, email, joiningDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Employee {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    joiningDate: ").append(toIndentedString(joiningDate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

