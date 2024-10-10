package com.qst.controller;


import com.qst.Constant;
import com.qst.ExamException;
import com.qst.RequestUtil;
import com.qst.mapper.UserMapper;
import com.qst.pojo.Team;
import com.qst.pojo.TestPersonnel;
import com.qst.pojo.User;
import com.qst.service.ITeamSsmService;
import com.qst.service.ITestPersonnelSsmService;
import com.qst.service.IUserAdminSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserAdminSsmService iUserAdminService;

    @Autowired
    private ITeamSsmService teamSsmService;

    @Autowired
    private ITestPersonnelSsmService testPersonnelSsmService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/list.action")
    public String list(HttpServletRequest request, HttpServletResponse response) {


        List<User> users = iUserAdminService.findUsers();
        request.setAttribute("userList", users);
        //跳转页面
        return "/user/list";
    }


    //SpringMvc原生写法
    //查看页面
    @RequestMapping("/view.action")
    public String view(String id, Model model) {
        if (id==null&&"".equals(id)){
            throw new ExamException("入参错误");
        }

        //类型转换
        int i=Integer.parseInt(id);

        //拿到前端传来的参数id,跳转详细页面
        User user = iUserAdminService.findUserById(i);
        model.addAttribute("user",user);
        return "/user/view";
    }

//    @RequestMapping("/view.action")
//    public String view(HttpServletRequest request, HttpServletResponse response) {
//
//        //拿到前端传来的参数id
//        int id = RequestUtil.getInt(request, "id");
//
//        User user = iUserAdminService.findUserById(id);
//        request.setAttribute("user", user);
//        return "/user/view";
//    }

    //跳转
    @RequestMapping("/create.action")
    public String create(HttpServletRequest request, HttpServletResponse response) {
        //赋默认值
        User user = new User();
        user.setStatus(1);
        user.setType(1);
        request.setAttribute("user", user);


        return "/user/create";
    }

    //跳转
    @RequestMapping("/save.action")
    public String save(HttpServletRequest request, HttpServletResponse response) {
        //获取前端传参
        User user = new User();
        user.setType(RequestUtil.getInt(request, "type"));
        user.setStatus(RequestUtil.getInt(request, "status"));
        user.setLogin(RequestUtil.getString(request, "login"));
        user.setName(RequestUtil.getString(request, "name"));
        user.setPasswd(com.qst.pojo.User.PASSWORD);


        //保存用户
        iUserAdminService.saveUser(user);
        String path="forward:/user/view.action?id="+ user.getId();
        return path;
    }


    @RequestMapping("/delete.action")
    public String delete(HttpServletRequest request, HttpServletResponse response) {

        //获取前台传递的id
        int id = RequestUtil.getInt(request, "id");


        //删除对应的id
        iUserAdminService.deleteUserById(id);

        return "redirect:/user/list.action";
    }

    //跳转到修改页面
    @RequestMapping("/edit.action")
    public String edit(HttpServletRequest request, HttpServletResponse response) {

        //拿到前端传来的参数id
        int id = RequestUtil.getInt(request, "id");
        User user = iUserAdminService.findUserById(id);
        request.setAttribute("user", user);
        return "/user/edit";
    }



    @RequestMapping("/update.action")
    public String update(User user) {
        iUserAdminService.updateUser(user);
        String path="redirect:/user/view.action?id=" + user.getId();
        return path;

    }
//    @RequestMapping("/update.action")
//    public String update(HttpServletRequest request, HttpServletResponse response) {
//
//        User user = new User();
//        user.setType(RequestUtil.getInt(request, "type"));
//        user.setStatus(RequestUtil.getInt(request, "status"));
//        user.setLogin(RequestUtil.getString(request, "login"));
//        user.setName(RequestUtil.getString(request, "name"));
//        user.setId(RequestUtil.getInt(request, "id"));
//
//
//        try {
//            //更新用户
//            iUserAdminService.updateUser(user);
//            return "redirect:/user/view.action?id=" + user.getId();
//        } catch (Exception ex) {
//            request.setAttribute("user", user);
//            return "forward:/user/edit";
//        }
//
//    }

    //    重置密码
    @RequestMapping("/password.action")
    public String password(HttpServletRequest request, HttpServletResponse response) {
        int id=RequestUtil.getInt(request,"id");

        //重置对应id的密码为初始化密码
        iUserAdminService.resetPassword(id);
        return "redirect:/user/list.action";
    }


    @RequestMapping("/showReg.do")
    public String ShowReg(Model model){
        List<Team> teamList=teamSsmService.findAll();
        model.addAttribute("teamList",teamList);

        return "user/reg";
    }

    @RequestMapping("/reg.do")
    public String register(HttpServletRequest req, HttpSession session,
                           @RequestParam("login") String login,
                           @RequestParam("passwd") String passwd,
                           @RequestParam("repasswd") String repasswd,
                           @RequestParam("name") String name,
                           @RequestParam("gender") String gender,
                           @RequestParam("teamId") int teamId,
                           Model model) {

        if (!passwd.equals(repasswd)) {
            model.addAttribute("message", "两次输入的密码不一致！");
            return "user/reg"; // 返回注册页面
        }

        User user = new User();
        user.setLogin(login);
        user.setPasswd(passwd);
        user.setName(name);

        TestPersonnel testPersonnel = new TestPersonnel();
        testPersonnel.setUser(user);
        testPersonnel.setGender(gender);
        testPersonnel.setPhone(login);
        testPersonnel.setTeamId(teamId);


        try {
            if (userMapper.findByLogin(testPersonnel.getPhone()) != null) {
                model.addAttribute("message", "该手机号已被注册！");
                return "user/reg"; // 返回注册页面
            } else {
                testPersonnelSsmService.addTestPersonnel(testPersonnel);
                model.addAttribute("message", "用户信息已保存");
                session.setAttribute(Constant.CURRENT_USER, user);
                session.setAttribute(Constant.CURRENT_TESTPERSONNEL, testPersonnel);
                return "redirect:/login.jsp"; // 重定向到登录页面
            }
        } catch (ExamException ex) {
            model.addAttribute("user", user);
            model.addAttribute("error", ex.getMessage());
            return "user/create"; // 返回注册页面
        }
    }

    }
