package com.example.teacher.Controller;

import com.example.teacher.Controller.Teacher;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
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
@RequestMapping("/teachers")
public class TeacherController {

    private List<Teacher> Teachers;

    public TeacherController() {
        // Load the Teacher data from the JSON file when the controller is initialized
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Teacher>> typeReference = new TypeReference<List<Teacher>>() {};
        try {
            Resource resource = new PathMatchingResourcePatternResolver()
                    .getResource("classpath:static/teachers.json");
            InputStream inputStream = resource.getInputStream();
            Teachers = mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping
    public List<Teacher> getAllTeachers() {
        // Return the entire list of Teachers
        return Teachers;
    }


    @GetMapping("/emailId/{emailId}")
    public List<Teacher> getTeacherByEmailId(@PathVariable String emailId) {
        // Find the teacher with the specified email ID
        List<Teacher> teacherOptional = Teachers.stream()
                .filter(teacher -> teacher.getEmailId().equalsIgnoreCase(emailId))
                .collect(Collectors.toList());

        // Return the teacher if found, or null if not found
        return teacherOptional;
    }

    @GetMapping("/classId/{classId}")
    public List<Teacher> getTeacherByClassId(@PathVariable String classId) {
        // Find the teacher with the specified classId
        List<Teacher> teachersInSection = Teachers.stream()
                .filter(teacher -> teacher.getClassId().equalsIgnoreCase(classId))
                .collect(Collectors.toList());
        return teachersInSection;
    }

    @GetMapping("/id/{id}")
    public Teacher getTeacherById(@PathVariable String id) {
        // Find the teacher with the specified email ID
        Optional<Teacher> teacherOptional = Teachers.stream()
                .filter(teacher -> teacher.getId().equalsIgnoreCase(id))
                .findFirst();

        // Return the teacher if found, or null if not found
        return teacherOptional.orElse(null);
    }

    @GetMapping("/firstName/{firstName}")
    public List<Teacher> getTeaherByFirstName(@PathVariable String firstName) {
        // Find the teacher with the specified email ID
        List<Teacher> teacherOptional = Teachers.stream()
                .filter(teacher -> teacher.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
        return teacherOptional;
    }

    @GetMapping("/lastName/{lastName}")
    public List<Teacher> getTeacherByLastName(@PathVariable String lastName) {
        // Find the teacher with the specified email ID
        List<Teacher> teacherOptional = Teachers.stream()
                .filter(teacher -> teacher.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
        return teacherOptional;
    }

}
