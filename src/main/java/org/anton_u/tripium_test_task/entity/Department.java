package org.anton_u.tripium_test_task.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments",
       indexes = {@Index(name = "idx_name", columnList = "department")})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @OneToOne()
    @JoinColumn(name = "head", referencedColumnName = "id")
    private Lecture head;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "department_lecture",
               joinColumns = @JoinColumn(name = "department_id"),
               inverseJoinColumns = @JoinColumn(name = "lecture_id"))
    private Set<Lecture> lectures = new HashSet<>();

    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
        lecture.getDepartments().add(this);
    }

    public void removeLecture(Lecture lecture) {
        lectures.remove(lecture);
        lecture.getDepartments().remove(this);
    }

    public Lecture getHead() {
        return head;
    }

    public void setHead(Lecture head) {
        this.head = head;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
