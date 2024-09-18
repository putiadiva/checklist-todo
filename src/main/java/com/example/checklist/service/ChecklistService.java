package com.example.checklist.service;

import com.example.checklist.model.Checklist;
import com.example.checklist.model.Todo;

import java.util.*;

public interface ChecklistService {
    
    Checklist createChecklist(Checklist checklist);

    List<Checklist> getAllChecklist();

    Checklist getChecklistById(Long id);

    Checklist addTodo(Checklist checklist, Todo todo);

    void deleteChecklist(Checklist checklist);

    Todo getTodo(Checklist checklist, Long idTodo);

    Todo saveTodo(Checklist checklist, Todo todo);

    void deleteTodo(Checklist checklist, Todo todo);

}
