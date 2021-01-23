package JavaBean;

public class ClassInfo {
    String classNumber;
    String className;

    public ClassInfo() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classNumber='" + classNumber + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public ClassInfo(String classNumber, String className) {
        this.classNumber = classNumber;
        this.className = className;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
