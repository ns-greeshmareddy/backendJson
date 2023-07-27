package com.example.student.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students;

    public StudentController() {
        // Load the student data from the JSON file when the controller is initialized
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Student>> typeReference = new TypeReference<List<Student>>() {
        };
        try {
            Resource resource = new PathMatchingResourcePatternResolver()
                    .getResource("classpath:static/students.json");
            InputStream inputStream = resource.getInputStream();
            students = mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public List<Student> getAllStudents() {
        // Return the entire list of students
        return students;
    }

    @GetMapping("/emailId/{emailId}")
    public Student getStudentByEmailId(@PathVariable String emailId) {
        // Find the student with the specified email ID
        Optional<Student> studentOptional = students.stream()
                .filter(student -> student.getEmailId().equalsIgnoreCase(emailId))
                .findFirst();

        // Return the student if found, or null if not found
        return studentOptional.orElse(null);
    }


    @GetMapping("/classId/{classId}")
    public List<Student> getStudentByClassId(@PathVariable String classId) {
        // Find the student with the specified classId
        List<Student> studentsInSection = students.stream()
                .filter(student -> student.getClassId().equalsIgnoreCase(classId))
                .collect(Collectors.toList());
        return studentsInSection;
    }

    @GetMapping("/firstName/{firstName}")
    public List<Student> getStudentByFirstName(@PathVariable String firstName) {
        // Find the student with the specified email ID
        List<Student> studentOptional = students.stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
        return studentOptional;
    }

    @GetMapping("/lastName/{lastName}")
    public List<Student> getStudentByLastName(@PathVariable String lastName) {
        // Find the student with the specified email ID
        List<Student> studentOptional = students.stream()
                .filter(student -> student.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
        return studentOptional;
    }

    @GetMapping("/id/{id}")
    public Student getStudentById(@PathVariable String id) {
        // Find the student with the specified email ID
        Optional<Student> studentOptional = students.stream()
                .filter(student -> student.getId().equalsIgnoreCase(id))
                .findFirst();

        // Return the student if found, or null if not found
        return studentOptional.orElse(null);
    }


//    @GetMapping("/classId/{classId}")
//    public List<Student> getStudentBySection(@PathVariable String classId) {
//        // Find the student with the specified classId
//        List<Student> studentsInSection = students.stream()
//                .filter(student -> student.getClassId().equalsIgnoreCase(classId))
//                .map(student -> {
//                    Student simpleStudent = new Student();
//                    simpleStudent.setFirstName(student.getFirstName());
//                    simpleStudent.setLastName(student.getLastName());
//                    simpleStudent.setTeacherId(student.getTeacherId());
//                    simpleStudent.setClassId(student.getId());
//                    return simpleStudent;
//                })
//                .collect(Collectors.toList());
//
//
//        return studentsInSection;
//    }
}
