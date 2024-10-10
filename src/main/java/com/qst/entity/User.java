package com.qst.entity;

import java.sql.Timestamp;

public class User extends BaseEntity{

    public static final String PASSWORD="123456";
    private String login;
    private String name;
    private String passwd;
    //int 基本类型,除了基本类型都叫引用类型
    private int type;
    private int status;//0:禁用 1：正常
    private Timestamp lastLogin;

    //构造函数的初始化时间点是实体化，即创建一个对象的时候
    public User(){
    }

    public User(int id,String login,String name,String passwd,int type,int status){
        this.id=id;
        this.login=login;
        this.name=name;
        this.passwd=passwd;
        this.type=type;
        this.status=status;
    }


    //getter和setter 叫做成员方法
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public Timestamp getLastLogin(){
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin){
        this.lastLogin=lastLogin;
    }
    public void setLast_Login(Timestamp lastLogin){
        this.lastLogin=lastLogin;
    }
    static {
        addDesc(User.class,"s0","禁用","s1","正常","t1","用户管理员","t2","题库管理员","t3","题库使用者");
    }

    public String getTypeDesc(){
        return getDesc(User.class,"t",type);
    }

    public String getStatusDesc(){
        return getDesc(User.class,"s",status);
    }


}
