package io.github.raduorleanu.sep4.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.UserListAdapter;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.AddUsersToEventUserSwapRepository;

public class UserViewModel extends AndroidViewModel {

    private AddUsersToEventUserSwapRepository repository;
    private MutableLiveData<List<User>> users;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = AddUsersToEventUserSwapRepository.getRepository();
        users = new MutableLiveData<>();
    }

    public void setGoingAdapter(UserListAdapter adapter) {
        repository.setGoingAdapter(adapter);
    }

    public void setNotGoingAdapter(UserListAdapter adapter) {
        repository.setNotGoingAdapter(adapter);
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }
}
