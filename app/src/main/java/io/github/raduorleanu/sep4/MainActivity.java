package io.github.raduorleanu.sep4;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.util.AddDataToFireBase;
import io.github.raduorleanu.sep4.viewModels.EventViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //   Give a number of events and a number of maximum users that will attend that event.
        // todo: COMMENT THIS, should only run once to generate database. Uncomment populateEventsView() when commenting this
        //AddDataToFireBase a = new AddDataToFireBase(17, 8, getBaseContext());

        // todo: DON'T FORGET TO comment populateEventsView() when adding data to firebase and uncomment after
        populateEventsView();
    }

    private void populateEventsView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final EventListAdapter eventListAdapter = new EventListAdapter(this);
        recyclerView.setAdapter(eventListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EventViewModel eventEventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        eventEventViewModel.setAdapter(eventListAdapter);

        eventEventViewModel.getEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                assert events != null;
                eventListAdapter.setData(new ArrayList<>(events));
            }
        });
    }


}