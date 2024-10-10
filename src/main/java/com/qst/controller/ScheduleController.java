package com.qst.controller;


import com.qst.*;
import com.qst.pojo.*;
import com.qst.service.AssessmentSsmService;
import com.qst.service.IScheduleSsmService;
import com.qst.service.ITeamSsmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/schedule/")
public class ScheduleController {
    @Autowired
    private IScheduleSsmService scheduleSsmService;

    @Autowired
    private AssessmentSsmService assessmentSsmService;
    @Autowired
    private ITeamSsmService teamSsmService;

    @RequestMapping("list.action")
    public String list(HttpServletRequest req ,Model model){
        User user = (User) req.getSession().getAttribute(Constant.CURRENT_USER);
        List<Schedule> scheduleList = scheduleSsmService.findByCreator(user);
        model.addAttribute("scheduleList",scheduleList);
        return "/schedule/list";
    }

    @RequestMapping("view.action")
    public String View(Integer id,Model model){
        if(id==null&&"".equals(id)){
            throw new ExamException("入参错误");
        }
        //通过id获取用户的详情
        Schedule schedule=scheduleSsmService.findById(id);
        model.addAttribute("schedule",schedule);
        return "/schedule/view";
    }

    @RequestMapping("create.action")
    public String create(Schedule scheduleDTO,Model model, HttpServletRequest req){
        User user = (User) req.getSession().getAttribute(Constant.CURRENT_USER);
            Schedule schedule =new Schedule();
            Date today = new Date(System.currentTimeMillis());
            SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            schedule.setBeginDate(stf.format(today));
            schedule.setEndDate(stf.format(today));
            schedule.setCreateDate(stf.format(today));
            model.addAttribute("schedule", schedule);
        List<Team> teamList = teamSsmService.findByCreator(user.getId());
        model.addAttribute("teamList", teamList);
        List<AssessmentType> assessmentTypeList = assessmentSsmService.findAllAssessment();
        model.addAttribute("assessmentList", assessmentTypeList);

        return "/schedule/create";
    }
    @RequestMapping("save.action")
    public String save(Schedule schedule , HttpServletRequest request){
            User  user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
            schedule.setCreatorId(user.getId());
            scheduleSsmService.saveSchedule(schedule);
            return "forward:/schedule/list.action";
    }
    @RequestMapping("edit.action")
    public String edit(ScheduleDTO scheduleDTO ,Model model,HttpServletRequest req) {
        User  user = (User) req.getSession().getAttribute(Constant.CURRENT_USER);
        Integer id = RequestUtil.getInt(req, "id");
        Schedule schedule = (Schedule) req.getAttribute("schedule");
        if (schedule == null) {
            schedule = scheduleSsmService.findById((id));
            model.addAttribute("schedule", schedule);
        }
        List<Team> teamList = teamSsmService.findByCreator(user.getId());
        model.addAttribute("teamList", teamList);
        List<AssessmentType> assessmentList = assessmentSsmService.findAllAssessment();
        model.addAttribute("assessmentList", assessmentList);
        return "/schedule/edit";
    }
    @RequestMapping("update.action")
    public String update(Schedule schedule , HttpServletRequest req){
            User user = (User) req.getSession().getAttribute(Constant.CURRENT_USER);
            schedule.setCreatorId(user.getId());
            scheduleSsmService.updateSchedule(schedule);
            req.setAttribute("schedule", schedule);
            return "redirect:/schedule/view.action?id=" + schedule.getId();

    }
    @RequestMapping("delete.action")
    public String delete(Integer id){
        scheduleSsmService.deleteSchedule(id);
        return "redirect:/schedule/list.action";

    }

}
