package io.github.raduorleanu.sep4.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.UserListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.AddUsersDbHandler;
import io.github.raduorleanu.sep4.databaseHandlers.UserDbHandler;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;

public class AddUsersToEventUserSwapRepository {

//    private MutableLiveData<List<User>> going;
//    private MutableLiveData<List<User>> notGoing;

    private UserListAdapter goingAdapter;
    private UserListAdapter notGoingAdapter;

    private static AddUsersToEventUserSwapRepository repository;

    private AddUsersToEventUserSwapRepository() {
//        going = new MutableLiveData<>();
//        notGoing = new MutableLiveData<>();
    }

    public static AddUsersToEventUserSwapRepository getRepository() {
        if(repository == null) {
            repository = new AddUsersToEventUserSwapRepository();
            return repository;
        }
        return repository;
    }

    // 0 is goingAdapter
    // 1 is notGoingAdapter
    public void swap(int adapterType, User user) {
        switch (adapterType) {
            case 0:
                removeFromGoing(user);
                break;
            case 1:
                addToGoing(user);
                break;
        }
    }

    public void addToGoing(User user) {
        notGoingAdapter.removeUser(user);
        goingAdapter.addData(user);
    }

    public void removeFromGoing(User user) {
        goingAdapter.removeUser(user);
        notGoingAdapter.addData(user);
    }

    public void setGoingAdapter(UserListAdapter goingAdapter) {
        this.goingAdapter = goingAdapter;
    }

    public void setNotGoingAdapter(UserListAdapter notGoingAdapter) {
        this.notGoingAdapter = notGoingAdapter;
    }
//
//    public MutableLiveData<List<User>> getGoing() {
//        return going;
//    }
//
//    public MutableLiveData<List<User>> getNotGoing() {
//        return notGoing;
//    }

    public void updateGoing(List<User> list) {
        goingAdapter.setUsers(list);
    }

    public void updateNotGoing(List<User> list) {
        notGoingAdapter.setUsers(list);
    }

    public void fetchDataFromDatabase(Event event, Button butt) {
        new UserDbHandler(repository, event);
        butt.setOnClickListener(new ClickApply(event));
    }

    class ClickApply implements View.OnClickListener {

        private Event event;

        public ClickApply(Event event) {
            this.event = event;
        }

        @Override
        public void onClick(View view) {
            Log.w("asd", event.get_id() + goingAdapter.getUsers().toString());
            AddUsersDbHandler.writeUsers(goingAdapter.getUsers(), event.get_id());
        }
    }
}
