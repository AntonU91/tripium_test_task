package org.anton_u.tripium_test_task.service;

import org.anton_u.tripium_test_task.entity.Lecture;
import org.springframework.stereotype.Service;

@Service
public interface ConsoleHandlerService {
     Lecture findHeadByName(String departmentName);

}
