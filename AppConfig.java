public class AppConfig {
    public Course course() {
        return new Course("Java Programming");
    }

    public Student student() {
        return new Student(course());
    }
}
