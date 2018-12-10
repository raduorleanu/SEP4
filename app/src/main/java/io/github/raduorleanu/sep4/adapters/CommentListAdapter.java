package io.github.raduorleanu.sep4.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.models.Comment;

import static android.support.constraint.Constraints.TAG;

/**
 * Comment List Adapter points to actual data.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {
    private LayoutInflater inflater;
//    private List<Comment> data;
    private List<String> data;

    public CommentListAdapter(Context context) {
        this.data = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Take compiled XML code and place it into each viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.comment_recycle_item, parent, false);
        return new CommentViewHolder(view);
    }

    /**
     * Sets the data from the data source on each view
     *
     * @param commentViewHolder represents the data source
     * @param position          represents position in the list
     */
    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int position) {
        Log.e(TAG, "onBindViewHolder: called");
        if (data != null) {
//            final Comment comment = data.get(position);
//            commentViewHolder.commentUserName.setText(comment.getUser().getName());
//            commentViewHolder.commentUserEmail.setText(comment.getUser().getEmail());
//            commentViewHolder.commentText.setText(comment.getMessage());
            final String comment = data.get(position);
            commentViewHolder.commentText.setText(comment);
        } else {
            commentViewHolder.commentText.setText(R.string.lack_of_comments);
        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

//    public void addData(List<Comment> comments) {
//        data = comments;
//        notifyDataSetChanged();
//    }

    public void addData(List<String> comments) {
        data = comments;
        notifyDataSetChanged();
    }

    /**
     * Used by CommentActivity
     * Used by CommentRepository
     *
     * @param comments
     */
//    public void setData(List<Comment> comments) {
//        data = comments;
//        notifyDataSetChanged();
//    }
    public void setData(List<String> comments) {
        data = comments;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder identifies the elements in a particular comment card
     */
    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private final TextView commentUserName;
        private final TextView commentUserEmail;
        private final TextView commentText;
        private final Button commentButton;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            commentUserName = itemView.findViewById(R.id.comment_user_name);
            commentUserEmail = itemView.findViewById(R.id.comment_user_email);
            commentText = itemView.findViewById(R.id.comment_text);
            commentButton = itemView.findViewById(R.id.reply_to_comment_button);
        }
    }
}
