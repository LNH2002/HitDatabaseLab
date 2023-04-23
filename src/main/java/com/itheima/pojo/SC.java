package com.itheima.pojo;

public class SC {
    String Sid;
    String Cid;
    String Score;

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    @Override
    public String toString() {
        return "sc{" +
                "Sid='" + Sid + '\'' +
                ", Cid='" + Cid + '\'' +
                ", Score='" + Score + '\'' +
                '}';
    }
}
