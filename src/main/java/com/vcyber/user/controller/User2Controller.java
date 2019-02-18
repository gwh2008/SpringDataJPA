package com.vcyber.user.controller;

import com.vcyber.user.entity.User;
import com.vcyber.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class User2Controller {

    @Resource
    UserService userService;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

//    @RequestMapping("/list")
//    public String list(Model model) {
//        List<User> users=userService.getUserList();
//        model.addAttribute("users", users);
//        return "user/list";
//    }
    /*
  *分页查询
  *
  */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable){
        Page<User> userPage = userService.findAll(pageable);
        PageWrapper<User> page = new PageWrapper<User>(userPage, "/list");
        model.addAttribute("users", page.getContent());
        model.addAttribute("page", page);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user) {
        userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable Long id) {
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/list";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/list";
    }
}
