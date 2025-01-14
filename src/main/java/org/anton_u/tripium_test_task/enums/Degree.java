package org.anton_u.tripium_test_task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Degree {
    ASSISTANT("assistant"),
    ASSOCIATE_PROFESSOR("associate_professor"),
    PROFESSOR("professor");

    private String degree;
}
