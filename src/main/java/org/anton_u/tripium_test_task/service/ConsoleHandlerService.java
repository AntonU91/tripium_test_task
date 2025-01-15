package org.anton_u.tripium_test_task.service;

import org.springframework.stereotype.Service;

@Service
public interface ConsoleHandlerService {
     String findHeadByName(String departmentName);
     String getDepartmentStatistic(String departmentName);
     String getAverageSalary(String departmentName);
     String getLecturesCount(String departmentName);

     String getLectureNameByPattern(String pattern);
}
