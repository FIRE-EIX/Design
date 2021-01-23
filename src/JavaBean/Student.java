package JavaBean;

public class Student {
    String id;
    String studentName;
    String sex;
    int entranceYear;
    String classNumber;

    @Override
    public String toString() {
        return "student{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", sex='" + sex + '\'' +
                ", entranceYear=" + entranceYear +
                ", classNumber='" + classNumber + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(String id, String studentName, String sex, int entranceYear, String classNumber) {
        this.id = id;
        this.studentName = studentName;
        this.sex = sex;
        this.entranceYear = entranceYear;
        this.classNumber = classNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(int entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
