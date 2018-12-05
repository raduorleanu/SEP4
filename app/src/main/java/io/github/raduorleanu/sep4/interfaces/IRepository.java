package io.github.raduorleanu.sep4.interfaces;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.EventListAdapter;
import io.github.raduorleanu.sep4.adapters.UserListAdapter;
import io.github.raduorleanu.sep4.models.User;

public interface IRepository {

    void setRightAdapter(UserListAdapter adapter);
    void setLeftAdapter(UserListAdapter adapter);
    void swap(int adapterType, User user);

}
