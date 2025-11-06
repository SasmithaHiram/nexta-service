package com.sasmitha.lms.repository;

import com.sasmitha.lms.model.Module;
import com.sasmitha.lms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Query("SELECT m FROM Module m JOIN m.users u WHERE u.id = userId AND u.role.name = 'STUDENT'")
    List<User> findModuleByStudentId(@Param("userId") Long id);
}
