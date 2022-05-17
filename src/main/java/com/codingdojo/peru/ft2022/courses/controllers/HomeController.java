package com.codingdojo.peru.ft2022.courses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.peru.ft2022.courses.models.LoginUser;
import com.codingdojo.peru.ft2022.courses.models.User;
import com.codingdojo.peru.ft2022.courses.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
     @Autowired
     private UserService userServ;

    @GetMapping("/")
    public String index(Model model) {

        // Enlazar objetos User y LoginUser vacíos al JSP
        // para capturar la entrada del formulario
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("userLoggedIn") == null) return "redirect:/";
        else return "home.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {

        // PARA HACER después: llamar a un método de registro en el servicio
        // ¡para hacer algunas validaciones adicionales y crear un nuevo usuario!
        User userLoggedIn = userServ.register(newUser, result);

        if(result.hasErrors()) {
            // Asegúrate de enviar el LoginUser vacío antes
            // de volver a renderizar la página.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }

        // ¡Sin errores!
        // PARA HACER después: Almacena sus ID de la base de datos en sesión,
        // en otras palabras, inicia sus sesiones.
        session.setAttribute("userLoggedIn", userLoggedIn);

        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {

        // Añadir una vez implementado el servicio:
         User userLoggedIn = userServ.login(newLogin, result);

        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }

        // ¡Sin errores!
        // PARA HACER después: Almacena sus ID de la base de datos en sesión,
        // en otras palabras, inicia sus sesiones.
        session.setAttribute("userLoggedIn", userLoggedIn);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userLoggedIn");
        return "redirect:/";
    }
}
