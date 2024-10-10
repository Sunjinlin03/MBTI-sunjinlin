package com.qst.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//用于传递ID
public class BaseEntity implements Serializable {

    protected Integer id;
    //传递两个空的静态Map对象
    //传递数据用
    private Map<Object,Object> extras=new HashMap<>();
    //转换代号到描述信息
    private static Map<String,String> desc=new HashMap<>();

    protected static String getDesc(Class clazz,String prefix,int code){
        return desc.get(clazz.getName()+"-"+prefix+code);
    }

    protected static void addDesc(Class clazz,String... descs){
        for (int i=1;i<descs.length;i+=2){
            desc.put(clazz.getName()+"-"+descs[i-1],descs[i]);
        }
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public Map<Object,Object> getExtras(){
        return extras;
    }

}
