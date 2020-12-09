package com.gmail.psyh2409.dao;

import com.gmail.psyh2409.entities.Rate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.LocalDate;

public class RateDAO extends AbstractDAO {

    public Rate getForDate(EntityManagerFactory emf, LocalDate date) {
        Rate rate = new Rate();
        if (emf != null) {
            EntityManager em = null;
            try {
                em = emf.createEntityManager();
                String query = "SELECT r FROM Rate r where r.date = :date";
                Query emQuery = em.createQuery(query);
                emQuery.setParameter("date", date);
                rate = (Rate) emQuery.getResultList().get(0);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
        return rate;
    }
}
