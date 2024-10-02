package ru.lavrent.weblab2.models;

import java.util.ArrayList;
import java.util.List;

public class RecordBean {
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
}
