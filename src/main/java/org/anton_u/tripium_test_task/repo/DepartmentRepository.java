package org.anton_u.tripium_test_task.repo;

import org.anton_u.tripium_test_task.entity.Department;
import org.anton_u.tripium_test_task.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.lectures WHERE d.id = :id")
    Optional<Department> findById(@Param(value = "id") Long id);

    @Query("SELECT d.head FROM Department d WHERE LOWER(d.name) = LOWER(:departmentName)")
    Lecture findHeadByName(@Param(value = "departmentName") String departmentName);

    @Query("SELECT l FROM Department d JOIN d.lectures l  WHERE LOWER(d.name) = LOWER(:departmentName)")
    List<Lecture> findAllLecturesFromDepartmentByName(String departmentName);


    @Query(nativeQuery = true,
           value = "SELECT avg(l.salary) as avg_salary " +
                   "FROM lectures l " +
                   "JOIN department_lecture dl ON  l.id = dl.lecture_id " +
                   "JOIN departments d ON d.id = dl.department_id " +
                   "WHERE LOWER(d.name) = LOWER(:departmentName)")
    Integer getAverageSalaryByName(@Param("departmentName") String departmentName);

    @Query(nativeQuery = true,
           value = "SELECT count(*) as empl_count " +
                   "FROM lectures l " +
                   "JOIN department_lecture dl ON  l.id = dl.lecture_id " +
                   "JOIN departments d ON d.id = dl.department_id " +
                   "WHERE LOWER(d.name) = LOWER(:departmentName)")
    Integer getLecturesCountByName(@Param("departmentName") String departmentName);

}