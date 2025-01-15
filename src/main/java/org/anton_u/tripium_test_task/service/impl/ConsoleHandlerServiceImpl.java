package org.anton_u.tripium_test_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.anton_u.tripium_test_task.entity.Department;
import org.anton_u.tripium_test_task.entity.Lecture;
import org.anton_u.tripium_test_task.repo.DepartmentRepository;
import org.anton_u.tripium_test_task.service.ConsoleHandlerService;

@RequiredArgsConstructor
public class ConsoleHandlerServiceImpl  implements ConsoleHandlerService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Lecture findHeadByName(String departmentName) {
        return departmentRepository.findHeadByName(departmentName);
    }
}
