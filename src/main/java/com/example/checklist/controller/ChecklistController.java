package com.example.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import com.example.checklist.dto.ReqAddTodo;
import com.example.checklist.dto.ReqCreateChecklist;
import com.example.checklist.dto.ReqRenameTodo;
import com.example.checklist.model.Checklist;
import com.example.checklist.model.Todo;
import com.example.checklist.service.ChecklistService;

@Controller
@RequestMapping("/api")
public class ChecklistController {
    
    @Autowired
    private ChecklistService service;

    @GetMapping("/checklist")
    public ResponseEntity<List<Checklist>> getAllChecklist() {
        List<Checklist> list = service.getAllChecklist();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/checklist")
    public ResponseEntity<Checklist> createChecklist(@RequestBody ReqCreateChecklist dto) {
        Checklist checklist = new Checklist();
        checklist.setName(dto.getName());

        Checklist c = service.createChecklist(checklist);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/checklist/{id}/item")
    public ResponseEntity<Checklist> detailChecklist(@PathVariable Long id) {
        Checklist c = service.getChecklistById(id);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(c);
        }
    }

    @DeleteMapping("/checklist/{id}")
    public ResponseEntity<Void> deleteChecklist(@PathVariable Long id) {
        Checklist c = service.getChecklistById(id);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            service.deleteChecklist(c);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/checklist/{idChecklist}/item")
    public ResponseEntity<Checklist> addTodo(@PathVariable Long idChecklist, @RequestBody ReqAddTodo dto) {
        Checklist c = service.getChecklistById(idChecklist);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Todo newTodo = new Todo();
            newTodo.setDescription(dto.getItemName());

            service.addTodo(c, newTodo);
            return ResponseEntity.ok(c);
        }
    }

    @GetMapping("/checklist/{idChecklist}/item/{idTodo}")
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

    @PutMapping("/checklist/{idChecklist}/item/rename/{idTodo}")
    public ResponseEntity<Todo> renameTodo(@PathVariable Long idChecklist, @PathVariable Long idTodo, @RequestBody ReqRenameTodo dto) {
        Checklist c = service.getChecklistById(idChecklist);
        if (c == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Todo t = service.getTodo(c, idTodo);

            if (t == null) {
                return ResponseEntity.badRequest().build();
            } else {
                t.setDescription(dto.getItemName());
                Todo tt = service.saveTodo(c, t);
                return ResponseEntity.ok(tt);
            }
        }
    }

    @PutMapping("/checklist/{idChecklist}/item/{idTodo}")
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

    @DeleteMapping("/checklist/{idChecklist}/item/{idTodo}")
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
