package com.codingdojo.peru.ft2022.courses.controllers;

import com.codingdojo.peru.ft2022.courses.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.peru.ft2022.courses.models.Course;
import com.codingdojo.peru.ft2022.courses.services.CourseService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseServ;

    @GetMapping("/courses")
    public String courses(Model model, HttpSession session) {
        User userLoggedIn = (User) session.getAttribute("userLoggedIn");
        if (userLoggedIn == null) return "redirect:/";
        else {
            List<Course> courses = courseServ.allCourses();
            model.addAttribute("courses", courses);
            return "courses.jsp";
        }
    }

    @GetMapping("/courses/new")
    public String showNewCourseForm(Model model, HttpSession session) {
        if (session.getAttribute("userLoggedIn") == null) return "redirect:/";
        else {
            model.addAttribute("newCourse", new Course());
            return "newcourse.jsp";
        }
    }

    @GetMapping("/courses/{id}")
    public String showCourseDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("userLoggedIn") == null) return "redirect:/";
        else {
            List<Object[]> rows = courseServ.findCourseDetails(id);
            if (rows == null) {
                return "redirect:/courses";
            } else {
                model.addAttribute("rows", rows);
                return "showcourse.jsp";
            }
        }
    }

    @GetMapping("/courses/{id}/edit")
    public String showEditCourseForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("userLoggedIn") == null) return "redirect:/";
        else {
            Course course = courseServ.findCourseById(id);
            if (course == null) {
                return "redirect:/courses";
            } else {
                model.addAttribute("course", course);
                return "editcourse.jsp";
            }
        }
    }

    @PutMapping("/courses/{id}/save")
    public String saveEditCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
        courseServ.updateCourse(course, result);
        if (result.hasErrors()) return "editcourse.jsp";
        else return "redirect:/courses";
    }

    @DeleteMapping("/courses/{id}/delete")
    public String destroyCourse(@PathVariable("id") Long id) {
        courseServ.deleteCourse(id);
        return "redirect:/courses";
    }

    @PostMapping("/courses/new")
    public String saveNewCourse(@Valid @ModelAttribute("newCourse") Course newCourse,
                                 BindingResult result, HttpSession session) {
        Course course = courseServ.saveNewCourse(newCourse, result, session);
        if (result.hasErrors()) return "newcourse.jsp";
        else return "redirect:/courses";
    }

    @PostMapping("/courses/{id}/add")
    public String addCourse(@PathVariable("id") Long id, HttpSession session) {
        if (courseServ.addCourse(id, session) == null) return "courses.jsp";
        else return "redirect:/courses";
    }

    @PostMapping("/courses/{id}/leave")
    public String leaveCourse(@PathVariable("id") Long id, HttpSession session) {
        courseServ.leaveCourse(id, session);
        return "redirect:/courses";
    }
}
