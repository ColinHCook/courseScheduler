
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
    private static PreparedStatement addCourses;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement dropCourse;
    private static ResultSet resultSet;
    
    public static ArrayList<CourseEntry> getAllCourses(String semester){
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<CourseEntry>();
        try
        {
            getAllCourses = connection.prepareStatement("select SEMESTER,COURSECODE,DESCRIPTION,SEATS from app.Course where semester = '"+semester+"'");
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
            addCourses = connection.prepareStatement("insert into app.course (sEMESTER,COURSECODE, DESCRIPTION, SEATS) values (?,?,?,?)");
            addCourses.setString(1, course.getSemester());
            addCourses.setString(2, course.getCourseCode());
            addCourses.setString(3, course.getDescription());
            addCourses.setInt(4, course.getSeats());
            addCourses.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<CourseEntry> getAllCourseCodes(String semester){
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<CourseEntry>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select coursecode from app.Course where semester=(?)");
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
            getAllCourseCodes = connection.prepareStatement("select SEATS from app.Course where semester=(?) and coursecode=(?)");
            getAllCourseCodes.setString(1, semester);
            getAllCourseCodes.setString(2, courseCode);
            resultSet = getAllCourseCodes.executeQuery();
            
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
}
    

    
