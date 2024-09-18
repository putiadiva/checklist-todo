package com.example.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

import com.example.checklist.model.Checklist;
import com.example.checklist.model.Todo;
import com.example.checklist.service.ChecklistService;

@Controller
public class ChecklistController {
    
    @Autowired
    private ChecklistService service;

    @GetMapping("/checklist/all")
    public ResponseEntity<List<Checklist>> getAllChecklist() {
        List<Checklist> list = service.getAllChecklist();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/checklist/create")
    public ResponseEntity<Checklist> createChecklist(@RequestBody Checklist checklist) {
        Checklist c = service.createChecklist(checklist);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/checklist/{id}")
    public ResponseEntity<Checklist> detailChecklist(@PathVariable Long id) {
        Checklist c = service.getChecklistById(id);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(c);
        }
    }

    @DeleteMapping("/checklist/{id}/delete")
    public ResponseEntity<Void> deleteChecklist(@PathVariable Long id) {
        Checklist c = service.getChecklistById(id);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            service.deleteChecklist(c);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/checklist/{id}/add")
    public ResponseEntity<Checklist> addTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Checklist c = service.getChecklistById(id);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            service.addTodo(c, todo);
            return ResponseEntity.ok(c);
        }
    }

    @GetMapping("/checklist/{idChecklist}/{idTodo}")
    public ResponseEntity<Todo> detailTodo(@PathVariable Long idChecklist, @PathVariable Long idTodo) {
        Checklist c = service.getChecklistById(idChecklist);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Todo t = service.getTodo(c, idTodo);

            if (t == null) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.ok(t);
            }
        }
    }

    @PostMapping("/checklist/{idChecklist}/{idTodo}/update")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long idChecklist, @PathVariable Long idTodo, @RequestBody Todo todo) {
        Checklist c = service.getChecklistById(idChecklist);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Todo t = service.getTodo(c, idTodo);

            if (t == null) {
                return ResponseEntity.badRequest().build();
            } else {
                todo.setId(t.getId());
                todo.setIdChecklist(idChecklist);
                Todo tt = service.saveTodo(c, todo);
                return ResponseEntity.ok(tt);
            }
        }
    }

    @PostMapping("/checklist/{idChecklist}/{idTodo}/toggle")
    public ResponseEntity<Todo> toggleTodo(@PathVariable Long idChecklist, @PathVariable Long idTodo) {
        Checklist c = service.getChecklistById(idChecklist);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Todo t = service.getTodo(c, idTodo);

            if (t == null) {
                return ResponseEntity.badRequest().build();
            } else {
                t.setDone(!t.isDone());
                Todo tt = service.saveTodo(c, t);
                return ResponseEntity.ok(tt);
            }
        }
    }

    @DeleteMapping("/checklist/{idChecklist}/{idTodo}/delete")
    public ResponseEntity<Todo> deleteTodo(@PathVariable Long idChecklist, @PathVariable Long idTodo) {
        Checklist c = service.getChecklistById(idChecklist);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Todo t = service.getTodo(c, idTodo);

            if (t == null) {
                return ResponseEntity.badRequest().build();
            } else {
                service.deleteTodo(c, t);
                return ResponseEntity.noContent().build();
            }
        }
    }
}
