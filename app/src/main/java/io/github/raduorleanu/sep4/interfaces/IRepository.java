package io.github.raduorleanu.sep4.interfaces;

import java.util.Collection;
import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;

public interface IRepository<T> {

    void updateData(List<T> dataList);
    void insertData(T entry);
    public void setAdapter(EventListAdapter adapter);

}
