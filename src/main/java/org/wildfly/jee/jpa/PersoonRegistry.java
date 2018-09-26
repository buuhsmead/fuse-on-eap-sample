package org.wildfly.jee.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

@ApplicationScoped
@Named
public class PersoonRegistry {
    @Inject
    private EntityManager em;

    @Inject
    private UserTransaction userTransaction;

    public List<Persoon> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Persoon> query = criteriaBuilder.createQuery(Persoon.class);
        query.select(query.from(Persoon.class));
        return em.createQuery(query).getResultList();
    }

    public Persoon findById(Long id) {
        return em.find(Persoon.class, id);
    }

    public Persoon save(Persoon persoon) {
        try {
            try {
                userTransaction.begin();
                return em.merge(persoon);
            } finally {
                userTransaction.commit();
            }
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }

    public void delete(Persoon persoon) {
        try {
            try {
                userTransaction.begin();
                if (!em.contains(persoon)) {
                    persoon = em.merge(persoon);
                }
                em.remove(persoon);
            } finally {
                userTransaction.commit();
            }
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }


}
