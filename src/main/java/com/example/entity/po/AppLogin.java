package com.example.entity.po;

import java.io.Serializable;

public class AppLogin implements Serializable {
    private Integer login_id;

    private String login_name;

    private String login_password;

    private String login_tell;

    private String login_type;

    private Integer u_id;

    private static final long serialVersionUID = 1L;

    public Integer getLogin_id() {
        return login_id;
    }

    public void setLogin_id(Integer login_id) {
        this.login_id = login_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name == null ? null : login_name.trim();
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password == null ? null : login_password.trim();
    }

    public String getLogin_tell() {
        return login_tell;
    }

    public void setLogin_tell(String login_tell) {
        this.login_tell = login_tell == null ? null : login_tell.trim();
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type == null ? null : login_type.trim();
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "AppLogin{" +
                "login_id=" + login_id +
                ", login_name='" + login_name + '\'' +
                ", login_password='" + login_password + '\'' +
                ", login_tell='" + login_tell + '\'' +
                ", login_type='" + login_type + '\'' +
                ", u_id=" + u_id +
                '}';
    }
}