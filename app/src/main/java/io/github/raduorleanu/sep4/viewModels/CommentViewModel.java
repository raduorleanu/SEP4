package io.github.raduorleanu.sep4.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.github.raduorleanu.sep4.adapters.CommentListAdapter;
import io.github.raduorleanu.sep4.models.Comment;
import io.github.raduorleanu.sep4.repositories.CommentRepository;

public class CommentViewModel extends AndroidViewModel {

    private CommentRepository repository;
//    private MutableLiveData<List<Comment>> comments;
    private MutableLiveData<List<String>> comments;
    private String eventId;

    public CommentViewModel(@NonNull Application application) {
        super(application);
        comments = new MutableLiveData<>();
//        repository = new CommentRepository(eventId);
    }

    public void setAdapter(CommentListAdapter adapter){
        repository.setAdapter(adapter);
    }

    public void setEventId(String eventId){
        this.eventId = eventId;
        repository = new CommentRepository(eventId);
    }

//    public MutableLiveData<List<Comment>> getComments(){
//        return comments;
//    }

//    ToDo: remove method below - only used for testing with Comment as with String
    public MutableLiveData<List<String>> getComments(){
        return comments;
    }
}