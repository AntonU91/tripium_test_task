package org.anton_u.tripium_test_task.enums;

public enum Degree {
    ASSISTANT("assistant"),
    ASSOCIATE_PROFESSOR("associate_professor"),
    PROFESSOR("professor");

    Degree(String degree) {
        this.degree = degree;
    }

    private final String degree;

    public String getDegree() {
        return degree;
    }
}
