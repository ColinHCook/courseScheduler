/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Timestamp;
/**
 *
 * @author colin
 */
public class ScheduleEntry {
    private String semester;
    private String courseCode;
    private String studentID;
    private String Status;
    private Timestamp timeStamp;

    public ScheduleEntry(String semester, String courseCode, String studentID, String Status, Timestamp timeStamp) {
        this.semester = semester;
        this.courseCode = courseCode;
        this.studentID = studentID;
        this.Status = Status;
        this.timeStamp = timeStamp;
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
        return Status;
    }

    public Timestamp getTimestamp() {
        return timeStamp;
    }
    
    
}
