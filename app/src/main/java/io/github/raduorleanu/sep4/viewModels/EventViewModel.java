package io.github.raduorleanu.sep4.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.repositories.EventRepository;

public class EventViewModel extends AndroidViewModel {

    private EventRepository repository;
    private MutableLiveData<List<Event>> events;

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = new EventRepository();
        events = new MutableLiveData<>();
    }

    public void setAdapter(EventListAdapter adapter) {
        repository.setAdapter(adapter);
    }

    public MutableLiveData<List<Event>> getEvents() {
        return events;
    }

}
