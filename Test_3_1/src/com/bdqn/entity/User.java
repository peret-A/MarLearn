package com.bdqn.entity;

public class User {
/**
 * @author liuziyang
 * @data 2024-03-01-9:36
 */
private int uid;
    /** 用户名 */
    private String name;
    /** 密码 */
    private String password;
    /** 手机号码 */
    private String phone;

    // 无参构造方法
    public User() {
        super();
    }

    // 有参构造方法
    public User(String name, String password, String phone) {
        super();
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    // 有参构造方法
    public User(int uid, String name, String password, String phone) {
        super();
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    // getXxx()/setXxx()方法
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 重写toString()方法
    @Override
    public String toString() {
        return "User [uid=" + uid + ", name=" + name + ", password=" + password
                + ", phone=" + phone + "]";
    }
}
