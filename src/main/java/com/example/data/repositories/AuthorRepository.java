package com.example.data.repositories;


import com.example.data.models.Author;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
//    static EntityManagerFactory factory = Persistence
//            .createEntityManagerFactory("Author");
//    EntityManager entityManager = factory.createEntityManager();
//
//
//    public default Session getSession() {
//        Session session = entityManager.unwrap(Session.class);
//        return session;
//    }
//
//    default List<Author> findAllAuthoustordWithJpql() {
//
//        Session session = entityManager.unwrap(Session.class);
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
//        Root<Author> rootEntry = cq.from(Author.class);
//        CriteriaQuery<Author> all = cq.select(rootEntry);
//
//        TypedQuery<Author> allQuery = session.createQuery(all);
//        return allQuery.getResultList();

//        return session.createQuery("SELECT a FROM Author a", Author.class).getResultList();
//    }
}
