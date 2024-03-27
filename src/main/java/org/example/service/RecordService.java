package org.example.service;

import lombok.NoArgsConstructor;
import org.example.models.Record;
import org.example.dao.RecordDAO;
import org.example.daoImpl.RecordDAOImpl;

import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor
public class RecordService {

    private final RecordDAO recordDao = new RecordDAOImpl();

    public Record getRecordById(Long id) throws SQLException {
        return recordDao.getRecordById(id);
    }

    public void addRecord(Record record) throws SQLException {
        recordDao.addRecord(record);
    }

    public void updateRecord(Long id, Record updatedRecord) throws SQLException {
        recordDao.updateRecord(id, updatedRecord);
    }

    public List<Record> getAllRecords() throws SQLException {
        return recordDao.getAllRecords();
    }

    public void deleteRecord(Record record) throws SQLException {
        recordDao.deleteRecord(record);
    }
}
