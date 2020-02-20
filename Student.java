
/*
Assignment number : 8.5
File Name         : Student.java
Name (First Last) : Itay Hanya
Student ID        : 311446876
Email             : Itay.ohad@post.idc.ac.il
*/
import linkedList.*;

/**
 * Represents a student.
 */
public class Student {

    private int sid;
    private String name;
    private LinkedList<CourseTaken> courseList;

    /**
     * Constructs a new student with the given sid and name, and an empty course
     * list.
     * 
     * @param sid  the student's sid
     * @param name the student's name
     */
    public Student(int sid, String name) {
        this.sid = sid;
        this.name = name;
        courseList = new LinkedList<CourseTaken>();
    }

    /**
     * Returns the sid of this student.
     * 
     * @return the sid of this student.
     */
    public int getSid() {
        return sid;
    }

    /**
     * Returns the name of this student.
     * 
     * @return the name of this student.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the course if this student took the given course, null otherwise.
     * 
     * @param c the course we want to know whether or not the student took.
     * @return course object if this student took the given course, null otherwise.
     */
    public CourseTaken findCourse(Course c) {
        ListIterator<CourseTaken> it = courseList.iterator();
        while (it.hasNext()) {
            CourseTaken current = it.next();
            if (current.getCourse() == c) {
                return current;
            }
        }
        return null;
    }

    /**
     * Adds the given course and grade to the course list of this student.
     * 
     * @param c     the course to add
     * @param grade the grade in the added course
     */
    public void addCourse(Course c, int grade) {
        CourseTaken cur = findCourse(c);
        if (cur != null) {
            cur.setGrade(grade);
            return;
        }
        CourseTaken c1 = new CourseTaken(c, grade);
        courseList.add(c1);
    }

    /**
     * If the given course is in courseList, removes it and returns true. Otherwise
     * returns false.
     * 
     * @param c the course.
     * @return True if the course was removed, false if there is no such course.
     */
    public boolean removeCourse(Course c) {
        return courseList.remove(findCourse(c));
    }

    /**
     * Returns the grade that this student got in the given course, or -1 if the
     * course was not taken by this student.
     * 
     * @param c - the returned grade will be the grade in this course.
     * @return the grade that this student got in the given course
     */
    public int gradeInCourse(Course c) {
        CourseTaken grd = findCourse(c);
        if (grd != null) {
            return grd.getGrade();
        }
        return -1;
    }

    /**
     * Returns true if this student took the given course, false otherwise.
     * 
     * @param c the course we want to know whether or not the student took.
     * @return true if this student took the given course, false otherwise.
     */
    public boolean tookCourse(Course c) {
        return findCourse(c) != null;
    }

    /**
     * Prints this student, all the courses that s/he took, and the grade point
     * average.
     */
    public void studentReport() {
        System.out.println(name + ":");
        if (courseList.size() == 0) {
            System.out.println("No courses taken yet");
            return;
        }
        ListIterator<CourseTaken> it = courseList.iterator();
        int sum = 0;
        while (it.hasNext()) {
            CourseTaken current = it.next();
            System.out.println(current);
            sum += current.getGrade();
        }
        System.out.println("Grade average: " + sum / courseList.size());
    }

    /**
     * Textual representation of this student.
     */
    public String toString() {
        return "Student " + sid + ": " + name;
    }
}