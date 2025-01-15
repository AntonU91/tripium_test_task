package org.anton_u.tripium_test_task.enums;

import lombok.Getter;

@Getter
public enum Degree {
    ASSISTANT("assistant"),
    ASSOCIATE_PROFESSOR("associate_professor"),
    PROFESSOR("professor");

    Degree(String degree) {
        this.degree = degree;
    }

    private final String degree;
}
