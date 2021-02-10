public class Student {
    private String name;

    public Student (String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object student) {
        if (student == null) return false;
        if (student == this) return true;
        if (this.getClass() != student.getClass()) return false;

        Student anthor = (Student) student;
        return this.name.equals(anthor.name);
    }
}
