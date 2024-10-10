package com.qst.service;


import com.qst.service.impl.*;

import java.util.HashMap;
import java.util.Map;

//获取接口的实例化对象
public class ServiceFactory {
    private static Map<Class,Object> services=new HashMap<Class, Object>();
    static {//添加相关的方法，便于生成接口类的实例
        services.put(IUserService.class,new UserServiceImpl());
        services.put(ITestPersonnelService.class,new TestPersonnelServiceImpl());
        services.put(IUserAdminService.class,new UserAdminServiceImpl());
        services.put(IAssessmentTypeService.class,new AssessmentTypeServiceImpl());
        services.put(IDimensionService.class,new DimensionServiceImpl());
        services.put(IQuestionService.class,new QuestionServiceImpl());
        services.put(IScheduleService.class,new ScheduleServiceImpl());
        services.put(ITeamService.class,new TeamServiceImpl());
        services.put(IExamService.class,new ExamServiceImpl());
    }

    public static <T> T getService(Class<T> clazz){
        return (T) services.get(clazz);
    }

}
