package com.synapse.scada.core.db;

import com.synapse.scada.core.db.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by tomek on 6/26/14.
 */
@Service
@Transactional
public class AccountService {

    @Inject
    private SessionFactory sessionFactory;

    public void createAccount(Account account) {
        getSession().save(account);
    }

    public List<Account> getAccounts() {
        return getSession().createQuery("from account").list();
    }

    public Account getAccount(Long id) {
        return (Account) getSession().get(Account.class, id);
    }

    public void updateAccount(Account account) {
        getSession().update(account);
    }

    public void deleteAccount(Long id) {
        getSession().delete(getAccount(id));
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
