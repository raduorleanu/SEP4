package io.github.raduorleanu.sep4.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.interfaces.IListAdapter;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder>
        implements IListAdapter {

    public ParticipantAdapter() {

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

    @Override
    public void setData(List<Object> objects) {

    }

    @Override
    public void addData(Object event) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewName);
        }
    }
}
