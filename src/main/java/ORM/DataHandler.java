package ORM;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataHandler {

    public void addItemToDB(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("codecubatorPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(object);

        transaction.commit();
        em.close();
        emf.close();
    }
}
