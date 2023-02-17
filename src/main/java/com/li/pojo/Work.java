package com.li.pojo;

public class Work {

    private Integer wid;
    private String workName;
    private String writer;
    private String type;
    private String themeStr;
    private String[] themes;
    private String workDescription;
    private String content;

    public Work() {
    }

    public Work(Integer wid, String workName, String writer, String type, String themeStr, String workDescription, String content) {
        this.wid = wid;
        this.workName = workName;
        this.writer = writer;
        this.type = type;
        this.themeStr = themeStr;
        this.workDescription = workDescription;
        this.content = content;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThemeStr() {
        return themeStr;
    }

    public String getTheme() {
        StringBuilder sb = new StringBuilder();
        if( themeStr != null ) {
            String[] split = themeStr.split(";");
            for (String theme : split) {
                sb.append(theme);
            }
        }
        return sb.toString();
    }

    public void setThemeStr(String themeStr) {
        this.themeStr = themeStr;
    }

    public String[] getThemes() {
        return themes;
    }

    public void setThemes(String[] themes) {
        this.themes = themes;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Work{" +
                "wid=" + wid +
                ", workName='" + workName + '\'' +
                ", writer='" + writer + '\'' +
                ", type='" + type + '\'' +
                ", themeStr='" + themeStr + '\'' +
                ", workDescription='" + workDescription + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
