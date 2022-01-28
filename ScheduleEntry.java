/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Timestamp;
/**
 *
 * @author USER
 */
public class ScheduleEntry {
    private String semester;
    private String courseCode;
    private String studentID;
    private String status;
    private Timestamp timestamp;

    public ScheduleEntry(String Semester, String CourseCode, String StudentID, String Status, Timestamp Timestamp) {
        this.semester = Semester;
        this.courseCode = CourseCode;
        this.studentID = StudentID;
        this.status = Status;
        this.timestamp = Timestamp;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    
}
