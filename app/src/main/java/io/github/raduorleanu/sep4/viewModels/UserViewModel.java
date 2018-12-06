package io.github.raduorleanu.sep4.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.UserListAdapter;
import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.AddUsersToEventUserSwapRepository;

public class UserViewModel extends AndroidViewModel {

    private IRepository repository;
    private MutableLiveData<List<User>> users;

    public UserViewModel(@NonNull Application application) {
        super(application);
        users = new MutableLiveData<>();
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    public void setRightAdapter(UserListAdapter adapter) {
        repository.setRightAdapter(adapter);
    }

    public void setLeftAdapter(UserListAdapter adapter) {
        repository.setLeftAdapter(adapter);
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }
}
