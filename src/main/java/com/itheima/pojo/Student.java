package com.itheima.pojo;

public class Student {
    String Sid;
    String Sname;
    String Sage;
    String Ssex;
    String Sclass;
    String Sdept;
    String Saddr;

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }


    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSage() {
        return Sage;
    }

    public void setSage(String sage) {
        Sage = sage;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public String getSclass() {
        return Sclass;
    }

    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    public String getSdept() {
        return Sdept;
    }

    public void setSdept(String sdept) {
        Sdept = sdept;
    }

    public String getSaddr() {
        return Saddr;
    }

    public void setSaddr(String saddr) {
        Saddr = saddr;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Sid=" + Sid +
                ", Sname='" + Sname + '\'' +
                ", Sage='" + Sage + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", Sclass='" + Sclass + '\'' +
                ", Sdept='" + Sdept + '\'' +
                ", Saddr='" + Saddr + '\'' +
                '}';
    }
}
