package com.gmail.psyh2409.dao;


import com.gmail.psyh2409.entities.Account;
import com.gmail.psyh2409.entities.Client;
import com.gmail.psyh2409.entities.Transaction;
import com.gmail.psyh2409.utils.Action;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class AccountDAO extends AbstractDAO {

    public Transaction depositAccount(EntityManagerFactory emf,
                                      Client client,
                                      Long accNum,
                                      BigDecimal amount,
                                      Long adderNum) {
        Transaction transaction = null;
        if (emf != null) {
            EntityManager em = null;
            try {
                BigDecimal bd = new BigDecimal(String.valueOf(amount == null ? 0 : amount));
                Account acc = client
                        .getAccounts()
                        .stream()
                        .filter(account -> Objects.equals(accNum, account.getNumber()))
                        .findAny()
                        .orElse(null);
                BigDecimal bdAmountOfAcc = acc.getAmount();
                acc.setAmount(bdAmountOfAcc.add(bd));
                em = emf.createEntityManager();
                em.getTransaction().begin();
                transaction = new Transaction();
                transaction.setClient(client);
                transaction.setAccCurrency(acc.getCurrency());
                transaction.setAccountNumber(accNum);
                transaction.setAmount(amount);
                transaction.setRest(acc.getAmount());
                transaction.setTarget(adderNum == null ? 0L : adderNum);
                transaction.setDateTime(LocalDateTime.now());
                transaction.setAction(Action.INCREMENT);
                client.getTransactions().add(transaction);
                em.merge(acc);
                em.merge(transaction);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
        return transaction;
    }

    public Account getByNumber(EntityManagerFactory emf, Long number) {
        Account acc = new Account();
        if (emf != null) {
            EntityManager em = null;
            try {
                em = emf.createEntityManager();
                String query = "SELECT a FROM Account a where a.number = :num";
                Query emQuery = em.createQuery(query);
                emQuery.setParameter("num", number);
                acc = (Account) emQuery.getSingleResult();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
        return acc;
    }

    public void transferMoney(EntityManagerFactory emf, Account acTo,
                              Account acFrom, BigDecimal amountTo, BigDecimal amountFrom) {

        Transaction transaction = null;
        if (emf != null) {
            EntityManager em = null;
            try {
                BigDecimal currentAmount = acFrom.getAmount();
                acFrom.setAmount(currentAmount.subtract(amountFrom));
                currentAmount = acTo.getAmount();
                acTo.setAmount(currentAmount.add(amountTo));

                transaction = new Transaction();
                transaction.setClient(acFrom.getClient());
                transaction.setAccCurrency(acFrom.getCurrency());
                transaction.setAccountNumber(acFrom.getNumber());
                transaction.setAmount(amountFrom);
                transaction.setRest(acFrom.getAmount());
                transaction.setTarget(acTo.getNumber());
                transaction.setAction(Action.DECREMENT);

                em = emf.createEntityManager();
                em.getTransaction().begin();
                em.merge(acTo);
                em.merge(acFrom);
                em.merge(transaction);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
    }
}
