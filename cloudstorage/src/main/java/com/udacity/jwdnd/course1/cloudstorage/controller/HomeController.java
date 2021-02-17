package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.view.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestingConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestingConstant;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;
//    private CredentialService credentialService;

    @Autowired
    private EncryptionService encryptionService;

    public HomeController(UserService userService, EncryptionService encryptionService) {
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @GetMapping(value = {"/", "/home"})
    public String homeView(Authentication authentication, Model model, HttpSession session){
        Integer userId = null;
        if(session.getAttribute("userId") == null){
            String username = authentication.getName();
            userId = Integer.valueOf(userService.getUser(username).getUserId());
            session.setAttribute("userId", userId);
        }else{
            userId = Integer.valueOf((int)session.getAttribute("userId"));
        }

        List<Note> notesList = noteService.getAllUserNotes(userId);

//        for(int i = 0; i< 3; i++) {
//            notesList.add(TestingConstant.getNote(i, userId));
//        }

        model.addAttribute("enS", encryptionService);
        model.addAttribute("notesList", notesList);
        //NoteForm noteForm = new NoteForm();
        model.addAttribute("note", new NoteForm());
//        List<Credential> credentials = credentialService.getAllUserCredentials(userId.intValue());

//        model.addAttribute("noteList", notes);
//        NoteForm noteForm = new NoteForm();
//        model.addAttribute("noteForm", noteForm);
        return "home";
    }
}
