package com.example.checklist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Todo {

    private Long id;

    private Long idChecklist;

    private boolean isDone;

    private String description;
}
