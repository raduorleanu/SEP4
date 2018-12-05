package io.github.raduorleanu.sep4.repositories;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.List;

import io.github.raduorleanu.sep4.AddFriendsActivity;
import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.adapters.UserListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.AddUsersDbHandler;
import io.github.raduorleanu.sep4.databaseHandlers.UserDbHandler;
import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.models.User;

public class AddUsersToEventUserSwapRepository implements IRepository {

//    private MutableLiveData<List<User>> going;
//    private MutableLiveData<List<User>> notGoing;

    private UserListAdapter goingAdapter;
    private UserListAdapter notGoingAdapter;

    private static AddUsersToEventUserSwapRepository repository;
    private WeakReference<Button> butt;
    private static WeakReference<Context> context;

    private boolean alone = false;

    private AddUsersToEventUserSwapRepository(Context context) {
        AddUsersToEventUserSwapRepository.context = new WeakReference<>(context);
    }

    public static AddUsersToEventUserSwapRepository getRepository(Context context) {
        if (repository == null) {
            repository = new AddUsersToEventUserSwapRepository(context);
            return repository;
        }
        AddUsersToEventUserSwapRepository.context = new WeakReference<>(context);
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

    private void addToGoing(User user) {
        notGoingAdapter.removeUser(user);
        goingAdapter.addData(user);
    }

    private void removeFromGoing(User user) {
        goingAdapter.removeUser(user);
        notGoingAdapter.addData(user);
    }

    public void setRightAdapter(UserListAdapter goingAdapter) {
        this.goingAdapter = goingAdapter;
    }

    public void setLeftAdapter(UserListAdapter notGoingAdapter) {
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

    public void checkFriendsNumber() {
        if (butt.get() != null) {
            butt.get().setText(R.string.add_friends_to_event_you_have_no_friends);
            alone = true;
        }
    }

    public void fetchDataFromDatabase(Event event, Button butt) {
        new UserDbHandler(repository, event);
        butt.setOnClickListener(new ClickApply(event));
        this.butt = new WeakReference<>(butt);
    }

    class ClickApply implements View.OnClickListener {

        private Event event;

        ClickApply(Event event) {
            this.event = event;
        }

        @Override
        public void onClick(View view) {
            if (!alone) {
                Log.w("asd", event.get_id() + goingAdapter.getUsers().toString());
                AddUsersDbHandler.writeUsers(goingAdapter.getUsers(), event.get_id());
                ((AppCompatActivity) context.get()).finish();
            } else {
                Intent intent = new Intent(context.get(), AddFriendsActivity.class);
                context.get().startActivity(intent);
            }
        }
    }
}
