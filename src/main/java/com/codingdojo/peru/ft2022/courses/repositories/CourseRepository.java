package com.codingdojo.peru.ft2022.courses.repositories;

import com.codingdojo.peru.ft2022.courses.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.peru.ft2022.courses.models.Course;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();

    // Recupera una lista de todas los cursos para un usuario en particular.
    List<Course> findAllByUsers(User user);

    // Recupera una lista de cualquier curso al que un usuario en particular no pertenece.
    List<Course> findByUsersNotContains(User user);

    @Query(value = "SELECT signup_date FROM courses_users WHERE course_id = ?1 AND user_id = ?2", nativeQuery = true)
    Optional<Date> findSignUpDate(Long courseId, Long userId);

    @Query(value = "SELECT c.name, c.instructor, u.user_name, c_u.signup_date, c_u.course_id, c_u.user_id FROM courses c, users u, courses_users c_u WHERE c.id = c_u.course_id AND u.id = c_u.user_id AND c_u.course_id = ?1", nativeQuery = true)
    List<Object[]> findCourseDetails(Long courseId);

    @Transactional
    // Queries that require a `@Modifying` annotation include INSERT, UPDATE, DELETE, and DDL statements.
    @Modifying
    @Query(value = "DELETE FROM courses_users WHERE course_id = ?1 AND user_id = ?2", nativeQuery = true)
    void deleteByCourseIdAndUserId(Long courseId, Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE courses SET name = ?2, instructor = ?3, capacity = ?4 WHERE id = ?1", nativeQuery = true)
    void updateCourse(Long courseId, String name, String instructor, Integer capacity);
}
