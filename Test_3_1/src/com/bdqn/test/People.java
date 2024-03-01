package com.bdqn.test;

import java.util.Date;

public class People {
/**
 * @author liuziyang
 * @data 2024-03-01-14:46
 */
    /** 编号 */
    private int pid;
    /** 姓名 */
    private String name;
    /** 年龄 */
    private int age;
    /** 性别 */
    private String gender;
    /** 出生日期 */
    private Date birthday;
    /** 身份证号码 */
    private String identitycard;
    /** 手机号码 */
    private String phone;
    /** 住址 */
    private String address;

    // 无参构造方法
    public People() {
        super();
    }

    // 有参构造方法
    public People(String name, int age, String gender, Date birthday,
                  String identitycard, String phone, String address) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.identitycard = identitycard;
        this.phone = phone;
        this.address = address;
    }

    // 有参构造方法
    public People(int pid, String name, int age, String gender, Date birthday,
                  String identitycard, String phone, String address) {
        super();
        this.pid = pid;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.identitycard = identitycard;
        this.phone = phone;
        this.address = address;
    }

    // getXxx()/setXxx()方法
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // 重写toString()方法
    @Override
    public String toString() {
        return "People [pid=" + pid + ", name=" + name + ", age=" + age
                + ", gender=" + gender + ", birthday=" + birthday
                + ", identitycard=" + identitycard + ", phone=" + phone
                + ", address=" + address + "]";
    }

}
