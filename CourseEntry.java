/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class CourseEntry {
    private String semester;
    private String courseCode;
    private String description;
    private Integer seats;

    public CourseEntry(String Semester, String CourseCode, String Description, Integer Seats) {
        this.semester = Semester;
        this.courseCode = CourseCode;
        this.description = Description;
        this.seats = Seats;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDescription() {
        return description;
    }

    public Integer getSeats() {
        return seats;
    }
    
    
}
