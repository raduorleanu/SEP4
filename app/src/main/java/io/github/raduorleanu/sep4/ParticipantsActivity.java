package io.github.raduorleanu.sep4;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.adapters.ParticipantAdapter;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.viewModels.ParticipantViewModel;

public class ParticipantsActivity extends AppCompatActivity {

    ParticipantAdapter participantsAdapter;
    private String eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant_recycle_view);

        getIncommingIntent();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.participantsRecyclerView);
        participantsAdapter = new ParticipantAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(participantsAdapter);

        ParticipantViewModel participantViewModel = ViewModelProviders.of(this).get(ParticipantViewModel.class);
        participantViewModel.setEventID(this.eventID);
        participantViewModel.setAdapter(participantsAdapter);


        participantViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                assert users != null;
                participantsAdapter.setData(new ArrayList<>(users));
            }
        });
    }

    private void getIncommingIntent() {
        if (getIntent().hasExtra("clicked")) {
            Event event = (Event) getIntent().getSerializableExtra("clicked");
            eventID = event.get_id();
        }
    }
}
