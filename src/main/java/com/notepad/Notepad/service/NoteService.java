package com.notepad.Notepad.service;

import com.notepad.Notepad.model.Note;
import com.notepad.Notepad.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note saveOrUpdateNote(Note note){
        if(note.getTitle() == null || note.getTitle() == ""){
            note.setTitle("Regular Note");
        }
        return noteRepository.save(note);
    }

    public Iterable<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Note findById(Long id){
        return noteRepository.findNoteById(id); //  Remember this came from the NoteRespository interface
    }

    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }
}
