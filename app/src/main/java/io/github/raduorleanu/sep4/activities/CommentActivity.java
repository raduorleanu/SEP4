package io.github.raduorleanu.sep4.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.adapters.CommentListAdapter;
import io.github.raduorleanu.sep4.models.Comment;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.viewModels.CommentViewModel;

public class CommentActivity extends AppCompatActivity {

    private static final String TAG = "CommentActivity";
    private CommentListAdapter commentAdapter;
    private String eventId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_recycle_view);

        enterCommentIntent();

        RecyclerView recyclerView = findViewById(R.id.commentRecyclerView);
        commentAdapter = new CommentListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapter);

        // get ViewModel
        CommentViewModel commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        commentViewModel.setEventId(this.eventId);
        commentViewModel.setAdapter(commentAdapter);

        // perform binding between commentViewModel MutableLiveData and comment list
//        commentViewModel.getComments().observe(this, new Observer<List<Comment>>() {
//            @Override
//
//            public void onChanged(@Nullable List<Comment> comments) {
//                assert comments != null;
//                commentAdapter.setData(new ArrayList<>(comments));
//            }
//        });
        commentViewModel.getComments().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> comments) {
                assert comments != null;
                commentAdapter.setData(new ArrayList<>(comments));
            }
        });
    }

    private void enterCommentIntent() {
        Log.w(TAG, "The comment intent has started");
        if (getIntent().hasExtra("comment")) {
            Event event = (Event) getIntent().getSerializableExtra("comment");
            eventId = event.get_id();
        }
    }
}
