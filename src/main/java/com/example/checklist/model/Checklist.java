package com.example.checklist.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Checklist {
    
    private Long id;

    private String name;

    private List<Todo> todos; 
}
