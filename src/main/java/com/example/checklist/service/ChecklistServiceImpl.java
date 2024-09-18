package com.example.checklist.service;

import com.example.checklist.model.Checklist;
import com.example.checklist.model.Todo;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class ChecklistServiceImpl implements ChecklistService {

    private List<Checklist> listChecklist = new ArrayList<Checklist>();

    private Long cntChecklist = 0L;

    public ChecklistServiceImpl() {
        listChecklist.add(new Checklist(1L, "Math", null));
        listChecklist.add(new Checklist(2L, "Physics", null));
        listChecklist.add(new Checklist(3L, "Biology", null));
        cntChecklist = 4L;
    }

    @Override
    public Checklist createChecklist(Checklist checklist) {
        checklist.setId(cntChecklist);
        listChecklist.add(checklist);
        cntChecklist += 1L;
        
        return checklist;
    }

    @Override
    public List<Checklist> getAllChecklist() {
        return listChecklist;
    }

    @Override
    public Checklist getChecklistById(Long id) {
        for (Checklist c : listChecklist) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Checklist addTodo(Checklist checklist, Todo todo) {
        if (checklist.getTodos() == null) {
            List<Todo> lst = new ArrayList<>();
            checklist.setTodos(lst);
        }

        Long id = checklist.getTodos().size() + 1L; //dummy id generation
        todo.setId(id);
        todo.setDescription(todo.getDescription());
        todo.setDone(false);
        todo.setIdChecklist(checklist.getId());

        checklist.getTodos().add(todo);

        return checklist;
    }

    @Override
    public void deleteChecklist(Checklist checklist) {
        listChecklist.remove(checklist);
    }

    @Override
    public Todo getTodo(Checklist checklist, Long idTodo) {
        if (checklist.getTodos() == null) {
            return null;
        }

        for (Todo t : checklist.getTodos()) {
            if (t.getId() == idTodo) {
                return t;
            }
        }

        return null;
    }

    @Override
    public Todo saveTodo(Checklist checklist, Todo todo) {
        for (Todo t : checklist.getTodos()) {
            if (t.getId() == todo.getId()) {
                t.setDescription(todo.getDescription());
                t.setDone(todo.isDone());

                return t;
            }
        }

        return null;
    }

    @Override
    public void deleteTodo(Checklist checklist, Todo todo) {
        checklist.getTodos().remove(todo);
    }
}
