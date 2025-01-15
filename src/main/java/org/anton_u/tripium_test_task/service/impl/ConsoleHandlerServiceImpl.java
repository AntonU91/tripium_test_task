package org.anton_u.tripium_test_task.service.impl;

import org.anton_u.tripium_test_task.entity.Lecture;
import org.anton_u.tripium_test_task.enums.Degree;
import org.anton_u.tripium_test_task.repo.DepartmentRepository;
import org.anton_u.tripium_test_task.repo.LectureRepository;
import org.anton_u.tripium_test_task.service.ConsoleHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConsoleHandlerServiceImpl implements ConsoleHandlerService {
    private static final String NAME_SURNAME_PATTERN = "%s %s";
    private static final String KEY_VALUE_PATTERN = "%s - %s";
    private final DepartmentRepository departmentRepository;
    private final LectureRepository lectureRepository;

    @Autowired
    public ConsoleHandlerServiceImpl(DepartmentRepository departmentRepository, LectureRepository lectureRepository) {
        this.departmentRepository = departmentRepository;
        this.lectureRepository = lectureRepository;
    }

    @Override
    public String findHeadByName(String departmentName) {
        Lecture head = departmentRepository.findHeadByName(departmentName);
        if (head != null) {
            String nameAndSurname = String.format(NAME_SURNAME_PATTERN, head.getName(), head.getSurname());
            return String.format("Head of %s department is %s", departmentName, nameAndSurname);
        }
        return String.format("There is no data with %s", departmentName);

    }

    @Override
    public String getDepartmentStatistic(String departmentName) {
        List<Lecture> allLecturesFromDepartmentByName = departmentRepository.findAllLecturesFromDepartmentByName(departmentName);

        if (allLecturesFromDepartmentByName.isEmpty()) {
            return String.format("There is no statistics for %s department", departmentName);
        }

        Map<Degree, Long> groupedMap = allLecturesFromDepartmentByName.stream()
                .collect(Collectors.groupingBy((Lecture::getDegree), Collectors.counting()));
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Degree, Long> entry : groupedMap.entrySet()) {
            result.append(String.format(KEY_VALUE_PATTERN + "\n", entry.getKey().getDegree(), entry.getValue()));
        }
        return result.toString();
    }

    @Override
    public String getAverageSalary(String departmentName) {
        Integer averageSalary = departmentRepository.getAverageSalaryByName(departmentName);
        if (averageSalary == null || averageSalary == 0) {
            return "No data found for the specified department";
        }
        return String.format("The average salary of %s is %s", departmentName, averageSalary);
    }

    @Override
    public String getLecturesCount(String departmentName) {
        Integer lecturesCountByName = departmentRepository.getLecturesCountByName(departmentName);
        if (lecturesCountByName == null || lecturesCountByName == 0) {
            return String.format("There is no lectures in %s department", departmentName);
        }
        return lecturesCountByName.toString();
    }

    @Override
    public String getLectureNameByPattern(String pattern) {
        List<Lecture> allLecturesByPattern = this.lectureRepository.findAllLecturesByPattern(pattern);
        if (allLecturesByPattern.isEmpty()) {
            return String.format("There is no lectures founded by '%s' pattern", pattern);
        }
        StringBuilder result = new StringBuilder();
        Lecture lecture;
        for (int i = 0; i < allLecturesByPattern.size(); i++) {
            lecture = allLecturesByPattern.get(i);
            if (i + 1 == allLecturesByPattern.size()) {
                result.append(String.format(NAME_SURNAME_PATTERN + ".", lecture.getName(), lecture.getSurname()));
                break;
            }
            result.append(String.format(NAME_SURNAME_PATTERN + ",", lecture.getName(), lecture.getSurname()));
        }
        return result.toString();
    }
}
