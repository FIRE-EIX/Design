package JavaBean;

public class ScoreInfo {
    String id;
    String studentName;
    String courseNumber;
    String teacherId;
    String teacherName;
    int score;
    String confirm;
    String term;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public ScoreInfo(String id, String studentName, String courseNumber, String teacherId, String teacherName, int score, String confirm, String term) {
        this.id = id;
        this.studentName = studentName;
        this.courseNumber = courseNumber;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.score = score;
        this.confirm = confirm;
        this.term = term;
    }

    @Override
    public String toString() {
        return "ScoreInfo{" +
                "id='" + id + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", score=" + score +
                ", term='" + term + '\'' +
                '}';
    }

    public ScoreInfo() {
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public ScoreInfo(String id, String courseNumber, int score, String term) {
        this.id = id;
        this.courseNumber = courseNumber;
        this.score = score;
        this.term = term;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
