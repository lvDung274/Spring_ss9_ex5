package com.example.demo_department.controllers;

import com.example.demo_department.models.dto.req.EmployeeCreateDTO;
import com.example.demo_department.models.dto.response.ApiResponse;
import com.example.demo_department.models.entities.Employee;
import com.example.demo_department.models.repositories.EmployeeRepository;
import com.example.demo_department.models.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeCreateDTO req){
        Employee createdEmployee = employeeService.createEmployee(req);

        ApiResponse<Employee> response = ApiResponse.<Employee>builder()
                .status("SUCCESS")
                .message("Thêm mới nhân viên thành công")
                .data(createdEmployee)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @PutMapping
    public ResponseEntity<?>updateAvatar(@PathVariable Long id, @RequestParam("file")MultipartFile file){
        Employee updatedEmployee = employeeService.updateAvatar(id, file);

        ApiResponse<?> response = ApiResponse.builder()
                .status("SUCCESS")
                .message("Cập nhật ảnh đại diện thành công")
                .data(updatedEmployee)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
