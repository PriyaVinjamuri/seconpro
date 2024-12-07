package com.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class Client {

	public String insertData(Client client) {
        SessionFactory sessionFactory = null;
        Session session = null;

        try {
            // Create session factory and session
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = sessionFactory.openSession();
            
            // Begin transaction
            session.getTransaction().begin();
            
            // Persist student object
            session.persist(client);
            
            // Commit the transaction
            session.getTransaction().commit();
            
            return "Data Inserted Successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            // Close session and session factory
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

   
    public List<Client> getAllClients() {
        SessionFactory sessionFactory = null;
        Session session = null;
        List<Client> clients = null;

        try {
            // Create session factory and session
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = sessionFactory.openSession();
            
            // Begin transaction
            session.getTransaction().begin();
            
            // Fetch all students
            Query<Client> query = session.createQuery("FROM Client", Client.class);
            clients = query.list();
            
            // Commit the transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close session and session factory
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }

        return clients;
    }

}
