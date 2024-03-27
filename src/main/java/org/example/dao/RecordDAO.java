package org.example.dao;

import org.example.models.Record;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface RecordDAO {
    public void addRecord(Record record) throws SQLException;
    public void updateRecord(Long record_id, Record record) throws SQLException;
    public Record getRecordById(Long record_id) throws  SQLException;
    public List<Record> getAllRecords() throws SQLException;
    public void deleteRecord(Record record) throws SQLException;
}
