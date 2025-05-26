package org.odontoclin.persistence;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.odontoclin.logic.Shift;
import org.odontoclin.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;

public class ShiftJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public ShiftJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontoclinPU");
    }

    public ShiftJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Shift shift) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(shift);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Shift shift) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            shift = em.merge(shift);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = shift.getId_shift();
                if (findShift(id) == null) {
                    throw new NonexistentEntityException("The shift with id " + id + " no longer exists.");
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
            Shift shift;
            try {
                shift = em.getReference(Shift.class, id);
                shift.getId_shift();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The shift with id " + id + " no longer exists.", enfe);
            }
            em.remove(shift);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Shift> findShiftEntities() {
        return findShiftEntities(true, -1, -1);
    }

    public List<Shift> findShiftEntities(int maxResults, int firstResult) {
        return findShiftEntities(false, maxResults, firstResult);
    }

    private List<Shift> findShiftEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Shift> cq = em.getCriteriaBuilder().createQuery(Shift.class);
            cq.select(cq.from(Shift.class));
            TypedQuery<Shift> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Shift findShift(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Shift.class, id);
        } finally {
            em.close();
        }
    }

    public int getShiftCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Shift> rt = cq.from(Shift.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}
