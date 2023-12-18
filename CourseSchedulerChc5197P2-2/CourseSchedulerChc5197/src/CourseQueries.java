
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
public class CourseQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement getAllCourses;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement dropCourse;
    private static ResultSet resultSet;
    
    public static ArrayList<CourseEntry> getAllCourses(String semester){
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<CourseEntry>();
        try
        {
            getAllCourses = connection.prepareStatement("select SEMESTER,COURSECODE,DESCRIPTION,SEATS from app.Courses where semester =?");
            getAllCourses.setString(1,semester);
        
            resultSet = getAllCourses.executeQuery();
            
            while(resultSet.next())
            {
                courses.add(new CourseEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
        
    }
    
    public static void addCourse(CourseEntry course){
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.courses (SEMESTER,COURSECODE, DESCRIPTION, SEATS) values (?,?,?,?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getDescription());
            addCourse.setInt(4, course.getSeats());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester){
        connection = DBConnection.getConnection();
        ArrayList<String> courses = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select coursecode from app.Courses where semester=(?)");
            getAllCourseCodes.setString(1, semester);
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                
                courses.add((resultSet.getString(1)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }        
        return courses;
                    
    }
       
 
    public static int getCourseSeats(String semester, String courseCode){
        connection = DBConnection.getConnection();
        int seats = 0;
        try
        {
            getCourseSeats = connection.prepareStatement("select SEATS from app.Courses where semester=(?) and coursecode=(?)");
            getCourseSeats.setString(1, semester);
            getCourseSeats.setString(2, courseCode);
            resultSet = getCourseSeats.executeQuery();
            
            while(resultSet.next())
            {
                seats = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }        
        return seats;
        
     
                    
    }   
    
    public static void dropCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try
        {
            dropCourse = connection.prepareStatement("delete from app.courses where semester='"+ semester + "' and courseCode='"+ courseCode +"'");
            dropCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
}
    

    
