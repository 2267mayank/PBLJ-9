import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // --- PART A: Manual Dependency Injection ---
        AppConfig config = new AppConfig();
        Student injectedStudent = config.student();
        injectedStudent.showDetails();

        // --- PART B: Hibernate CRUD Operations ---
        StudentDAO dao = new StudentDAO();

        Student s1 = new Student("Akash", "akash@example.com");
        dao.createStudent(s1);
        System.out.println("Student saved successfully!");

        List<Student> students = dao.readStudents();
        System.out.println("Current Students in DB:");
        for (Student s : students) {
            System.out.println(s.getId() + " - " + s.getName() + " - " + s.getEmail());
        }

        // --- PART C: Manual Transaction Management ---
        TransactionService bankService = new TransactionService();

        // Create accounts for demonstration
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Account acc1 = new Account("Akash", 5000);
        Account acc2 = new Account("Ravi", 2000);
        session.save(acc1);
        session.save(acc2);
        tx.commit();
        session.close();

        // Perform money transfer
        bankService.transferMoney(acc1.getId(), acc2.getId(), 1500);

        HibernateUtil.shutdown();
    }
}
