package com.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AppCategory {

    private Integer id;
    private Integer categoryCode;
    private String categoryName;
    private Integer parentId;
    private Integer createdBy;
    private Date creationTime;
    private Integer modifyBy;
    private Date modifyDate;
}
