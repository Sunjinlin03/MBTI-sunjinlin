package com.qst.controller;


import com.qst.ExamException;
import com.qst.RequestUtil;
import com.qst.mapper.UserMapper;
import com.qst.pojo.Team;
import com.qst.pojo.TestPersonnel;
import com.qst.pojo.User;
import com.qst.service.ITeamSsmService;
import com.qst.service.ITestPersonnelSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialStruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/testPersonnel")
public class TestPersonnelController {

    @Autowired
    private ITestPersonnelSsmService testPersonnelSsmService;
    @Autowired
    private ITeamSsmService teamSsmService;
    @Autowired
    private UserMapper userMapper;


    @RequestMapping(value = "/list.action", method = RequestMethod.GET)
    public String list(HttpServletRequest request, HttpServletResponse response) {

        int tid = RequestUtil.getInt(request, "tid");

        List<TestPersonnel> testPersonnelList = testPersonnelSsmService.findByTeamId(tid);
        request.setAttribute("testPersonnel", testPersonnelList);
        List<Team> teams = teamSsmService.findAll();
        request.setAttribute("teamList", teams);

        return "/testPersonnel/list";
    }


    @RequestMapping("/view.action")
    public String view(HttpServletRequest request, HttpServletResponse response) {

        int id = RequestUtil.getInt(request, "id");
        TestPersonnel testPersonnel = testPersonnelSsmService.findById(id);

        // 格式化日期
        if (testPersonnel.getBirthDate() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(testPersonnel.getBirthDate());
            testPersonnel.setFormattedDate(formattedDate); // 假设你增加了一个新的属性来存储格式化后的日期
        }

        request.setAttribute("testPersonnel", testPersonnel);

        return "/testPersonnel/view";
    }

    @RequestMapping("/select.action")
    public ModelAndView select(
            @RequestParam(value = "teamId", defaultValue = "0") int teamId,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "phone", defaultValue = "") String phone) {

        // 调用 service 层方法进行查询
        List<TestPersonnel> testPersonnels = testPersonnelSsmService.query(teamId, name, phone);

        // 查询批次信息
        List<Team> teamList = teamSsmService.findAll();

        // 使用 ModelAndView 设置视图和数据
        ModelAndView modelAndView = new ModelAndView("/testPersonnel/list");
        modelAndView.addObject("testPersonnel", testPersonnels);
        modelAndView.addObject("teamList", teamList);

        return modelAndView;
    }


    @RequestMapping("/add.action")
    public String saveTestPersonnel(HttpServletRequest request, HttpServletResponse response) {

        //获取前端传参
        User user = new User();
        user.setId(RequestUtil.getInt(request, "id"));
        user.setLogin(RequestUtil.getString(request, "login"));
        user.setPasswd(User.PASSWORD);
        user.setName(RequestUtil.getString(request, "name"));
        user.setType(RequestUtil.getInt(request, "type"));
        user.setStatus(RequestUtil.getInt(request, "status"));

        TestPersonnel s = new TestPersonnel();
        s.setUser(user);
        s.setGender(RequestUtil.getString(request, "gender"));
        s.setPhone(RequestUtil.getString(request, "login")); // 这里使用 login 作为手机号是正确的吗？
        s.setTeamId(RequestUtil.getInt(request, "teamId"));
        s.setBirthDate(RequestUtil.getDate(request, "birthDate"));


        User existingUser = userMapper.findByLogin(s.getPhone());
        //保存添加
        if (existingUser != null && existingUser.getLogin() != null) {
            int teamId = RequestUtil.getInt(request, "teamId");
            request.setAttribute("teamId", teamId);
            return "/testPersonnel/create";
        } else {
            testPersonnelSsmService.addTestPersonnel(s);
            String path = "redirect:/testPersonnel/select.action?teamId=" + s.getTeamId();
            return path;
        }

    }


    //用于跳转至修改参测人员页面
    @RequestMapping("edit.action")
    public String edit(HttpServletRequest req, HttpServletResponse resp) {

        //从前端获取参测人员id
        int id = RequestUtil.getInt(req, "id");
        TestPersonnel testPersonnel = testPersonnelSsmService.findById(id);


        // 格式化日期为 yyyy-MM-dd
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = null;

        if (testPersonnel.getBirthDate() != null) {
            formattedDate = dateFormat.format(testPersonnel.getBirthDate());
        }
        req.setAttribute("formattedBirthDate", formattedDate);
        req.setAttribute("testPersonnel", testPersonnel);

        return "/testPersonnel/edit";
    }

    //修改参测人员信息
    @RequestMapping("/update.action")
    public String update(HttpServletRequest req, HttpServletResponse resp) {

        // 创建 User 对象并设置基本信息
        User user = new User();
        user.setId(RequestUtil.getInt(req, "id"));
        user.setName(RequestUtil.getString(req, "name"));
        user.setStatus(Integer.parseInt(RequestUtil.getString(req, "status")));

        // 创建 TestPersonnel 对象并设置基本信息
        TestPersonnel testPersonnel = new TestPersonnel();
        testPersonnel.setId(user.getId());
        testPersonnel.setUser(user);

        // 处理生日日期
        String birthDateString = RequestUtil.getString(req, "birthDate");
        Date birthDate = Date.valueOf(birthDateString);
        testPersonnel.setBirthDate(birthDate);

        // 设置其他字段
        testPersonnel.setPhone(RequestUtil.getString(req, "phone"));
        testPersonnel.setGender(RequestUtil.getString(req, "gender"));

        // 更新 TestPersonnel
        testPersonnelSsmService.updateTestPersonnel(testPersonnel);

        // 重定向到指定的页面
        String path = "redirect:/testPersonnel/view.action?id=" + user.getId();
        return path;
    }


    @RequestMapping("/delete.action")
    public String delete(HttpServletRequest req, HttpServletResponse resp) {

        //获取参测人员id
        int id = RequestUtil.getInt(req, "id");

        TestPersonnel testPersonnel = testPersonnelSsmService.deleteTestPersonnel(id);

        //返回list.action
        String path = "forward:/testPersonnel/list.action?id=" + id;
        return path;
    }


    @GetMapping("/import")
    public String showImportPage() {
        return "testPersonnel/import"; //跳转到import.jsp页面
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importTestPersonnel(@RequestParam("tid") int tid,
                                      @RequestParam("file") MultipartFile file,
                                      HttpServletRequest request,
                                      Model model) {

        List<TestPersonnel> testPersonnelList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 4) {
                    model.addAttribute("error", "导入文件格式错误:" + line);
                    return "testPersonnel/import";
                }

                TestPersonnel testPersonnel = new TestPersonnel();
                User user=new User();
                testPersonnel.setTeamId(tid);
                testPersonnel.setUser(user);
                testPersonnel.setPhone(data[0]);
                user.setName(data[1]);
                testPersonnel.setGender(data[2]);
                testPersonnel.setBirthDate(Date.valueOf(data[3]));
                testPersonnelList.add(testPersonnel);
            }
            testPersonnelSsmService.importTestPersonnel(testPersonnelList);
            model.addAttribute("message", "成功导入" + testPersonnelList.size() + "个参测人员");
            String path="redirect:/testPersonnel/list?tid=" + tid;
            return path;

        } catch (IOException e) {
            model.addAttribute("error", "文件读取错误:" + e.getMessage());
            return "testPersonnel/import";
        } catch (ExamException e) {
            model.addAttribute("error", "导入失败:"+e.getMessage());
            return "team/list"; // 视图名称
        }
    }
}
