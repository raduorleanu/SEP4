package io.github.raduorleanu.sep4.repositories;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.ParticipantAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.ParticipantDbHandler;
import io.github.raduorleanu.sep4.models.User;

public class ParticipantRepository {

    private MutableLiveData<List<User>> data;
    private ParticipantAdapter adapter;

    public ParticipantRepository(String eventId) {
        data = new MutableLiveData<>();
        new ParticipantDbHandler(this, "attendees", eventId);
    }

    public void setAdapter(ParticipantAdapter adapter) {
        this.adapter = adapter;
    }

    public void updateData(List<User> dataList) {
        adapter.setData(dataList);
    }

    public void removeData(User user) {
        adapter.removeData(user);
    }

    public void insertData(User User) {
        adapter.addData(User);
    }

    public MutableLiveData<List<User>> getData() {
        return data;
    }

}
