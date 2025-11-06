import jakarta.persistence.*;

// Hibernate annotations
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    // PART A: DI constructor
    private Course course;

    public Student() {} // Required for Hibernate

    // Constructor for DI (Part A)
    public Student(Course course) {
        this.course = course;
    }

    // Constructor for Hibernate CRUD (Part B)
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getter and setter methods
    public int getId() { return id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    // Part A method
    public void showDetails() {
        System.out.println("Student object created successfully using Dependency Injection!");
        if (course != null) course.displayCourse();
    }
}
