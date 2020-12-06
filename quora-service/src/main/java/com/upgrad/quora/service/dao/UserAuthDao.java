package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserAuthEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserAuthDao {

    @PersistenceContext
    private EntityManager entityManager;
    /**
     * Persists user authen entity in database.
     *
     * @param userAuthEntity to be persisted in the DB.
     * @return UserAuthEntity
     */
    public UserAuthEntity createAuthToken(final UserAuthEntity userAuthEntity) {
        entityManager.persist(userAuthEntity);
        return userAuthEntity;
    }
}
