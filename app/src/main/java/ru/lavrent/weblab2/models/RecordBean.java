package ru.lavrent.weblab2.models;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class RecordBean implements Serializable {
  private List<Record> records;

  public RecordBean() {
    records = new ArrayList<>();
  }

  public void addRecord(Record record) {
    records.add(record);
  }

  public List<Record> getRecords() {
    return records;
  }

  public void setRecords(List<Record> records) {
    this.records = records;
  }

  public void clear() {
    records.clear();
  }
}
