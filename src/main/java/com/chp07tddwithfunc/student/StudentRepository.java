package com.chp07tddwithfunc.student;

import java.util.*;

public class StudentRepository {

    private final Set<Student> students;

    public StudentRepository(Collection<Student> students){
        this.students = new HashSet<>(students);
    }

    public Optional<Student> findByName(String name){
        return students.stream().filter(s -> s.name.equals(name)).findFirst();

        //alternative
/*        for (Student student: students){
            if (student.name.equals(name)){
                return Optional.of(student);
            }
        }
        return Optional.empty();*/
    }

}
