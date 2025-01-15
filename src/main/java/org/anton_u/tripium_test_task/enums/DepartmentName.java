package org.anton_u.tripium_test_task.enums;

public enum DepartmentName {
    PHYSICS("Physics"),
    MATH("Math"),
    PSYCHOLOGY("Psychology"),
    PHILOLOGY("Philology");

    DepartmentName(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
