package com.example.entity.po;

import java.util.Date;

public class AppCodeEfficacy {
   private String TellNumber;
   private String TellCode;
   private Date   CodeTime;

    public String getTellNumber() {
        return TellNumber;
    }

    public void setTellNumber(String tellNumber) {
        TellNumber = tellNumber;
    }

    public String getTellCode() {
        return TellCode;
    }

    public void setTellCode(String tellCode) {
        TellCode = tellCode;
    }

    public Date getCodeTime() {
        return CodeTime;
    }

    public void setCodeTime(Date codeTime) {
        CodeTime = codeTime;
    }

    @Override
    public String toString() {
        return "AppCodeEfficacy{" +
                "TellNumber='" + TellNumber + '\'' +
                ", TellCode='" + TellCode + '\'' +
                ", CodeTime=" + CodeTime +
                '}';
    }
}
