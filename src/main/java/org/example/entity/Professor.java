package org.example.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.List;


@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="professor_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;

    }
    public void setDepartment(String department) {
        this.department = department;
    }
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Course> courses;


}
