package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Test 1: Unauthenticated requests should be rejected
    @Test
    void employeeApiShouldRejectUnauthenticatedRequest() throws Exception {

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isUnauthorized());
    }

    // Test 2: Admin should access employee APIs
    @Test
    void adminShouldAccessEmployeeApi() throws Exception {

        mockMvc.perform(get("/api/employees")
                .with(httpBasic("admin", "Admin@123")))
                .andExpect(status().isOk());
    }

    // Test 3: Employees should not access user management APIs
    @Test
    void employeeShouldNotAccessUserManagementApi() throws Exception {

        mockMvc.perform(get("/api/users")
                .with(httpBasic("employee", "Employee@123")))
                .andExpect(status().isForbidden());
    }

    // Test 4: Invalid employee data should be rejected
    @Test
    void shouldRejectEmployeeWithInvalidData() throws Exception {

        String invalidEmployee = """
                {
                    "firstName": "",
                    "lastName": "",
                    "email": "invalid-email",
                    "phone": "",
                    "jobTitle": "",
                    "salary": -100,
                    "status": ""
                }
                """;

        mockMvc.perform(post("/api/employees")
                .with(httpBasic("admin", "Admin@123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidEmployee))
                .andExpect(status().isBadRequest());
    }

    // Test 5: Admin should create a valid employee
    @Test
    void adminShouldCreateValidEmployee() throws Exception {

        String validEmployee = """
                {
                    "firstName": "Test",
                    "lastName": "Employee",
                    "email": "test.employee.unique102@example.com",
                    "phone": "9876501234",
                    "jobTitle": "Software Tester",
                    "salary": 45000,
                    "joiningDate": "2026-10-09",
                    "status": "ACTIVE",
                    "departmentId": 2
                }
                """;

        mockMvc.perform(post("/api/employees")
                .with(httpBasic("admin", "Admin@123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(validEmployee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeId").exists())
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.email")
                        .value("test.employee.unique102@example.com"));
    }

    // Test 6: Admin should update an existing employee
    @Test
    void adminShouldUpdateExistingEmployee() throws Exception {

        String updatedEmployee = """
                {
                    "firstName": "Rahul",
                    "lastName": "Sharma",
                    "email": "rahul.sharma@example.com",
                    "phone": "9876543210",
                    "jobTitle": "Senior Software Developer",
                    "salary": 70000,
                    "joiningDate": "2026-10-08",
                    "status": "ACTIVE",
                    "departmentId": 2
                }
                """;

        mockMvc.perform(put("/api/employees/2")
                .with(httpBasic("admin", "Admin@123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedEmployee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(2))
                .andExpect(jsonPath("$.jobTitle")
                        .value("Senior Software Developer"))
                .andExpect(jsonPath("$.salary").value(70000.0));
    }
    
 
 // Test 7: Admin should fetch an employee by ID
 @Test
 void adminShouldGetEmployeeById() throws Exception {

     mockMvc.perform(get("/api/employees/2")
             .with(httpBasic("admin", "Admin@123")))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$.employeeId").value(2));
 }

 // Test 8: Admin should delete an employee
 @Test
 void adminShouldDeleteEmployee() throws Exception {

     mockMvc.perform(
             org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                     .delete("/api/employees/2")
                     .with(httpBasic("admin", "Admin@123")))
             .andExpect(status().isNoContent());
 }
 
//Test 9: Admin should access all leave requests
@Test
void adminShouldAccessLeaveRequestsApi() throws Exception {

  mockMvc.perform(get("/api/leave-requests")
          .with(httpBasic("admin", "Admin@123")))
          .andExpect(status().isOk());
}

//Test 10: Invalid leave request data should be rejected
@Test
void shouldRejectLeaveRequestWithInvalidData() throws Exception {

 String invalidLeaveRequest = """
         {
             "startDate": null,
             "endDate": null,
             "employeeId": null,
             "leaveTypeId": null
         }
         """;

 mockMvc.perform(post("/api/leave-requests")
         .with(httpBasic("admin", "Admin@123"))
         .contentType(MediaType.APPLICATION_JSON)
         .content(invalidLeaveRequest))
         .andExpect(status().isBadRequest());
}

//Test 11: Admin should retrieve all leave requests
@Test
void adminShouldGetAllLeaveRequests() throws Exception {

 mockMvc.perform(get("/api/leave-requests")
         .with(httpBasic("admin", "Admin@123")))
         .andExpect(status().isOk());
}

//Test 12: Missing leave request should return 404
@Test
void shouldReturnNotFoundForMissingLeaveRequest() throws Exception {

 mockMvc.perform(get("/api/leave-requests/999999999")
         .with(httpBasic("admin", "Admin@123")))
         .andExpect(status().isNotFound());
}

//Test 13: Employee should not be allowed to approve leave requests
@Test
void employeeShouldNotApproveLeaveRequest() throws Exception {

 mockMvc.perform(
         org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                 .patch("/api/leave-requests/1/approve")
                 .param("managerComment", "Approved")
                 .with(httpBasic("employee", "Employee@123")))
         .andExpect(status().isForbidden());
}

//Test 14: HR should be allowed to access the approval endpoint
@Test
void hrShouldBeAllowedToApproveLeaveRequest() throws Exception {

 mockMvc.perform(
         org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                 .patch("/api/leave-requests/999999999/approve")
                 .param("managerComment", "Test approval")
                 .with(httpBasic("hr", "Hr@123")))
         .andExpect(status().isNotFound());
}



}

