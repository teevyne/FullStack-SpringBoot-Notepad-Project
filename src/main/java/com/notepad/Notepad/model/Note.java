package com.notepad.Notepad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    @NotBlank(message = "Body cannot be blank")
    private String body;

    private Instant postDate;
}
