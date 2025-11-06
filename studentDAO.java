import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class StudentDAO {

    public void createStudent(Student s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(s);
        tx.commit();
        session.close();
    }

    public List<Student> readStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("from Student", Student.class).list();
        session.close();
        return students;
    }

    public void updateStudent(int id, String newEmail) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, id);
        if (s != null) {
            s.setEmail(newEmail);
            session.update(s);
        }
        tx.commit();
        session.close();
    }

    public void deleteStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, id);
        if (s != null) session.delete(s);
        tx.commit();
        session.close();
    }
}
