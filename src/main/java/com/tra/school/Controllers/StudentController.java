package com.tra.school.Controllers;

import com.tra.school.Models.Student;
import com.tra.school.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("getByName")
    public Student getStudentByName(@RequestParam String studentName) {
        return studentService.getStudentByName(studentName);
    }

    @GetMapping("getByNumber")
    public void getByNumber(@RequestParam int num) throws Exception {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[num]);  // This will cause an ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Array index out of bound" + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception: " + e.getMessage());
        } finally {
            System.out.println("This block always executes");
        }
    }

    @GetMapping("getByAge")
    public void getBy(@RequestParam Integer age) throws Exception {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or older");
        }
        System.out.println("Age is valid");
    }
}

