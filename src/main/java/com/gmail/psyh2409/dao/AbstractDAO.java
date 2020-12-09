package com.gmail.psyh2409.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class AbstractDAO {

    public <X> void add(EntityManagerFactory emf, X x) {
        if (emf != null) {
            EntityManager em = null;
            try {
                em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(x);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
    }

    public <X> X getById(EntityManagerFactory emf, Class<X> xClass, Long id) {
        X x = null;
        if (emf != null) {
            EntityManager em = null;
            try {
                em = emf.createEntityManager();
                x = em.find(xClass, id);
            } finally {
                em.close();
            }
        }
        return x;
    }

}
