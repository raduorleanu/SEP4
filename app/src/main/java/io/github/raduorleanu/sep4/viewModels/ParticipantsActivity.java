package io.github.raduorleanu.sep4.viewModels;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.adapters.ParticipantAdapter;
import io.github.raduorleanu.sep4.models.User;

public class ParticipantsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ParticipantAdapter participantsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participant_recycle_view);

        recyclerView = findViewById(R.id.participantsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        participantsAdapter = new ParticipantAdapter();
        recyclerView.setAdapter(participantsAdapter);

        ViewModel<User> userViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        userViewModel.setAdapter(participantsAdapter);
    }
}
