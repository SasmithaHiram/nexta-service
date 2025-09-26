package com.sasmitha.lms.repository;

import com.sasmitha.lms.entity.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<ClassroomEntity, Long> {
}
