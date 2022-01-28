/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourse;
    private static PreparedStatement dropCourse;
    private static ResultSet resultSet;
    private static Integer seat;
    
    public static void addCourse(CourseEntry course){
        connection = DBConnection.getConnection();
        
        try{
            addCourse = connection.prepareStatement("insert into app.course (SEMESTER, COURSECODE, DESCRIPTION, SEATS) values (?, ?, ?, ?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getDescription());
            addCourse.setInt(4, course.getSeats());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<CourseEntry> getAllCourses(String semester){
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> course = new ArrayList<>();
        
        try{
            getCourse = connection.prepareStatement("select * from app.course where SEMESTER = ? order by COURSECODE");
            getCourse.setString(1, semester);
            resultSet = getCourse.executeQuery();
            
            while(resultSet.next()){
                course.add(new CourseEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return course;
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester){
        connection = DBConnection.getConnection();
        ArrayList<String> courseCode = new ArrayList<String>();
        
        try{
            getCourse = connection.prepareStatement("select COURSECODE from app.course where SEMESTER = ? order by COURSECODE");
            getCourse.setString(1, semester);
            resultSet = getCourse.executeQuery();
            
            while(resultSet.next()){
                courseCode.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return courseCode;
    }
    
    public static int getCourseSeats(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try{
            getCourse = connection.prepareStatement("select SEATS from app.course where SEMESTER = ? and COURSECODE = ?");
            getCourse.setString(1, semester);
            getCourse.setString(2, courseCode);
            resultSet = getCourse.executeQuery();
            if(resultSet.next()){
                seat = resultSet.getInt(1);
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return seat;
    }
    
    public static void dropCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try{
            dropCourse = connection.prepareStatement("delete from app.course where SEMESTER = ? and COURSECODE = ?");
            dropCourse.setString(1, semester);
            dropCourse.setString(2, courseCode);
            dropCourse.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}