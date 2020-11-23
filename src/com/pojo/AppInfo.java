package com.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AppInfo {
    private Integer id;
    private String softwareName;
    private String APKName;
    private String supportROM;
    private String interfaceLanguage;
    private Double softwareSize;
    private Date updateDate;
    private Integer devId;
    private String appInfo;
    private Integer  status;
    private Date onSaleDate;
    private Date offSaleDate;
    private Integer flatformId;
    private Integer downloads;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private Integer categoryLevel1;
    private Integer categoryLevel2;
    private Integer categoryLevel3;
    private String logoPicPath;
    private String logoLocPath;
    private Integer versionId;
}
