package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.view.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@RequestMapping("/note")
@Controller
public class NoteController {

    private static final Logger log = LoggerFactory.getLogger(com.udacity.jwdnd.course1.cloudstorage.controller.NoteController.class);

    @Autowired
    NoteService noteService;

    //"@{/note/delete/(noteId=${note.getNoteId()})}
    @GetMapping("/delete")
    public String deleteNote(@RequestParam(name="noteId") String noteId, Model model){
        int res = noteService.deleteNote(Integer.parseInt(noteId));
        model = getProccessModel(res, model);
        return "result";
    }

    @PostMapping("/add")
    public String addNote(NoteForm noteForm, Model model, HttpSession session){
        int userId = (int)session.getAttribute("userId");
        Note note = new Note(noteForm, userId);
        model = processPostNote(note, model);
        return "result";
    }

    private Model processPostNote(Note note, Model model){
        int res = (note.getNoteId() == 0)?noteService.addNote(note):noteService.editNoteByNoteObject(note);
        model = getProccessModel(res, model);
        return model;
    }

    private Model getProccessModel(int input, Model model){
        if(input == 1){
            model.addAttribute("successResult", true);
        }else{
            model.addAttribute("errorResult", true);
            model.addAttribute("errorTrue", true);
            model.addAttribute("errorMsg", "add error msg for note");
        }
        return model;
    }
}
