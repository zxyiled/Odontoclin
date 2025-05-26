package org.odontoclin.persistence;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.odontoclin.logic.LegalGuard;
import org.odontoclin.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;

public class LegalGuardJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public LegalGuardJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontoclinPU");
    }

    public LegalGuardJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LegalGuard legalGuard) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(legalGuard);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LegalGuard legalGuard) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            legalGuard = em.merge(legalGuard);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = legalGuard.getId();
                if (findLegalGuard(id) == null) {
                    throw new NonexistentEntityException("The legalGuard with id " + id + " no longer exists.");
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
            LegalGuard legalGuard;
            try {
                legalGuard = em.getReference(LegalGuard.class, id);
                legalGuard.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The legalGuard with id " + id + " no longer exists.", enfe);
            }
            em.remove(legalGuard);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LegalGuard> findLegalGuardEntities() {
        return findLegalGuardEntities(true, -1, -1);
    }

    public List<LegalGuard> findLegalGuardEntities(int maxResults, int firstResult) {
        return findLegalGuardEntities(false, maxResults, firstResult);
    }

    private List<LegalGuard> findLegalGuardEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<LegalGuard> cq = em.getCriteriaBuilder().createQuery(LegalGuard.class);
            cq.select(cq.from(LegalGuard.class));
            TypedQuery<LegalGuard> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public LegalGuard findLegalGuard(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LegalGuard.class, id);
        } finally {
            em.close();
        }
    }

    public List<LegalGuard> findLegalGuardByDni(String dni) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<LegalGuard> query = em.createQuery(
                "SELECT l FROM LegalGuard l WHERE l.dni = :dni", LegalGuard.class);
            query.setParameter("dni", dni);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int getLegalGuardCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<LegalGuard> rt = cq.from(LegalGuard.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}
