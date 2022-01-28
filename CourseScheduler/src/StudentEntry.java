/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class StudentEntry {
    private String studentID;
    private String firstName;
    private String lastName;

    public StudentEntry(String StudentID, String FirstName, String LastName) {
        this.studentID = StudentID;
        this.firstName = FirstName;
        this.lastName = LastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    
}
