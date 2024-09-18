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
        for (Checklist c : listChecklist) {
            if (c.getId() == checklist.getId()) {
                addTodoHelper(checklist, todo);

                return c;
            }
        }

        return null;
    }

    private void addTodoHelper(Checklist checklist, Todo todo) {
        // dummy id generation
        if (checklist.getTodos() == null) {
            List<Todo> lst = new ArrayList<>();
            checklist.setTodos(lst);
        }

        Long id = checklist.getTodos().size() + 1L;
        Todo t = new Todo();
        t.setId(id);
        t.setDescription(todo.getDescription());
        t.setDone(false);
        t.setIdChecklist(checklist.getId());
        checklist.getTodos().add(t);
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
