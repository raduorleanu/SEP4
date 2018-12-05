package io.github.raduorleanu.sep4.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.EventDbHandler;
import io.github.raduorleanu.sep4.interfaces.IListAdapter;
import io.github.raduorleanu.sep4.models.Event;

public class EventRepository {

    private MutableLiveData<List<Event>> data;
    private EventListAdapter adapter;

    public EventRepository() {
        data = new MutableLiveData<>();
        new EventDbHandler(this, "events");
    }

    public void setAdapter(EventListAdapter adapter) {
        this.adapter = adapter;
    }

    public void updateData(List<Event> dataList) {
//        Log.w("repo-update", dataList.toString());
        adapter.setData(dataList);
    }

    public void removeData(Event event) {
        adapter.removeData(event);
    }

    public void insertData(Event entry) {
        adapter.addData(entry);
    }

    public MutableLiveData<List<Event>> getData() {
        return data;
    }
}
