package com.bdqn.entity;

public class People{
private int pid;
private String name;
private int age;
private String gender;
private java.util.Date birthday;
private String identitycrd;
private String phone;
private String address;
public int getPid(){
        return this.pid;
    }
public void setPid(int pid){
        this.pid = pid;
    }
public String getName(){
        return this.name;
    }
public void setName(String name){
        this.name = name;
    }
public int getAge(){
        return this.age;
    }
public void setAge(int age){
        this.age = age;
    }
public String getGender(){
        return this.gender;
    }
public void setGender(String gender){
        this.gender = gender;
    }
public java.util.Date getBirthday(){
        return this.birthday;
    }
public void setBirthday(java.util.Date birthday){
        this.birthday = birthday;
    }
public String getIdentitycrd(){
        return this.identitycrd;
    }
public void setIdentitycrd(String identitycrd){
        this.identitycrd = identitycrd;
    }
public String getPhone(){
        return this.phone;
    }
public void setPhone(String phone){
        this.phone = phone;
    }
public String getAddress(){
        return this.address;
    }
public void setAddress(String address){
        this.address = address;
    }
}
