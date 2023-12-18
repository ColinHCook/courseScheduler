
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author colin
 */
public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudents;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static ResultSet resultSet;
    
 //adding different student entries   
    public static void addScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (SEMESTER,STUDENTID,COURSECODE,STATUS,TIMESTAMP) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getStudentID());
            addScheduleEntry.setString(3, entry.getCourseCode());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudents = connection.prepareStatement("select * from app.Schedule where semester=(?) and studentID=(?)");
            getScheduleByStudents.setString(1, semester);
            getScheduleByStudents.setString(2, studentID);
            resultSet = getScheduleByStudents.executeQuery();
            
            while(resultSet.next())
            {
                schedule.add(new ScheduleEntry(resultSet.getString(1),resultSet.getString(3),resultSet.getString(2),resultSet.getString(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }        
        return schedule;
                    
    }
   
    public static int getScheduledStudentCount(String currentSemester, String courseCode){
        connection = DBConnection.getConnection();
        int studentCount = 0;
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select * from app.schedule where semester=(?) and coursecode=(?)");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            
            while(resultSet.next())
            {
                studentCount++;
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }        
        return studentCount;
                    
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select * from app.schedule where semester=(?) and coursecode=(?) and status=(?)");
            getScheduledStudentCount.setString(1, semester);
            getScheduledStudentCount.setString(2, courseCode);
            getScheduledStudentCount.setString(3, "S");
            resultSet = getScheduledStudentCount.executeQuery();
            
            while(resultSet.next())
            {
                schedule.add(new ScheduleEntry(resultSet.getString(1),resultSet.getString(3),resultSet.getString(2),resultSet.getString(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }    
        
        return schedule;
               
    }
    
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select * from app.schedule where semester=(?) and coursecode=(?) and status=(?)");
            getScheduledStudentCount.setString(1, semester);
            getScheduledStudentCount.setString(2, courseCode);
            getScheduledStudentCount.setString(3, "W");
            resultSet = getScheduledStudentCount.executeQuery();
            
            while(resultSet.next())
            {
                schedule.add(new ScheduleEntry(resultSet.getString(1),resultSet.getString(3),resultSet.getString(2),resultSet.getString(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }        
        return schedule;
                    
    }
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and studentID = ? and courseCode = ?");
            dropStudentScheduleByCourse.setString(1, semester);
            dropStudentScheduleByCourse.setString(2, studentID);
            dropStudentScheduleByCourse.setString(3, courseCode);
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public void dropScheduleByCourse(String semester, String studentID, String courseCode){
        
        connection = DBConnection.getConnection();
        try
        {
            dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and studentID = ? and courseCode = ?");
            dropScheduleByCourse.setString(1, semester);
            dropScheduleByCourse.setString(2,studentID);
            dropScheduleByCourse.setString(3,courseCode);
            dropScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
        
    
    
    public void updateScheduleEntry(String semester, ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("update app.schedule set status = (?) where semester = (?) and studentID = (?) and courseCode = (?)");
            addScheduleEntry.setString(1, "S");
            addScheduleEntry.setString(2, entry.getSemester());
            addScheduleEntry.setString(3, entry.getStudentID());
            addScheduleEntry.setString(4, entry.getCourseCode());
            
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
}
