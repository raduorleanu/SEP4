package io.github.raduorleanu.sep4.repositories;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.List;

import io.github.raduorleanu.sep4.adapters.UserListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.AddFriendsDbHandler;
import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.User;

public class AddFriendsRepository implements IRepository {

    private UserListAdapter searchResults;
    private UserListAdapter addedFriends;


    private static  AddFriendsRepository repository;
    private AddFriendsDbHandler dbHandler;

    //private WeakReference<Button> applyButton;

    private AddFriendsRepository(){
        dbHandler = new AddFriendsDbHandler(this);
    }

    public static AddFriendsRepository getRepository() {
        if(repository == null) {
            repository = new AddFriendsRepository();
            return repository;
        }
        return repository;
    }

    // 0 is goingAdapter
    // 1 is notGoingAdapter
    public void swap(int adapterType, User user) {
        switch (adapterType) {
            case 0:
                removeFromFriendList(user);
                break;
            case 1:
                addToFriendList(user);
                break;
        }
    }

    private void addToFriendList(User user) {
        searchResults.removeUser(user);
        addedFriends.addData(user);
    }

    private void removeFromFriendList(User user) {
        addedFriends.removeUser(user);
        searchResults.addData(user);
    }

    public void insertSearchResultSingleUser(User user) {
        searchResults.addData(user);
    }

    public void setLeftAdapter(UserListAdapter searchResults) {
        this.searchResults = searchResults;
    }

    public void setRightAdapter(UserListAdapter addedFriends) {
        this.addedFriends = addedFriends;
    }

    public void updateSearchResults(List<User> users) {
        searchResults.setUsers(users);
    }

    public void updateFriendList(List<User> users) {
        addedFriends.setUsers(users);
    }

    public void setApplyButton(Button button) {
        //applyButton = new WeakReference<>(button);
        button.setOnClickListener(new WriteToDatabase());
    }

    public void fetchDataFromDatabase(String searchData) {
        dbHandler.searchData(searchData);
    }

    //todo: write db writer
    class WriteToDatabase implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            dbHandler.addFriends(addedFriends.getUsers());
        }
    }
}
