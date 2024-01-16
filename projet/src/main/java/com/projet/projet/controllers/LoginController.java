package com.projet.projet.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.projet.beans.User;
import com.projet.projet.services.UserServiceImpl;

@Controller
public class LoginController {
	
	@Autowired
	private UserServiceImpl userService;

    @Autowired
    private JavaMailSender javaMailSender;
	
	@GetMapping(value={"/login","/"})
    public String  loginView(){
        return "login";
    }
	
	@GetMapping("/register")
    public String  registerView(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
    public String createAccount(@ModelAttribute User user, Model model){
       System.out.println(user.toString());
        userService.save(user);
        model.addAttribute("regSucc","You have been registred successfully");
        return "login";
    }

   @GetMapping("/recover")
    public String showRecoveryPage() {
        return "recovery";
    }

    @PostMapping("/recover")
    public String recoverPassword(@RequestParam("email") String email) {
        User user = userService.getByMail(email);

        if (user != null) {
            String resetToken = UUID.randomUUID().toString();

            String resetUrl = "http://xx.com/reset-password?token=" + resetToken;

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getMail());
            mailMessage.setSubject("Réinitialisation du mot de passe");
            mailMessage.setText("Pour réinitialiser votre mot de passe, cliquez sur le lien suivant : " + resetUrl);

            javaMailSender.send(mailMessage);
            return "redirect:/login?recoveryEmailSent=true";
        } else {
            return "redirect:/recover?error=true";
        }
    }
}
