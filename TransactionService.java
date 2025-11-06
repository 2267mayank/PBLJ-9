import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionService {

    public void transferMoney(int fromId, int toId, double amount) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Account from = session.get(Account.class, fromId);
            Account to = session.get(Account.class, toId);

            if (from == null || to == null) {
                throw new RuntimeException("Account not found!");
            }

            if (from.getBalance() < amount) {
                throw new RuntimeException("Insufficient funds!");
            }

            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);

            session.update(from);
            session.update(to);

            tx.commit();
            System.out.println("Transaction successful!");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Transaction failed. Rolled back: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
