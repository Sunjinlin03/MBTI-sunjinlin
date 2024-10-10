package com.qst.controller;

import com.qst.BaseServlet;
import com.qst.Constant;
import com.qst.RequestUtil;
import com.qst.controller.QuestionHelper;
import com.qst.pojo.*;
import com.qst.service.AssessmentSsmService;
import com.qst.service.IQuestionSsmService;
import com.qst.service.PersonalityDimensionSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController extends BaseServlet {

    @Autowired
    private IQuestionSsmService questionSsmService;
    @Autowired
    private AssessmentSsmService assessmentSsmService;
    @Autowired
    private PersonalityDimensionSsmService dimensionSsmService;


    //查看题目列表
    //@RequestMapping("/list.action")
    //public String list1(HttpServletRequest req, HttpServletResponse resp) {
    //    String submiButton = RequestUtil.getString(req,"create");
    //    List<AssessmentType> assessmentList = assessmentSsmService.findAllAssessment();      //查询考核类型方法
    //    req.setAttribute("assessmentList",assessmentList);
    //    QuestionQueryParam param = QuestionQueryParam.create(req);
    //    req.setAttribute("query",param);
    //    List<Question> questionList = questionSsmService.find(param); //问题查询
    //    req.setAttribute("questionList",questionList);
    //    return "/question/list";
    //}

    //查看试题列表
    @RequestMapping("/list.action")
    public String list(HttpServletRequest req, HttpServletResponse resp) {
        String submitButton = RequestUtil.getString(req, "create");

        if (submitButton.length() > 0) {
            //doCreate(request, response);
            //点击了添加按钮
            List<AssessmentType> assessmentList = assessmentSsmService.findAllAssessment();
            req.setAttribute("assessmentList", assessmentList);
            QuestionQueryParam param = QuestionQueryParam.create(req);
            req.setAttribute("query", param);
            if (param.getAssessmentId()>0){
                List<Question> questionList = questionSsmService.find(param);
                req.setAttribute("questionList", questionList);
            }
            return "/question/create";

        } else {
            //doList(request, response);
            //点击查看按钮
            List<AssessmentType> assessmentList = assessmentSsmService.findAllAssessment();
            req.setAttribute("assessmentList",assessmentList);
            QuestionQueryParam param = QuestionQueryParam.create(req);
            req.setAttribute("query",param);
            //req.setAttribute("assessment", assessmentSsmService.findById(param.getAssessmentId()));
            //Question qs=new Question();
            //qs.setType(param.getType());
            //qs.setDifficulty(4);
            //req.setAttribute("question",qs);
            List<Question> questionList = questionSsmService.find(param);
            req.setAttribute("questionList", questionList);

            return "/question/list";
        }
    }


    //查看题目详情
    @RequestMapping("/view.action")
    public String view(HttpServletRequest req, HttpServletResponse resp) {
        Question question = questionSsmService.findById(RequestUtil.getInt(req,"id"));
        List<Choice> choices = questionSsmService.findChoices(question.getId());
        AssessmentType assessment = assessmentSsmService.findById(question.getAssessmentId());    //chaxun
        List<PersonalityDimension> dimension = questionSsmService.findDimensionByQuestion(question.getId());
        req.setAttribute("assessment",assessment);
        req.setAttribute("question",question);
        req.setAttribute("choices",choices);
        req.setAttribute("dimension",dimension);
        return "/question/view";
    }


    //添加试题
    @RequestMapping("/save.action")
    public String save(HttpServletRequest req, HttpServletResponse resp) {
        Question question= QuestionHelper.createQuestion(req);
        List<Choice> choices=QuestionHelper.createChoice(req);
        try {
            User currentUser=(User)req.getSession().getAttribute(Constant.CURRENT_USER);
            question.setUserId(currentUser.getId());
            questionSsmService.save(question,choices);
            addMessage(req,"试题保存在数据库中");
            //WebUtil.redirect(req,resp,"/question/dimension.action?id="+question.getId());
            return "redirect:/question/dimension.action?id="+question.getId();
        }catch (Exception ex){
            System.out.println(ex);
            addError(req,ex.getMessage());
            req.setAttribute("question",question);
            req.setAttribute("choices",choices);
            req.setAttribute("assessment",assessmentSsmService.findById(question.getAssessmentId()));
            return "/question/create";
        }
    }

    protected String save1(HttpServletRequest req, HttpServletResponse resp) {
        int qid = RequestUtil.getInt(req,"id");
        if (qid==0) {
            qid=RequestUtil.getInt(req,"qid");
        }
        int[] pids = RequestUtil.getIntArray(req,"pid");
        if (pids == null) {
            Question question = questionSsmService.findById(qid);
            List<PersonalityDimension> dimensionList = dimensionSsmService.findAllDimensionByAssessment(question.getAssessmentId());
            req.setAttribute("dimensionList", dimensionList);
            List<Integer> attachedDimension = questionSsmService.findDimensionIdByQuestion(qid);
            for (PersonalityDimension pd : dimensionList) {
                pd.getExtras().put("checked", attachedDimension.contains(pd.getId()) ? "checked" : "");
            }
            req.setAttribute("error", "你还未关联任何维度");
            req.setAttribute("qid", qid);
            return "/question/dimension";
        }

//        try {
//            questionSsmService.attachDimension(qid, pids);
//            System.out.println("Dimensions successfully attached");
//        } catch (Exception e) {
//            req.setAttribute("error", "Failed to attach dimensions: " + e.getMessage());
//            WebUtil.forward(req, resp, "/question/dimension.jsp");
//            return;
//        }
////        //添加维度
        questionSsmService.attachDimension(qid, pids);
        return "redirect:/question/view.action?id="+qid;
    }


    @RequestMapping("/dimension.action")
    public void dimension(HttpServletRequest req, HttpServletResponse resp) {
        String method = RequestUtil.getString(req,"method");
        if ("save".equals(method)) {
            save1(req,resp);
        }else {
            edit(req,resp);
        }
    }


    protected String  edit(HttpServletRequest req, HttpServletResponse resp) {
        int id = RequestUtil.getInt(req, "id");
        Question question = questionSsmService.findById(id);
        req.setAttribute("question", question);
        List<PersonalityDimension> dimensionList = dimensionSsmService.findAllDimensionByAssessment(question.getAssessmentId());
        req.setAttribute("dimensionList", dimensionList);
        List<Integer> attachedDimension = questionSsmService.findDimensionIdByQuestion(id);
        for (PersonalityDimension p : dimensionList) {
            p.getExtras().put("checked", attachedDimension.contains(p.getId()) ? "checked" : "");
        }

        //WebUtil.forward(req, resp, "/question/dimension.jsp");
        return "/question/dimension";
    }



    //删除试题
    @RequestMapping("/delete.action")
    public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= RequestUtil.getInt(req,"id");
        Question question=questionSsmService.findById(id);
        questionSsmService.delete(id);
        String url="/question/list.action?assessmentId="+ question.getAssessmentId()+"&status="+question.getStatus()+"&type="+question.getType();
        return "redirect:"+url;
    }



}
