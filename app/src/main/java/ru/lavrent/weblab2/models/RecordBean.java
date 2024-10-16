package ru.lavrent.weblab2.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import java.util.Date;
import java.io.Serializable;

@Stateful
@SessionScoped
public class RecordBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private List<Record> records;
  private Date lastModifiedDate;

  public RecordBean() {
    records = new ArrayList<>();
    this.lastModifiedDate = new Date();
  }

  public void addRecord(Record record) {
    records.add(record);
    this.lastModifiedDate = new Date();
  }

  public List<Record> getRecords() {
    return records;
  }

  public void setRecords(List<Record> records) {
    this.records = records;
    this.lastModifiedDate = new Date();
  }

  public void clear() {
    records.clear();
    this.lastModifiedDate = new Date();
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }
}
