package com.notepad.Notepad.repository;

import com.notepad.Notepad.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Can I declare a search/fetch term in this repo? Yes I can!
    Note findNoteById(Long id);
}
