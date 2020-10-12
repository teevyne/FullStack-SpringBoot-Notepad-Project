package com.notepad.Notepad.controller;

import com.notepad.Notepad.model.Note;
import com.notepad.Notepad.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class NoteController {

    @Autowired
    private NoteService noteService;

//    @PostMapping("/newnote")
//    public ResponseEntity<Note> createNote(@RequestBody Note note)throws URISyntaxException {
//        Note result = noteService.saveOrUpdateNote(note);
//        return ResponseEntity.created(new URI("/api/note" + result.getId())).body(result);
//    }

    @PostMapping("/newnote")
    public ResponseEntity<?> createNote (@Valid @RequestBody Note note, BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Note aNote = noteService.saveOrUpdateNote(note);
        return new ResponseEntity<Note>(aNote, HttpStatus.CREATED);
    }

    @GetMapping("/allnotes")
    public Iterable<Note> getNotes(){
        return noteService.getAllNotes();
    }

//    @GetMapping("allnotes/{id}")
//    public Note getNoteDetails(@PathVariable Long id){
//        return noteService.findById(id);
//    }

    @GetMapping("/allnotes/{id}")
    public ResponseEntity<?> getNoteDetails(@PathVariable Long id) {    // The ID here is the same as in signature
        Note aNote = noteService.findById(id);
        return new ResponseEntity<Note>(aNote, HttpStatus.OK);
    }

    @PutMapping("/allnotes/{id}")
    public ResponseEntity<Note> updateNote (@Valid @RequestBody Note note) throws URISyntaxException {
        Note noteUpdate = noteService.saveOrUpdateNote(note);
        return new ResponseEntity<Note>(noteUpdate, HttpStatus.OK);
    }
    // Actually, to perform an update, just do the same thing as Post, but specify the ID and you're good

//    @DeleteMapping("/allnotes/{id}")
//    public void deleteNoteById(@PathVariable Long id){
//        noteService.deleteNote(id);
//    }

    @DeleteMapping("/allnotes/{id}")
    public ResponseEntity<?> deleteNoteFromBoard(@PathVariable Long id){
        noteService.deleteNote(id);
        return new ResponseEntity<String>("Note successfully deleted", HttpStatus.OK);
    }
}
