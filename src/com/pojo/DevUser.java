package com.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class DevUser {
    private Integer id;
    private String devCode;
    private String devName;
    private String devPassword;
    private String devEmail;
    private String devInfo;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;

}
