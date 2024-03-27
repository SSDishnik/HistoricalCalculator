package org.example.daoImpl;

import org.example.utils.HibernateUtil;
import org.example.dao.RecordDAO;
import org.example.models.Record;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;


public class RecordDAOImpl implements RecordDAO {

    @Override
    public void addRecord(Record record) throws SQLException {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(record);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Paste Error ", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }

    }

    @Override
    public void updateRecord(Long record_id, Record updatedRecord) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Record recordToUpdate = session.get(Record.class, record_id);
            if (recordToUpdate != null) {
                recordToUpdate.setExpression(updatedRecord.getExpression());
                recordToUpdate.setResult(updatedRecord.getResult());
                session.getTransaction().commit();
            } else {
                System.out.println("Record with ID " + record_id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Update Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Record getRecordById(Long record_id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Record.class, record_id);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fetch Error", JOptionPane.OK_OPTION);
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Record> getAllRecords() throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Record", Record.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fetch Error", JOptionPane.OK_OPTION);
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteRecord(Record record) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.remove(record);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Deletion Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
