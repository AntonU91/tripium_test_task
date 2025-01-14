package org.anton_u.tripium_test_task.repo;

import org.anton_u.tripium_test_task.entity.Department;
import org.anton_u.tripium_test_task.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

  Lecture findHeadByName(String departmentName);

  List<Lecture> findAllLecturesByName(String departmentName);


  @Query(nativeQuery = true,
         value = "SELECT avg(l.salary) as avg_salary " +
                 "FROM lectures l " +
                 "JOIN department_lecture dl ON  l.id = dl.id " +
                 "JOIN departments d ON d.id = dl.id " +
                 "WHERE d.name = :departmentName")
  Integer getAverageSalaryByName(@Param("departmentName") String departmentName);

  @Query(nativeQuery = true,
         value = "SELECT count(*) as empl_count " +
                 "FROM lectures l " +
                 "JOIN department_lecture dl ON  l.id = dl.id " +
                 "JOIN departments d ON d.id = dl.id " +
                 "WHERE d.name = :departmentName")
  Integer getLecturesCountByName(String departmentName);

}