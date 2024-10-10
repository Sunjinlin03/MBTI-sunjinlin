package com.qst.controller;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.pojo.AssessmentType;
import com.qst.pojo.PersonalityDimension;
import com.qst.service.AssessmentSsmService;
import com.qst.service.PersonalityDimensionSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/dimension/")
public class DimensionController extends BaseServlet {

    @Autowired
    private PersonalityDimensionSsmService personalityDimensionSsmService;

    @Autowired
    private AssessmentSsmService assessmentSsmService;


    @RequestMapping("create.action")
    public String create(String id, Model model) {

            AssessmentType assessment = assessmentSsmService.findById(Integer.parseInt(id));
            model.addAttribute("assessment", assessment);
        System.out.println(assessment);
            return "dimension/create";

    }


    @RequestMapping("list.action")
    public String list(String id,Model model) {

        model.addAttribute("assessmentList", assessmentSsmService.findAllAssessment());

        if (id != null && !id.equals("")) {
            List<PersonalityDimension> dimensionList = personalityDimensionSsmService.findAllDimensionByAssessment(Integer.parseInt(id));
            model.addAttribute("dimensionList", dimensionList);
        }else {
            List<PersonalityDimension> dimensionList = personalityDimensionSsmService.findAllDimension();
            model.addAttribute("dimensionList", dimensionList);
        }
        return "dimension/list";
    }




    @RequestMapping("view.action")
    public String view(String id, Model model) {

        PersonalityDimension dimension = personalityDimensionSsmService.findById(Integer.parseInt(id));
        model.addAttribute("dimension", dimension);
        return "dimension/view";
    }

    @RequestMapping("delete.action")
    public String delete(String id, Model model,HttpSession session) {
        PersonalityDimension dimension = new PersonalityDimension();
        dimension.setId(Integer.valueOf(id));
        personalityDimensionSsmService.deleteDimension(dimension.getId());
        session.setAttribute(BaseServlet.MSG_KEY,"删除成功");
        model.addAttribute("dimension", dimension);
        return "forward:list.action";
    }

    @RequestMapping("edit.action")
    public String edit(String id, Model model) {
        PersonalityDimension dimension = personalityDimensionSsmService.findById(Integer.parseInt(id));
        model.addAttribute("dimension", dimension);
        return "dimension/edit";
    }

    @RequestMapping("update.action")
    public String update(PersonalityDimension dimension, Model model, HttpSession session) {

        personalityDimensionSsmService.updateDimension(dimension);
        session.setAttribute(BaseServlet.MSG_KEY,"信息已修改");
//        return "redirect:view.action?id=" + dimension.getId();
        return "forward:view.action";
    }


    @RequestMapping("save.action")
    public String save(PersonalityDimension dimension,HttpSession session) throws ServletException {

        personalityDimensionSsmService.addDimension(dimension);
        session.setAttribute(BaseServlet.MSG_KEY,"信息已保存");
        return "redirect:view.action?id=" + dimension.getId();
    }

}
