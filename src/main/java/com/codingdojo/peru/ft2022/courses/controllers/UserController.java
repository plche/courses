package com.codingdojo.peru.ft2022.courses.controllers;

import com.codingdojo.peru.ft2022.courses.services.CourseService;
import com.codingdojo.peru.ft2022.courses.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;
}
