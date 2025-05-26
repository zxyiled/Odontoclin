package org.odontoclin.persistence;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.odontoclin.logic.Dentist;
import org.odontoclin.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;

public class DentistJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public DentistJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontoclinPU");
    }

    public DentistJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dentist dentist) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dentist);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dentist dentist) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dentist = em.merge(dentist);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = dentist.getId();
                if (findDentist(id) == null) {
                    throw new NonexistentEntityException("The dentist with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dentist dentist;
            try {
                dentist = em.getReference(Dentist.class, id);
                dentist.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dentist with id " + id + " no longer exists.", enfe);
            }
            em.remove(dentist);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dentist> findDentistEntities() {
        return findDentistEntities(true, -1, -1);
    }

    public List<Dentist> findDentistEntities(int maxResults, int firstResult) {
        return findDentistEntities(false, maxResults, firstResult);
    }

    private List<Dentist> findDentistEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Dentist> cq = em.getCriteriaBuilder().createQuery(Dentist.class);
            cq.select(cq.from(Dentist.class));
            TypedQuery<Dentist> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Dentist findDentist(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dentist.class, id);
        } finally {
            em.close();
        }
    }

    public int getDentistCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Dentist> rt = cq.from(Dentist.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}
