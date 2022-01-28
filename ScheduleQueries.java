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
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addSchedule;
    private static PreparedStatement getSchedule;
    private static PreparedStatement dropSchedule;
    private static PreparedStatement updateSchedule;
    private static ResultSet resultSet;
    private static int count;
    
    public static void addScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        
        try{
            addSchedule = connection.prepareStatement("insert into app.schedule (SEMESTER, STUDENTID, COURSECODE, STATUS, TIMESTAMP) values (?, ?, ?, ?, ?)");
            addSchedule.setString(1, entry.getSemester());
            addSchedule.setString(2, entry.getStudentID());
            addSchedule.setString(3, entry.getCourseCode());
            addSchedule.setString(4, entry.getStatus());
            addSchedule.setTimestamp(5, entry.getTimestamp());
            addSchedule.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<>();
        
        try{
            getSchedule = connection.prepareStatement("select SEMESTER, COURSECODE, STATUS, TIMESTAMP from app.schedule where STUDENTID = ? and SEMESTER = ? order by TIMESTAMP");
            getSchedule.setString(1, studentID);
            getSchedule.setString(2, semester);
            resultSet = getSchedule.executeQuery();
            
            while(resultSet.next()){
                schedule.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), studentID, resultSet.getString(3), resultSet.getTimestamp(4)));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return schedule;
    }
    
    public static int getScheduledStudentCount(String semester, String courseCode){
        connection = DBConnection.getConnection();
        
        try{
            getSchedule = connection.prepareStatement("select count(STUDENTID) from app.schedule where SEMESTER = ? and COURSECODE = ?");
            getSchedule.setString(1, semester);
            getSchedule.setString(2, courseCode);
            resultSet = getSchedule.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
            
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return count;
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try{
            dropSchedule = connection.prepareStatement("delete from app.schedule where SEMESTER = ? and COURSECODE = ? ");
            dropSchedule.setString(1, semester);
            dropSchedule.setString(2, courseCode);
            dropSchedule.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void dropScheduleByStudent(String studentID){
        connection = DBConnection.getConnection();
        try{
            dropSchedule = connection.prepareStatement("delete from app.schedule where STUDENTID = ?");
            dropSchedule.setString(1, studentID);
            dropSchedule.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        try{
            dropSchedule = connection.prepareStatement("delete from app.schedule where SEMESTER = ? and STUDENTID = ? and COURSECODE = ?");
            dropSchedule.setString(1, semester);
            dropSchedule.setString(2, studentID);
            dropSchedule.setString(3, courseCode);
            dropSchedule.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void updateScheduleEntry(String semester, ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try{
            updateSchedule = connection.prepareStatement("update app.schedule set STATUS = ? where SEMESTER = ? and STUDENTID = ? and COURSECODE = ?");
            updateSchedule.setString(1, entry.getStatus());
            updateSchedule.setString(2, entry.getSemester());
            updateSchedule.setString(3, entry.getStudentID());
            updateSchedule.setString(4, entry.getCourseCode());
            updateSchedule.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<>();
        try{
            getSchedule = connection.prepareStatement("select STUDENTID, TIMESTAMP from app.schedule where SEMESTER = ? and COURSECODE = ? and STATUS = ? order by TIMESTAMP");
            getSchedule.setString(1, semester);
            getSchedule.setString(2, courseCode);
            getSchedule.setString(3, "S");
            resultSet = getSchedule.executeQuery();
            
            while(resultSet.next()){
                schedule.add(new ScheduleEntry(semester, courseCode, resultSet.getString(1), "S", resultSet.getTimestamp(2)));
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return schedule;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlist = new ArrayList<>();
        try{
            getSchedule = connection.prepareStatement("select STUDENTID, TIMESTAMP from app.schedule where SEMESTER = ? and COURSECODE = ? and STATUS = ? order by TIMESTAMP");
            getSchedule.setString(1, semester);
            getSchedule.setString(2, courseCode);
            getSchedule.setString(3, "W");
            resultSet = getSchedule.executeQuery();
            
            while(resultSet.next()){
                waitlist.add(new ScheduleEntry(semester, courseCode, resultSet.getString(1), "W", resultSet.getTimestamp(2)));
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return waitlist;
    }
}
