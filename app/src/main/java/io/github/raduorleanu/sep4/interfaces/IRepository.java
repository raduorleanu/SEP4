package io.github.raduorleanu.sep4.interfaces;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;

public interface IRepository<T> {

    void updateData(List<T> dataList);
    void insertData(Object entry);
    void setAdapter(IListAdapter adapter);

}
