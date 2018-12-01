package io.github.raduorleanu.sep4;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.adapters.UserListAdapter;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.AddUsersToEventUserSwapRepository;
import io.github.raduorleanu.sep4.viewModels.UserViewModel;

public class AddFriendsToEventActivity  extends AppCompatActivity {

    private TextView description;
    private TextView alreadyGoing;
    private TextView allFriends;
    private RecyclerView going;
    private RecyclerView notGoing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people_to_event);
        description = findViewById(R.id.add_people_to_event_description);
        alreadyGoing = findViewById(R.id.add_people_to_event_already_going);
        allFriends = findViewById(R.id.add_people_to_event_all_friends);
        going = findViewById(R.id.recyclerview_added_friends);
        notGoing = findViewById(R.id.recyclerview_all_friends);

        String message = "something went wrong";

        Intent intent = getIntent();
        if(intent != null) {
            Bundle b = intent.getExtras();
            if(b != null) {
                message = "Add people for: " + b.getString("eventDescription");
            }
        }

        description.setText(message);
        populateUserViews();
    }

    public void populateUserViews() {
        final UserListAdapter goingAdapter = new UserListAdapter(this);
        final UserListAdapter notGoingAdapter = new UserListAdapter(this);

        // adapter type for the repository to know who called
        goingAdapter.setAdapterType(0);
        notGoingAdapter.setAdapterType(1);

        going.setAdapter(goingAdapter);
        notGoing.setAdapter(notGoingAdapter);

        going.setLayoutManager(new LinearLayoutManager(this));
        notGoing.setLayoutManager(new LinearLayoutManager(this));

        UserViewModel goingModel = ViewModelProviders.of(this).get(UserViewModel.class);
        UserViewModel notGoingModel = ViewModelProviders.of(this).get(UserViewModel.class);

        goingModel.setGoingAdapter(goingAdapter);
        goingModel.setNotGoingAdapter(notGoingAdapter);

        notGoingModel.setGoingAdapter(goingAdapter);
        notGoingModel.setNotGoingAdapter(notGoingAdapter);

        goingModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                assert users != null;
                goingAdapter.setUsers(new ArrayList<>(users));
            }
        });

        notGoingModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                assert users != null;
                goingAdapter.setUsers(new ArrayList<>(users));
            }
        });

        AddUsersToEventUserSwapRepository.getRepository().simulate();

    }
}
