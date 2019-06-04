package org.tutorial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tutorial.model.Quote;
import org.tutorial.model.QuoteDAO;
import org.tutorial.model.User;
import org.tutorial.services.UserDetailsServiceImp;
import org.tutorial.validator.UserValidator;

import javax.validation.Valid;
import java.util.Random;

@Controller
@SessionAttributes("aNewAccount")
public class MyTutorialController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserDetailsServiceImp userService;

    @Autowired
    private QuoteDAO quoteDAO;

    @RequestMapping(value = {"/login"}, method = { RequestMethod.GET})
    public String login() {

        System.out.println("Login handler running");
        return "login";
    }

    @RequestMapping(value = {"/loginFail"}, method = { RequestMethod.GET})
    public String login(Model model) {

        model.addAttribute("errorMessage","Oops, there was a problem logging in.");
        return "login";
    }

    @GetMapping({"/registration"})
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        user.setRoles(new String[] {"ROLE_USER"});
        userService.saveUser(user);
        UserDetails myNewUser = userService.loadUserByUsername(user.getUsername());

        Authentication auth = new UsernamePasswordAuthenticationToken(myNewUser, null, myNewUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/home";
    }

    @RequestMapping(value = {"/logout"})
    public String logout(Model model) {

        System.out.println("Logout handler running");

        model.addAttribute("logoutMessage","Thanks for using the app. See you next time!");

        return "logout";
    }

    @RequestMapping(value = {"/home","/",""})
    public String home(Model model){

        int totalQuotes = quoteDAO.getCountOfQuotes();
        int rand = new Random().nextInt(totalQuotes) + 1;
        Quote quote = quoteDAO.findById(rand);
        String randomQuote = quote.getQuote() + " - " + quote.getAuthor();
        model.addAttribute("randomQuote", randomQuote);

        return "home";
    }

}
