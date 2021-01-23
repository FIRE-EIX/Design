package JavaBean;

public class CourseInfo {
    String courseId;
    String courseName;

    public CourseInfo() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseInfo(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
}
