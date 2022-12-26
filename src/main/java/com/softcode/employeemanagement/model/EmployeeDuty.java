package com.softcode.employeemanagement.model;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * EmployeeDuty
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-24T21:30:51.511249+06:00[Asia/Dhaka]")
public class EmployeeDuty implements PostEmployeeDutyRequest, Serializable {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("employeeId")
  private Integer employeeId;

  @JsonProperty("dutyStart")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dutyStart;

  @JsonProperty("dutyEnd")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dutyEnd;

  public EmployeeDuty id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "1", required = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EmployeeDuty employeeId(Integer employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  /**
   * Get employeeId
   * @return employeeId
  */
  
  @Schema(name = "employeeId", example = "10000000", required = false)
  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public EmployeeDuty dutyStart(OffsetDateTime dutyStart) {
    this.dutyStart = dutyStart;
    return this;
  }

  /**
   * Get dutyStart
   * @return dutyStart
  */
  @Valid 
  @Schema(name = "dutyStart", required = false)
  public OffsetDateTime getDutyStart() {
    return dutyStart;
  }

  public void setDutyStart(OffsetDateTime dutyStart) {
    this.dutyStart = dutyStart;
  }

  public EmployeeDuty dutyEnd(OffsetDateTime dutyEnd) {
    this.dutyEnd = dutyEnd;
    return this;
  }

  /**
   * Get dutyEnd
   * @return dutyEnd
  */
  @Valid 
  @Schema(name = "dutyEnd", required = false)
  public OffsetDateTime getDutyEnd() {
    return dutyEnd;
  }

  public void setDutyEnd(OffsetDateTime dutyEnd) {
    this.dutyEnd = dutyEnd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployeeDuty employeeDuty = (EmployeeDuty) o;
    return Objects.equals(this.id, employeeDuty.id) &&
        Objects.equals(this.employeeId, employeeDuty.employeeId) &&
        Objects.equals(this.dutyStart, employeeDuty.dutyStart) &&
        Objects.equals(this.dutyEnd, employeeDuty.dutyEnd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, employeeId, dutyStart, dutyEnd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployeeDuty {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    employeeId: ").append(toIndentedString(employeeId)).append("\n");
    sb.append("    dutyStart: ").append(toIndentedString(dutyStart)).append("\n");
    sb.append("    dutyEnd: ").append(toIndentedString(dutyEnd)).append("\n");
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

