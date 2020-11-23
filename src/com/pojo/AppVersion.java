package com.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class AppVersion {

    private Integer id;
    private Integer appId;
    private Integer versionNo;
    private String versionInfo;
    private Integer publishStatus;
    private Integer downloadLink;
    private Double versionSize;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private String apkLocPath;
    private String apkFileName;
}
