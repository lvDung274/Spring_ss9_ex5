package com.example.demo_department.models.services.impl;

import com.example.demo_department.exceptions.DuplicateResourceException;
import com.example.demo_department.exceptions.ResourceNotFoundException;
import com.example.demo_department.models.dto.req.EmployeeCreateDTO;
import com.example.demo_department.models.entities.Department;
import com.example.demo_department.models.entities.Employee;
import com.example.demo_department.models.repositories.DepartmentRepository;
import com.example.demo_department.models.repositories.EmployeeRepository;
import com.example.demo_department.models.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(EmployeeCreateDTO req){
        // tìm phòng ban theo ID từ DTO trước khi gán vào Employee
        Department department = departmentRepository.findById(req.departmentId())
                .orElseThrow(()->new ResourceNotFoundException("Department Not Found"));
        //Kiểm tra trùng email -> Nếu trùng, ném lỗi Duplicate
        if (employeeRepository.existsByEmail(req.email())) {
            throw new DuplicateResourceException("Email đã được sử dụng");
        }

        Employee employee = Employee.builder()
                .fullName(req.fullName())
                .email(req.email())
                .phone(req.phone())
                .salary(req.salary())
                .department(department)
                .build();

        return employeeRepository.save(employee);
    }
    @Override
    public Employee updateAvatar(Long employeeId, MultipartFile file) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhân viên"));

        return employeeRepository.save(employee);
    }


}
