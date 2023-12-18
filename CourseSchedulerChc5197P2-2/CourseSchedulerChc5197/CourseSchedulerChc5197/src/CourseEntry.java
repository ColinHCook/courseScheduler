/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author colin
 */
public class CourseEntry {
    private String currentSemester;
    private String courseCode;
    private String description;
    private int seats;
   

    public CourseEntry(String currentSemester,String courseCode, String description, int seats) {
        this.currentSemester = currentSemester;
        this.courseCode = courseCode;
        this.description = description;
        this.seats = seats;
        
        
    }

    public String getSemester() {
        return currentSemester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDescription() {
        return description;
    }

    public int getSeats() {
        return seats;
    }
    
    public String toString(){
        return(courseCode);
    }

  
 
    
    
    
}
