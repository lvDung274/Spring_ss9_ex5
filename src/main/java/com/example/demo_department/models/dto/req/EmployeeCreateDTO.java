package com.example.demo_department.models.dto.req;

import jakarta.validation.constraints.*;

public record EmployeeCreateDTO(
        @NotBlank(message = "Họ tên nhân viên không được để trống")
         String fullName,

        @NotBlank(message = "Email không được để trống")
        @Email(message = "Email không đúng định dạng")
         String email,

        @NotBlank(message = "Số điện thoại không được để trống")
        @Pattern(
        regexp = "^(03|05|07|08|09)[0-9]{8}$",
        message = "Số điện thoại không hợp lệ"
        )
         String phone,

        @NotNull(message = "Lương không được để trống")
        @Min(value = 5000000, message = "Lương tối thiểu phải từ 5,000,000 VNĐ")
         Double salary,

        @NotNull(message = "Phòng ban không được để trống")
         Long departmentId


) {
}
