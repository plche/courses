package com.codingdojo.peru.ft2022.courses.models;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name="courses_users")
public class CourseUser {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Temporal(TemporalType.TIMESTAMP)
        @Generated(value = GenerationTime.INSERT)
        @Column(name = "signup_date",
                columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private Date signupDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="user_id")
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="course_id")
        private Course course;

        public CourseUser() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Date getSignupDate() {
                return signupDate;
        }

        public void setSignupDate(Date signupDate) {
                this.signupDate = signupDate;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Course getCourse() {
                return course;
        }

        public void setCourse(Course course) {
                this.course = course;
        }
}
