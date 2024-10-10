package com.qst.action.question;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.PersonalityDimension;
import com.qst.entity.Question;
import com.qst.service.IDimensionService;
import com.qst.service.IQuestionService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebServlet("/question/dimension.action")
public class DimensionServlet extends BaseServlet {
    private IDimensionService dimensionService = ServiceFactory.getService(IDimensionService.class);
    private IQuestionService questionService = ServiceFactory.getService(IQuestionService.class);


    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = RequestUtil.getString(request, "method");
        if ("save".equals(method)) {
            save(request, response);
        } else {
            edit(request, response);
        }

    }


    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = RequestUtil.getInt(req, "id");
        Question question = questionService.findById(id);
        req.setAttribute("question", question);
        List<PersonalityDimension> dimensionList = dimensionService.findDimensionByAssessmentId(question.getAssessmentId());
        req.setAttribute("dimensionList", dimensionList);
        List<Integer> attachedDimension = questionService.findDimensionIdByQuestion(id);
        for (PersonalityDimension p : dimensionList) {
            p.getExtras().put("checked", attachedDimension.contains(p.getId()) ? "checked" : "");
        }

        WebUtil.forward(req, resp, "/question/dimension.jsp");
    }

    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int qid = RequestUtil.getInt(req, "id");
        System.out.println("Question ID: " + qid);

        if (qid == 0) {
            qid = RequestUtil.getInt(req, "qid");
        }
        int[] pids = RequestUtil.getIntArray(req, "pid");

        if (pids == null) {
            Question question = questionService.findById(qid);
            List<PersonalityDimension> dimensionList = dimensionService.findDimensionByAssessmentId(question.getAssessmentId());
            System.out.println("Dimension List: " + dimensionList);
            req.setAttribute("dimensionList", dimensionList);

            List<Integer> attachedDimension = questionService.findDimensionIdByQuestion(qid);
            System.out.println("PID Parameter Values: " + Arrays.toString(req.getParameterValues("pid")));
            System.out.println("Dimension IDs to Attach: " + Arrays.toString(pids));


            for (PersonalityDimension pd : dimensionList) {
                pd.getExtras().put("checked", attachedDimension.contains(pd.getId()) ? "checked" : "");
            }
            req.setAttribute("error", "你还未关联任何维度");
            req.setAttribute("qid", qid);
            WebUtil.forward(req, resp, "/question/dimension.jsp");
        }

        try {
            questionService.attachDimension(qid, pids);
            System.out.println("Dimensions successfully attached");
        } catch (Exception e) {
            req.setAttribute("error", "Failed to attach dimensions: " + e.getMessage());
            WebUtil.forward(req, resp, "/question/dimension.jsp");
            return;
        }
//        //添加维度
//        questionService.attachDimension(qid, pids);
        WebUtil.redirect(req, resp, "/question/view.action?id=" + qid);
    }


}
