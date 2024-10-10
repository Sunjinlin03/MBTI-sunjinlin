package com.qst.action.question;

import com.qst.RequestUtil;
import com.qst.entity.Choice;
import com.qst.entity.Question;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class QuestionHelper {

    public static Question createQuestion(HttpServletRequest request) {
        Question qs = new Question();
        qs.setId(RequestUtil.getInt(request, "id"));
        qs.setTitle(RequestUtil.getString(request, "title"));
        qs.setDifficulty(RequestUtil.getInt(request, "difficulty"));
        qs.setAssessmentId(RequestUtil.getInt(request, "assessmentId"));
        qs.setType(RequestUtil.getInt(request, "type"));
        qs.setStatus(2);
        qs.setHint(RequestUtil.getString(request, "hint"));
        return qs;
    }

    public static List<Choice> createChoice(HttpServletRequest request){
        List<Choice> choiceList=new ArrayList<>();
        for (int i=0;i<4;i++){
            Choice choice=new Choice();
            choice.setId(RequestUtil.getInt(request,"id["+i+"]"));
            choice.setTitle(RequestUtil.getString(request,"title["+i+"]"));
            choice.setChecked(RequestUtil.getInt(request,"checked["+i+"]")==1);
            choiceList.add(choice);
        }
        return choiceList;
    }

    }
