package com.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class AdPromotion {
    private Integer id;
    private Integer appId;
    private String adPicPath;
    private Integer adPV;
    private Integer carouselPosition;
    private Date startTime;
    private Date endTime;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
}
