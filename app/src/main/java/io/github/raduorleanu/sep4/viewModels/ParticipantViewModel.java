package io.github.raduorleanu.sep4.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.ParticipantAdapter;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.EventRepository;
import io.github.raduorleanu.sep4.repositories.ParticipantRepository;

public class ParticipantViewModel extends AndroidViewModel {
    private ParticipantRepository repository;
    private MutableLiveData<List<User>> users;

    public ParticipantViewModel(@NonNull Application application) {
        super(application);
        repository = new ParticipantRepository();
        users = new MutableLiveData<>();
    }

    public void setAdapter(ParticipantAdapter adapter) {
        repository.setAdapter(adapter);
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }
}
