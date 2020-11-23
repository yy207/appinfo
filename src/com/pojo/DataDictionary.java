package com.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class DataDictionary {
    private Integer id;
    private String typeCode;
    private String typeName;
    private Integer valueId;
    private String valueName;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
}
