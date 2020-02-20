
/*
Assignment number : 8.1
File Name         : College.java
Name (First Last) : Itay Hanya
Student ID        : 311446876
Email             : Itay.ohad@post.idc.ac.il
*/
import linkedList.*;

/**
 * Represents a college, and college management operations. A college has
 * courses, and students. Students take courses and get grades. (See the Course,
 * Student, and CourseTaken classes for more details).
 */
public class College {

    private static String nl = System.getProperty("line.separator");

    private String name; // the name of this college
    private LinkedList<Course> courses;
    private LinkedList<Student> students;

    /**
     * Constructs a new college, with empty student and course lists.
     */
    public College(String name) {
        this.name = name;
        this.courses = new LinkedList<Course>();
        this.students = new LinkedList<Student>();
    }

    /**
     * Adds the given course to the course list of this college.
     * 
     * @param cid   course id.
     * @param title course title.
     */
    public void addCourse(int cid, String title) {
        if (getCourse(cid) == null) {
            Course newCourse = new Course(cid, title);
            courses.add(newCourse);
        }
    }

    /**
     * Prints a list of all the courses.
     */
    public void coursesList() {
        System.out.println(courses);
    }

    /**
     * If the given course is in this college, removes it and returns true.
     * Otherwise returns false.
     * 
     * @param cid the course to remove.
     * @return True if the course was removed, false if there is no such course.
     */
    public boolean removeCourse(int cid) {
        Course a = this.getCourse(cid);
        if (a == null) {
            return false;
        }
        ListIterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student check = it.next();
            check.removeCourse(a);
        }
        return courses.remove(a);
    }

    // Returns the course that has the given id, or null if there is no such course.
    private Course getCourse(int cid) {
        ListIterator<Course> it = courses.iterator();
        while (it.hasNext()) {
            Course current = it.next();
            if (current.getCid() == cid) {
                return current;
            }
        }
        return null;
    }

    /**
     * Adds the given student to the students list of this college.
     * 
     * @param sid  student id
     * @param name student name
     */
    public void addStudent(int sid, String name) {
        if (getStudent(sid) == null) {
            Student newStudent = new Student(sid, name);
            students.add(newStudent);
        }
    }

    /**
     * Prints a list of all the students.
     */
    public void studentsList() {
        System.out.println(students);
    }

    /**
     * If the given student is in this college, removes it and returns true.
     * Otherwise returns false.
     * 
     * @param sid the student's id.
     * @return True if the student was removed, false if there is no such student.
     */
    public boolean removeStudent(int sid) {
        Student a = this.getStudent(sid);
        if (a != null) {
            students.remove(a);
            return true;
        }
        return false;
    }

    // Returns the student that has the given id, or null if there is no such
    // student.
    private Student getStudent(int sid) {
        ListIterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student current = it.next();
            if (current.getSid() == sid) {
                return current;
            }
        }
        return null;
    }

    /**
     * Adds the given course to the course list of the given student.
     * 
     * @param sid   student id
     * @param cid   course id
     * @param grade student's grade in the course
     */
    public void addCourseTaken(int sid, int cid, int grade) {
        if (getStudent(sid) != null && getCourse(cid) != null) {
            this.getStudent(sid).addCourse(getCourse(cid), grade);
        }
    }

    /**
     * Prints the student report of the given student. See the Student class for
     * more details.
     * 
     * @param sid student id
     */
    public void studentReport(int sid) {
        Student buddy = getStudent(sid);
        if (buddy == null) {
            System.out.println("No such student");
        } else {
            buddy.studentReport();
        }
    }

    /**
     * Prints a list of all the students who took the course with the given cid.
     * 
     * @param cid the course
     */
    public void courseReport(int cid) {
        Course newCourse = getCourse(cid);
        LinkedList<Student> temp = studentsWhoTook(newCourse);
        if (newCourse == null) {
            System.out.println("No such course");
        } else {
            System.out.println("Course report: " + newCourse.getTitle());
            System.out.println(temp);
        }
    }

    /**
     * Prints the number of students in the given course
     * 
     * @param cid course id
     */
    public void printSize(int cid) {
        Course newCourse = getCourse(cid);
        LinkedList<Student> temp = studentsWhoTook(newCourse);
        System.out.println(newCourse + " has " + temp.size() + " students");
    }

    // Returns a list of all the students who took the given course
    private LinkedList<Student> studentsWhoTook(Course c) {
        LinkedList<Student> temp = new LinkedList();
        ListIterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student check = it.next();
            if (check.tookCourse(c)) {
                temp.add(check);
            }
        }
        return temp;
    }

    /**
     * Prints the student with the highest grade in the given course.
     * 
     * @param cid course id
     */
    public void topPerfomerReport(int cid) {
        Course newCourse = getCourse(cid);
        int max = 0;
        LinkedList<Student> temp = studentsWhoTook(newCourse);
        if (newCourse == null) {
            System.out.println("no such course");
            return;
        } else if (temp.size() == 0) {
            System.out.println("no students took this course");
            return;
        }
        ListIterator<Student> it = temp.iterator();
        while (it.hasNext()) {
            Student check = it.next();
            if (max < check.gradeInCourse(newCourse)) {
                max = check.gradeInCourse(newCourse);
            }
        }
        System.out.println("Top perfomer in " + newCourse + ": ");
        ListIterator<Student> iter = temp.iterator();
        while (iter.hasNext()) {
            Student check1 = iter.next();
            if (check1.gradeInCourse(newCourse) == max) {
                System.out.println(check1.getName());
            }
        }
    }

    /**
     * Returns the college name
     * 
     * @return the college name
     */
    public String getName() {
        return name;
    }

    /**
     * A textual representation of this college, along with its courses and
     * students.
     */
    public String toString() {
        String str = name + nl;
        str += "courses:" + nl;
        str += courses.toString() + nl;
        str += "students:" + nl;
        str += students.toString() + nl;
        return str;
    }
}
