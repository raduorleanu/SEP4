package io.github.raduorleanu.sep4;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.DbHandler;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.viewModels.EventViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AddDataToFireBase a = new AddDataToFireBase();

        addEvents();
    }

    private void addEvents() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final EventListAdapter eventListAdapter = new EventListAdapter(this);
        recyclerView.setAdapter(eventListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EventViewModel eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        eventViewModel.setAdapter(eventListAdapter);

        eventViewModel.getEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                eventListAdapter.setEvents(events);
            }
        });
    }


}
