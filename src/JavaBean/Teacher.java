package JavaBean;

public class Teacher {
    String id;
    String teacherName;
    String sex;
    String classNumber;

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", sex='" + sex + '\'' +
                ", classNumber='" + classNumber + '\'' +
                '}';
    }

    public Teacher(String id, String teacherName, String sex, String classNumber) {
        this.id = id;
        this.teacherName = teacherName;
        this.sex = sex;
        this.classNumber = classNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
