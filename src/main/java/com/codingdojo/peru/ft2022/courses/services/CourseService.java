package com.codingdojo.peru.ft2022.courses.services;

import com.codingdojo.peru.ft2022.courses.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.peru.ft2022.courses.models.Course;
import com.codingdojo.peru.ft2022.courses.models.User;
import com.codingdojo.peru.ft2022.courses.repositories.CourseRepository;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private UserRepository userRepo;

    public List<Course> allCourses() {
        return courseRepo.findAll();
    }

    public List<Course> findCoursesByUser(User user) {
        return courseRepo.findAllByUsers(user);
    }

    public List<Course> findCoursesByUserNotContains(User user) {
        return courseRepo.findByUsersNotContains(user);
    }

    public Course saveNewCourse(Course newCourse, BindingResult result, HttpSession session) {
        if (result.hasErrors()) return null;
        else return courseRepo.save(newCourse);
    }

    public Course findCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepo.findById(id);
        if (optionalCourse.isEmpty()) {
            System.out.println("Error: Not found; Course with ID: " + id + " doesn't exist!");
            return null;
        } else return optionalCourse.get();
    }

    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }

    public Course addCourse(Long id, HttpSession session) {
        Course course = findCourseById(id);
        if (course != null) {
            User user = (User) session.getAttribute("userLoggedIn");
            List<User> users = course.getUsers();
            users.add(user);
            course.setUsers(users);
            return courseRepo.save(course);
        } else return null;
    }

    public void leaveCourse(Long id, HttpSession session) {
        Course course = findCourseById(id);
        if (course != null) {
            User user = (User) session.getAttribute("userLoggedIn");
            courseRepo.deleteByCourseIdAndUserId(course.getId(), user.getId());
        }
    }

    public Date findSignUpDate(Long courseId, Long userId) {
        Optional<Date> optionalDate = courseRepo.findSignUpDate(courseId, userId);
        if (optionalDate.isEmpty()) {
            System.out.println("Error: Not found; User is not registered in that course!");
            return null;
        } else return optionalDate.get();
    }

    public List<Object[]> findCourseDetails(Long courseId) {
        return courseRepo.findCourseDetails(courseId);
    }

    public void deleteByCourseIdAndUserId(Long courseId, Long userId) {
        courseRepo.deleteByCourseIdAndUserId(courseId, userId);
    }

    public void updateCourse(Course updateCourse, BindingResult result) {
        if (!result.hasErrors()) courseRepo.updateCourse(updateCourse.getId(), updateCourse.getName(), updateCourse.getInstructor(), updateCourse.getCapacity());
    }
}
