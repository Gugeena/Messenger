package com.example.demo1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DBService
{
    private static DBService dbService;
    private EntityManager em;

    private DBService()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.em = emf.createEntityManager();
    }

    void login(String message, String password)
    {

    }

    User getUser(String username)
    {
        return em.find(User.class, username);
    }

    void registerUser(String username, String password) throws UserAlreadyFoundException
    {
        if(userExists(username)) throw new UserAlreadyFoundException("User already found");
        em.getTransaction().begin();
        em.persist(new User(username, password));
        em.getTransaction().commit();
    }

    private boolean userExists(String username)
    {
        return em.find(User.class, username) != null;
    }

    List<String> getMessages(String user, String password)
    {
        List<String> result = null;
        try
        {
            List<Mails> mail;
            String sqlQuery = "SELECT m FROM Mails m WHERE m.receiver_user = :user AND m.userToMails.password = :password";
            TypedQuery<Mails> query = em.createQuery(sqlQuery, Mails.class);
            query.setParameter("user", user);
            query.setParameter("password", password);
            mail = query.getResultList();
            result = mail.stream().map(Mails::getMessage).toList();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return result;
    }

    public static DBService getDBService()
    {
        if(dbService == null)
        {
            return dbService = new DBService();
        }
        else return dbService;
    }

    void sendMessage(Mails message)
    {
        if(!userExists(message.getUserToMails().getUser()) || message.getMessage().contains("\n")) throw new UserNotFoundException("User not found");
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
    }
}
