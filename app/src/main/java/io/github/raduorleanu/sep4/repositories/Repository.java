package io.github.raduorleanu.sep4.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.DbHandler;
import io.github.raduorleanu.sep4.databaseHandlers.EventDbHandler;
import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.Event;

public class Repository<T> implements IRepository<T> {

    private MutableLiveData<List<T>> data;
    private EventListAdapter adapter;

    public Repository() {
        data = new MutableLiveData<>();
        //data.setValue(new ArrayList<T>());
        //new DbHandler<>(Event.class, this, "events");
        new EventDbHandler(this);
    }

    // todo: change this to interface
    public void setAdapter(EventListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void updateData(List<T> dataList) {
        data.setValue(dataList);
    }

    @Override
    public void insertData(T entry) {
        adapter.addEvent((Event) entry);
    }

    public MutableLiveData<List<T>> getData() {
        return data;
    }
}
