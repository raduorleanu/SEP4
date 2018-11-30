package io.github.raduorleanu.sep4.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.interfaces.IListAdapter;
import io.github.raduorleanu.sep4.models.User;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder> {

    List<User> data;

    public ParticipantAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.participant_recycle_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public void setData(List<User> users) {

    }


    public void addData(User user) {

    }

    public void removeData(User user) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView desc;
        CardView parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewName);
            desc = itemView.findViewById(R.id.textViewDesc);
            parentLayout = itemView.findViewById(R.id.parrent_layout);

        }
    }
}
