package io.github.raduorleanu.sep4.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.AddUsersToEventUserSwapRepository;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<User> users;
    private IRepository repository;
    private int adapterType;

    public UserListAdapter(Context context, IRepository repository) {
        layoutInflater = LayoutInflater.from(context);
        users = new ArrayList<>();
        this.repository = repository;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    // todo: test this!
    public void removeUser(User user) {
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).get_id().equals(user.get_id())) {
                users.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, getItemCount());
            }
        }
    }

    public void addData(User user) {
        users.add(user);
        notifyItemInserted(users.size() - 1);
    }

    public List<User> getUsers() {
        return users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.user_recycle_item, viewGroup, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder alreadyGoingViewHolder, int i) {
        if(users != null) {
            final User user = users.get(i);
            alreadyGoingViewHolder.userName.setText(user.getName());
            alreadyGoingViewHolder.card.setOnClickListener(new CardClicked(user));
            new DownloadImageAsync(alreadyGoingViewHolder.userPicture).execute(user.getPicture());
        }
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        }
        return 0;
    }

    public void setAdapterType(int adapterType) {
        this.adapterType = adapterType;
    }

    class CardClicked implements View.OnClickListener {

        private User user;

        CardClicked(User user) {
            this.user = user;
        }

        @Override
        public void onClick(View view) {
            repository.swap(adapterType, user);
        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private final ImageView userPicture;
        private final TextView userName;
        private final ConstraintLayout card;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userPicture = itemView.findViewById(R.id.user_recycle_item_picture);
            userName = itemView.findViewById(R.id.user_recycle_item_username);
            card = itemView.findViewById(R.id.event_item_view);
        }
    }

    static class DownloadImageAsync extends AsyncTask<String, Void, Bitmap> {

        WeakReference<ImageView> imageViewWeakReference;

        DownloadImageAsync(ImageView image) {
            imageViewWeakReference = new WeakReference<>(image);
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap res = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                res = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.w("download_error", e.getMessage());
                e.printStackTrace();
            }
            return res;
        }

        protected void onPostExecute(Bitmap result) {
            imageViewWeakReference.get().setImageBitmap(result);
        }
    }
}
