package com.qst.controller;

import com.qst.BaseServlet;
import com.qst.pojo.AssessmentType;
import com.qst.service.AssessmentSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/assessment/")
public class AssessmentController extends BaseServlet {

    @Autowired
    private AssessmentSsmService assessmentSsmService;


    @RequestMapping("list.action")
    public String list(Model model) {
        List<AssessmentType> assessmentList = assessmentSsmService.findAllAssessment();
        model.addAttribute("assessmentList", assessmentList);
        return "assessment/list";
    }

    @RequestMapping("view.action")
    public String view(String id , Model model) {

        AssessmentType assessment = assessmentSsmService.findById(Integer.parseInt(id));
        model.addAttribute("assessment", assessment);
        System.out.println(assessment);
        return "assessment/view";
    }

    @RequestMapping("delete.action")
    public String delete(String id) {

        assessmentSsmService.deleteAssessment(Integer.parseInt(id));
        return "forward:list.action";
    }

    @RequestMapping("create.action")
    public String create(Model model) {
        AssessmentType assessment = new AssessmentType();
        model.addAttribute("assessment", assessment);
        return "assessment/create";
    }

    @RequestMapping("save.action")
    public String save(AssessmentType assessment , HttpSession session) {

        assessmentSsmService.saveAssessment(assessment);
        session.setAttribute(BaseServlet.MSG_KEY,"考核类型已保存");
        return "redirect:view.action?id="+assessment.getId();
    }

    @RequestMapping("edit.action")
    public String edit(String id,Model model) {
        AssessmentType assessment = assessmentSsmService.findById(Integer.parseInt(id));
        model.addAttribute("assessment", assessment);
        return "assessment/edit";
    }

    @RequestMapping("update.action")
    public String update(AssessmentType assessment, HttpSession session) {

        assessmentSsmService.updateAssessmentService(assessment);
        session.setAttribute(BaseServlet.MSG_KEY,"修改成功");
        return "forward:view.action";
    }



}
