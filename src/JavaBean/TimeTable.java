package JavaBean;

public class TimeTable {
    String id;
    String courseNumber;
    String term;

    @Override
    public String toString() {
        return "TimeTable{" +
                "id='" + id + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", term='" + term + '\'' +
                '}';
    }

    public TimeTable(String id, String courseNumber, String term) {
        this.id = id;
        this.courseNumber = courseNumber;
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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
