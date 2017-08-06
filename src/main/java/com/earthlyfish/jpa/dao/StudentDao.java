package com.earthlyfish.jpa.dao;


import com.earthlyfish.jpa.pojo.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StudentDao {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    public static void prepareEnv() {
        emf = Persistence.createEntityManagerFactory("SimplePU");
        em = emf.createEntityManager();
    }

    public void save() {
        em.getTransaction().begin();
        em.persist(new Student("小天天", 10, "济南山东"));
        em.getTransaction().commit();
    }


    public static void destroyEnv() {
        em.close();
        emf.close();
    }
}
