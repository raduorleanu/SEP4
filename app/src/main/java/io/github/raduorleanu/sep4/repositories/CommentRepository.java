package io.github.raduorleanu.sep4.repositories;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.CommentListAdapter;
import io.github.raduorleanu.sep4.databaseHandlers.CommentDbHandler;
import io.github.raduorleanu.sep4.models.Comment;

public class CommentRepository {
    private CommentListAdapter adapter;
    private CommentDbHandler commentDbHandler;
//    private MutableLiveData<List<Comment>> data;
    private MutableLiveData<List<String>> data;
    private String eventId;

    public CommentRepository(String eventId) {
        this.eventId = eventId;
        this.data = new MutableLiveData<>();
        new CommentDbHandler(this, "comments", eventId);
    }

    /**
     * CommentRepository points to the adapter.
     * This is done to change the data in the MutableLiveData with the data that you are getting from Firebase,
     * because instead of doing SQL commands and getting Comments 1 by 1. Firebase gives you a list of all the comments,
     * Therefore, instead of appending them to the list, you set the list to the result from Firebase.
     * @param adapter
     */
    public void setAdapter(CommentListAdapter adapter) {
        this.adapter = adapter;
    }

//    public void updateData(List<String> comments){
//        adapter.addData(comments);
//    }

    // ToDo: remove method below - only used for initial testing with String
    public void updateData(List<String> comments){
        adapter.addData(comments);
    }
}
