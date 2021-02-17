package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.data.User;
import com.udacity.jwdnd.course1.cloudstorage.model.view.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/signup")
public class SignupController {

    private static final Logger log = LoggerFactory.getLogger(com.udacity.jwdnd.course1.cloudstorage.controller.SignupController.class);

    @Autowired
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }
    //default -- get action
    @GetMapping()
    public String fuckYouForSignUp(){
        return "signup";
    }

    //delete action

    //put action (for update)

    //post action
    @PostMapping() //linked to the post action
    public String fuckYouSignupUser(@ModelAttribute SignupForm signupForm, Model model){ //SignupForm is being pass thought form action from signup.html
        String signupErrorMsg = null;
        log.info("testing on signupForm " + signupForm);
        if(!userService.isUsernameAvailable(signupForm.getUsername())){
            signupErrorMsg = "The username already exists.";
            model.addAttribute("signupErrorMsg", signupErrorMsg);
        }
        if(signupErrorMsg==null){
            //public User(Integer userId, String username, String salt, String password, String firstName, String lastName)
            User temp = new User(signupForm);
            int addRow = userService.createUser(temp);
            //if addrow return 0, means nothing is add to db (error)
            if(addRow == 0) {
                signupErrorMsg = "There was an error during signup process. Please try again!!";
                model.addAttribute("signupErrorMsg", signupErrorMsg);
            }
        }
        if(signupErrorMsg==null){
            //success
            model.addAttribute("signupSuccess", true);
        }
        //return back to signup.html page
        return "signup";
    }



}
