/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.softcode.employeemanagement.api;

import org.springframework.format.annotation.DateTimeFormat;
import com.softcode.employeemanagement.model.EmployeeDuty;
import java.time.OffsetDateTime;
import com.softcode.employeemanagement.model.PostEmployeeDutyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-24T21:30:51.511249+06:00[Asia/Dhaka]")
@Validated
@Tag(name = "employee-duties", description = "")
public interface EmployeeDutiesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /employee-duties/{id} : Delete an employee duty record
     * 
     *
     * @param id  (required)
     * @return Successfully removed employee duty (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Employee duty not found (status code 404)
     */
    @Operation(
        operationId = "deleteEmployeeDuty",
        summary = "Delete an employee duty record",
        tags = { "Employee Duties" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully removed employee duty"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Employee duty not found")
        },
        security = {
            @SecurityRequirement(name = "ApiKeyAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/employee-duties/{id}"
    )
    default ResponseEntity<Void> deleteEmployeeDuty(
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /employee-duties : Get all employee duties
     * 
     *
     * @param dutyStart Filter for duty start (ex. 2021-05-26) (optional)
     * @param dutyEnd Filter for duty end (ex. 2021-05-27) (optional)
     * @return All employee duties (status code 200)
     */
    @Operation(
        operationId = "getEmployeeDuties",
        summary = "Get all employee duties",
        tags = { "Employee Duties" },
        responses = {
            @ApiResponse(responseCode = "200", description = "All employee duties", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDuty.class))
            })
        },
        security = {
            @SecurityRequirement(name = "ApiKeyAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/employee-duties",
        produces = { "application/json" }
    )
    default ResponseEntity<List<EmployeeDuty>> getEmployeeDuties(
        @Parameter(name = "duty_start", description = "Filter for duty start (ex. 2021-05-26)") @Valid @RequestParam(value = "duty_start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dutyStart,
        @Parameter(name = "duty_end", description = "Filter for duty end (ex. 2021-05-27)") @Valid @RequestParam(value = "duty_end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dutyEnd
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dutyStart\" : \"2000-01-23T04:56:07.000+00:00\", \"dutyEnd\" : \"2000-01-23T04:56:07.000+00:00\", \"employeeId\" : 10000000, \"id\" : 1 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /employee-duties/{id} : Get employee duty by id
     * 
     *
     * @param id  (required)
     * @return Employee duty (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Employee duty not found (status code 404)
     */
    @Operation(
        operationId = "getEmployeeDuty",
        summary = "Get employee duty by id",
        tags = { "Employee Duties" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Employee duty", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDuty.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Employee duty not found")
        },
        security = {
            @SecurityRequirement(name = "ApiKeyAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/employee-duties/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<EmployeeDuty> getEmployeeDuty(
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dutyStart\" : \"2000-01-23T04:56:07.000+00:00\", \"dutyEnd\" : \"2000-01-23T04:56:07.000+00:00\", \"employeeId\" : 10000000, \"id\" : 1 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /employee-duties : Add new employee duty record
     * 
     *
     * @param postEmployeeDutyRequest  (required)
     * @return Successfully created employee duty (status code 200)
     *         or Invalid input (status code 405)
     */
    @Operation(
        operationId = "postEmployeeDuty",
        summary = "Add new employee duty record",
        tags = { "Employee Duties" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully created employee duty", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PostEmployeeDutyRequest.class))
            }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
        },
        security = {
            @SecurityRequirement(name = "ApiKeyAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/employee-duties",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<PostEmployeeDutyRequest> postEmployeeDuty(
        @Parameter(name = "PostEmployeeDutyRequest", description = "", required = true) @Valid @RequestBody PostEmployeeDutyRequest postEmployeeDutyRequest
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /employee-duties : Update an employee duty record
     * 
     *
     * @param employeeDuty  (required)
     * @return Successfully updated employee duty record (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Employee duty not found (status code 404)
     *         or Validation exception (status code 405)
     */
    @Operation(
        operationId = "putEmployeeDuty",
        summary = "Update an employee duty record",
        tags = { "Employee Duties" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully updated employee duty record"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Employee duty not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")
        },
        security = {
            @SecurityRequirement(name = "ApiKeyAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/employee-duties",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> putEmployeeDuty(
        @Parameter(name = "EmployeeDuty", description = "", required = true) @Valid @RequestBody EmployeeDuty employeeDuty
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
