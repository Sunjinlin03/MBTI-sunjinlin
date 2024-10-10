package com.qst.dao;

import java.util.HashMap;
import java.util.Map;

//生成Dao类的实例化对象
public class DAOFactory {
    private static Map<Class,Object> daos=new HashMap<>();
    static {
        daos.put(UserDao.class,new UserDao());
        daos.put(TestPersonnelDao.class,new TestPersonnelDao());
        daos.put(AssessmentTypeDao.class,new AssessmentTypeDao());
        daos.put(DimensionDao.class,new DimensionDao());
        daos.put(QuestionDao.class,new QuestionDao());
        daos.put(ChoiceDao.class,new ChoiceDao());
        daos.put(ScheduleDao.class,new ScheduleDao());
        daos.put(TeamDao.class,new TeamDao());
        daos.put(ExamDao.class, new ExamDao());
        daos.put(ExamQuestionDao.class,new ExamQuestionDao());
    }

    //<T>：T表示同用泛型Object，clazz是它的反射值
    public static <T> T getDao(Class<T> clazz){
        return (T) daos.get(clazz);
    }
}
