package com.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class BackendUser {

    private Integer id;
    private String userCode;
    private String userName;
    private Integer userType;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private String userPassword;
}
