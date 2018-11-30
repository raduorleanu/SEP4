package io.github.raduorleanu.sep4.adapters;

import android.content.Context;
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
import io.github.raduorleanu.sep4.databaseHandlers.ParticipantDbHandler;
import io.github.raduorleanu.sep4.interfaces.IListAdapter;
import io.github.raduorleanu.sep4.models.User;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder> {

    private final LayoutInflater minflater;
    private List<User> data;

    public ParticipantAdapter(Context context) {
        data = new ArrayList<>();
        minflater = LayoutInflater.from(context);
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
        if(data != null) {
            final User user = data.get(i);
            viewHolder.name.setText(user.getName());
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public void setData(List<User> users) {
        data = users;
        notifyDataSetChanged();
    }


    public void addData(User user) {
        data.add(user);
        notifyItemInserted(data.size() - 1);
    }

    public void removeData(User user) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).get_id().equals(user.get_id())) {
                data.remove(i);
                notifyItemChanged(i);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final CardView parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewName);
            parentLayout = itemView.findViewById(R.id.parrent_layout);

        }
    }
}
