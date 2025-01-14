package org.anton_u.tripium_test_task.repo;

import org.anton_u.tripium_test_task.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query(
            value = "from Lecture l where l.name like :pattern or l.surname like :pattern")
    List<Lecture> findAllLecturesByPattern( @Param("pattern") String pattern);
}