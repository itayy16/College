/*
Assignment number : 8.3
File Name         : Course.java
Name (First Last) : Itay Hanya
Student ID        : 311446876
Email             : Itay.ohad@post.idc.ac.il
*/
/**
 * Represents a course.
 */
public class Course {

    private int cid;
    private String title;

    /**
     * Constructs a new course.
     * 
     * @param cid   the course id
     * @param title the course title
     */
    public Course(int cid, String title) {
        this.cid = cid;
        this.title = title;
    }

    /**
     * returns the course id
     * 
     * @return the course id
     */
    public int getCid() {
        return cid;
    }

    /**
     * returns the course title
     * 
     * @return the course title
     */
    public String getTitle() {
        return title;
    }

    /**
     * A textual representation of this course.
     */
    public String toString() {
        return "Course " + cid + ": " + title;
    }
}
