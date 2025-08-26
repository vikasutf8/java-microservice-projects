package Streaming.Application.Video.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course {


    @Id
    private int courseId;
    private String courseName;
    private String courseDescription;
    private String courseDuration;


    @OneToMany(mappedBy = "course")
    private List<Video> videoList =new ArrayList<>();
}
