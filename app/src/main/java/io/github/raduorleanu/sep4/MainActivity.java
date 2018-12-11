package io.github.raduorleanu.sep4;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.util.AddDataToFireBase;
import io.github.raduorleanu.sep4.viewModels.EventViewModel;

public class MainActivity extends AppCompatActivity {

    private EventListAdapter eventListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar t = findViewById(R.id.toolbar_);
        setSupportActionBar(t);

        //   Give a number of events and a number of maximum users that will attend that event.
        // todo: COMMENT THIS, should only run once to generate database. Uncomment populateEventsView() when commenting this
        //  AddDataToFireBase a = new AddDataToFireBase(4, 12, getBaseContext());

        // todo: DON'T FORGET TO comment populateEventsView() when adding data to firebase and uncomment after
//        populateEventsView();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(eventListAdapter != null) {
//            eventListAdapter.clearData();
//        }
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_new_event) {
            Intent intent = new Intent(this, AddNewEventActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.add_friends) {
            Intent intent = new Intent(this, AddFriendsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateEventsView() {
//        Log.w("onCreate", "called");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        eventListAdapter = new EventListAdapter(this);
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
