package com.qst.controller;

import com.qst.BaseServlet;
import com.qst.pojo.Team;
import com.qst.pojo.User;

import com.qst.service.ITeamSsmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController extends BaseServlet {

    @Autowired
    private ITeamSsmService teamService;

    // 显示团队列表
    @RequestMapping ("/list.action")
    public String List(HttpServletRequest req, Model model) {
        // 获取当前登录的用户
        User user = getCurrentUser(req);  // 假设当前用户存储在Session中
        System.out.println("获取到用户类型"+user.getType()+"当前登录用户"+user.getId());
        try {
            List<Team> teamList;

            if (user.getType() == 1) {  // 如果用户类型为1，显示所有
                teamList = teamService.findAll();
                System.out.println(teamList);
            } else {  // 否则只显示创建者为该用户的
                teamList = teamService.findByCreator(user.getId());
                System.out.println(teamList);
            }
            model.addAttribute("teamList", teamList);  // 将列表传递给视图
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());  // 将错误信息传递给视图
        }

        return "/team/list";  // 返回视图名，Spring 会自动解析成对应的 JSP 文件
    }


    @RequestMapping("/view.action")
    public String View(@RequestParam("id") Integer id, Model model) {
        try {
            Team team = teamService.findById(id);
            model.addAttribute("team", team);  // 将数据传递给视图
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());  // 将错误信息传递给视图
        }
        return "/team/view";  // 返回视图名，Spring 会自动解析到对应的 JSP 文件
    }


    @RequestMapping("/create.action")
    public String Create(@ModelAttribute("team") Team team, Model model) {
        try {
            teamService.save(team);  // 保存新创建的团队
            model.addAttribute("successMessage", "Team created successfully.");
            return "redirect:/team/list";  // 重定向到列表页面
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());  // 捕获异常并显示错误信息
            return "/team/create";  // 返回创建表单，保持用户输入
        }
    }

    @RequestMapping("/edit.action")
    public String Edit(@RequestParam("id") Integer id, Model model) {
        try {
            Team team = teamService.findById(id);
            model.addAttribute("team", team);  // 将数据传递给视图
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());  // 将错误信息传递给视图
        }
        return "/team/edit";  // 返回视图名，Spring 会自动解析到对应的 JSP 文件
    }


    @RequestMapping( "/update.action")
    public String Update(@ModelAttribute("team") Team team, Model model) {
        try {
            teamService.update(team);  // 更新信息
            model.addAttribute("successMessage", "Team updated successfully.");
            return "redirect:/team/view.action?id=" + team.getId();  // 重定向到详细视图
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());  // 捕获异常并显示错误信息
            return "/team/edit";  // 返回编辑表单，保持用户输入
        }
    }

    @RequestMapping("/save.action")
    public String Save(@ModelAttribute("team") Team team, HttpServletRequest req, Model model) {
        // 获取当前用户
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");

        team.setCreatorId(user.getId());  // 设置创建者ID

        try {
            teamService.save(team);  // 保存新创建的团队
            model.addAttribute("successMessage", "Team created successfully.");
            return "redirect:/team/view.action?id=" + team.getId();  // 重定向到团队详细视图
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());  // 捕获异常并显示错误信息
            return "/team/create";  // 返回创建表单，保持用户输入
        }
    }


    @RequestMapping("/delete.action")
    public String Delete(@RequestParam("id") Integer id, Model model) {
        try {
            teamService.delete(id);  // 删除指定的团队
            model.addAttribute("successMessage", "Team deleted successfully.");
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());  // 捕获异常并显示错误信息
        }
        return "redirect:/team/list";  // 重定向到列表页面
    }
}
