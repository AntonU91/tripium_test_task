package org.anton_u.tripium_test_task.util;

import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.anton_u.tripium_test_task.entity.Department;
import org.anton_u.tripium_test_task.entity.Lecture;
import org.anton_u.tripium_test_task.enums.Degree;
import org.anton_u.tripium_test_task.enums.DepartmentName;
import org.anton_u.tripium_test_task.repo.DepartmentRepository;
import org.anton_u.tripium_test_task.repo.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class FakeDataGenerator {

    private final LectureRepository lectureRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public FakeDataGenerator(LectureRepository lectureRepository, DepartmentRepository departmentRepository) {
        this.lectureRepository = lectureRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void fillInData() throws Exception {
        Faker faker = new Faker(Locale.ENGLISH);
        List<Lecture> lectures = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Lecture lecture = new Lecture();
            lecture.setName(faker.name().firstName());
            lecture.setSurname(faker.name().lastName());
            lecture.setSalary((double) faker.number().numberBetween(800, 1500));
            lecture.setDegree(Degree.values()[faker.random().nextInt(0, 2)]);
            lectures.add(lectureRepository.save(lecture));
        }

        for (int i = 0; i < 4; i++) {
            Department department = new Department();
            department.setName(DepartmentName.values()[faker.random().nextInt(0, 3)].getName());
            department.setHead(lectures.get(i));
            department.setName(DepartmentName.values()[i].getName());

            for (int j = 0; j < lectures.size() / 4; j++) {
                Integer randomId = faker.random().nextInt(1, lectures.size());
                Lecture lecture = lectureRepository.findById(Long.valueOf(randomId)).orElseThrow(Exception::new);
                department.addLecture(lecture);
            }
            departmentRepository.saveAndFlush(department);

        }

    }

}
