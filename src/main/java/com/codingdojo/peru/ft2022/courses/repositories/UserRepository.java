package com.codingdojo.peru.ft2022.courses.repositories;

import com.codingdojo.peru.ft2022.courses.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.peru.ft2022.courses.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAll();

    // Recupera una lista de todas los usuarios para un curso en particular.
    List<User> findAllByCourses(Course course);

    // Recupera una lista de cualquier usuario al que un curso en particular no pertenece.
    List<User> findByCoursesNotContains(Course course);
}
