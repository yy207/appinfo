package com.pojo;

public class PruductCategory {
    private  String  id;
    private  String  pddid;
    private  String  fid;
    private  String  name;
    private  String  title;
    private String img;
    private String level;

    public PruductCategory() {
    }

    public PruductCategory(String id, String pddid, String fid, String name, String title, String img,String level) {
        this.id = id;
        this.pddid = pddid;
        this.fid = fid;
        this.name = name;
        this.title = title;
        this.img = img;
        this.level = level;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPddid() {
        return pddid;
    }

    public void setPddid(String pddid) {
        this.pddid = pddid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
