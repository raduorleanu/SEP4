package io.github.raduorleanu.sep4.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.DbHandler;
import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.Repository;

public class EventViewModel extends AndroidViewModel {

    private IRepository repository;
    private MutableLiveData<List<Event>> events;

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository<Event>();
        events = new MutableLiveData<>();
    }

    public void setAdapter(EventListAdapter adapter) {
        repository.setAdapter(adapter);
    }

    public MutableLiveData<List<Event>> getEvents() {
        return events;
    }

}
