package io.github.raduorleanu.sep4.repositories;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.DbHandler;
import io.github.raduorleanu.sep4.interfaces.IListAdapter;
import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.Event;

public class Repository<T> implements IRepository<T> {

    private MutableLiveData<List<T>> data;
    private IListAdapter adapter;

    public Repository() {
        data = new MutableLiveData<>();
        //data.setValue(new ArrayList<T>());
        new DbHandler<>(Event.class, this, "events");
        //new EventDbHandler(this);
    }

    // todo: change this to interface
    public void setAdapter(EventListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void updateData(List<T> dataList) {
        List<Object> l = new ArrayList<>();
        l.addAll(dataList);
        adapter.setData(l);
    }

    @Override
    public void insertData(Object entry) {
        adapter.addData(entry);
    }

    public MutableLiveData<List<T>> getData() {
        return data;
    }
}
