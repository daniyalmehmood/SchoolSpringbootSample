package com.tra.school.Services;

import com.tra.school.DTO.SchoolDTO;
import com.tra.school.Models.School;
import com.tra.school.Models.Student;
import com.tra.school.Repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SchoolService {


    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    StudentService studentService;

    public School saveSchool(School school) {

        school.setCreatedDate(new Date());
        school.setIsActive(Boolean.TRUE);

        return schoolRepository.save(school);
    }


    public String deleteSchool(String schoolName) {
        School schoolFromDb = schoolRepository.getBySchoolName(schoolName);
        schoolFromDb.setIsActive(Boolean.FALSE);
        schoolRepository.save(schoolFromDb);
        return "Success";
    }

    public String deleteSchoolByLocation(String location) {
        List<School> schools = schoolRepository.getSchoolByLocation(location);

        List<School> updatedSchoolList = new ArrayList<>();

        for (School school : schools) {
            school.setIsActive(false);
            updatedSchoolList.add(school);
        }
        schoolRepository.saveAll(updatedSchoolList);

        return "Success";
    }

    public List<SchoolDTO> getSchools() {
        List<School> schools = schoolRepository.findAll();

        return SchoolDTO.convertToDTO(schools);
    }


    public void parallelComputing() {

        /*
        Fixed Thread Pool (newFixedThreadPool(int nThreads))
        A fixed-size thread pool with a specified number of threads.
        If all threads are busy, new tasks are placed in a queue until a thread becomes available.
        */
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        /*
        Cached Thread Pool (newCachedThreadPool())
        A pool that creates new threads as needed but reuses previously constructed threads when they are available.
        Suitable for applications that execute many short-lived asynchronous tasks
        */
        //ExecutorService executor = Executors.newCachedThreadPool();

        /*
        Single Thread Executor (newSingleThreadExecutor())
        A thread pool with only one thread. Tasks are executed sequentially.
        Ensures that tasks are executed in the order they are submitted.
        */
        //ExecutorService executor = Executors.newSingleThreadExecutor();

        /*
        Scheduled Thread Pool (newScheduledThreadPool(int corePoolSize))
        A thread pool that can schedule tasks to run after a given delay or to execute periodically.
        Useful for tasks that need to run at regular intervals.
        */
        //ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);


        // Create and submit 5 tasks to the executor service
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executorService.submit(() -> {
                System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate some work with sleep
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " is completed");
            });
        }

        // Shut down the executor service
        executorService.shutdown();
    }


}
