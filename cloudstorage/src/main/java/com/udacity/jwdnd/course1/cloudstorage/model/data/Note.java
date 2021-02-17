package com.udacity.jwdnd.course1.cloudstorage.model.data;

import com.udacity.jwdnd.course1.cloudstorage.model.view.NoteForm;
import org.thymeleaf.util.StringUtils;

public class Note {
    private int noteId;
    private String noteTitle;
    private String noteDescription;
    private int userId;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Note(int noteId, String noteTitle, String noteDescription, int userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }
    public Note(String noteTitle, String noteDescription, int userId) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    public Note(NoteForm noteForm, int userId){
        if(!StringUtils.isEmpty(noteForm.getNoteId())){
            this.noteId = Integer.parseInt(noteForm.getNoteId());
        }
        this.noteTitle = noteForm.getNoteTitle();
        this.noteDescription = noteForm.getNoteDescription();
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                ", userId=" + userId +
                '}';
    }
}
