package com.itheima.pojo;

public class Course {
    String Cid;
    String Cname;
    String Credit;
    int Chours;
    String Tid;

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }

    public int getChours() {
        return Chours;
    }

    public void setChours(int chours) {
        Chours = chours;
    }

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    @Override
    public String toString() {
        return "course{" +
                "Cid='" + Cid + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Credit='" + Credit + '\'' +
                ", Chours='" + Chours + '\'' +
                ", Tid='" + Tid + '\'' +
                '}';
    }
}
