package com.hos.sys.modular.system.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户传输bean
 *
 * @author dev
 * @Date 2017/5/5 22:40
 */
@Data
public class UserDto {

    private Long userId;

    @NotBlank
    private String account;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String sex;

    @NotBlank
    private String email;

    private String phone;

    private String roleId;

    @NotNull
    private Long deptId;

    private String status;

    private String avatar;

    @NotBlank
    private String position;

}
