package org.odontoclin.persistence;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.odontoclin.logic.Secretary;
import org.odontoclin.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;

public class SecretaryJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public SecretaryJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontoclinPU");
    }

    public SecretaryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Secretary secretary) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(secretary);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Secretary secretary) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            secretary = em.merge(secretary);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = secretary.getId();
                if (findSecretary(id) == null) {
                    throw new NonexistentEntityException("The secretary with id " + id + " no longer exists.");
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
            Secretary secretary;
            try {
                secretary = em.getReference(Secretary.class, id);
                secretary.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The secretary with id " + id + " no longer exists.", enfe);
            }
            em.remove(secretary);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Secretary> findSecretaryEntities() {
        return findSecretaryEntities(true, -1, -1);
    }

    public List<Secretary> findSecretaryEntities(int maxResults, int firstResult) {
        return findSecretaryEntities(false, maxResults, firstResult);
    }

    private List<Secretary> findSecretaryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Secretary> cq = em.getCriteriaBuilder().createQuery(Secretary.class);
            cq.select(cq.from(Secretary.class));
            TypedQuery<Secretary> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Secretary findSecretary(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Secretary.class, id);
        } finally {
            em.close();
        }
    }

    public int getSecretaryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Secretary> rt = cq.from(Secretary.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }
}
