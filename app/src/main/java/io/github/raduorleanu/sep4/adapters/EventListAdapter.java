package io.github.raduorleanu.sep4.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.R;
import io.github.raduorleanu.sep4.interfaces.IListAdapter;
import io.github.raduorleanu.sep4.models.Event;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder>
        implements IListAdapter {

    private final LayoutInflater mInflater;
    private List<Event> data;

    public EventListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
//        this.data.add(new Event(new User("Momo"), "karaoke"));
//        this.data.add(new Event(new User("Mina"), "cooking"));
    }

    public void setData(List objects) {
        List<Event> events = new ArrayList<>();
        for (Object o : objects) {
            events.add((Event) o);
        }
        data = events;
        notifyDataSetChanged();
    }

    public void addData(Object event) {
        Log.w("Adapter", "adding to " + data.size() + event.toString());
        Event e = (Event) event;
        if(!data.contains(e)) {
            data.add(e);
        }

        notifyItemInserted(data.size() - 1);
    }

    @NonNull
    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.event_recycle_item, viewGroup, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.EventViewHolder eventViewHolder, int i) {
        if (data != null) {
            final Event event = data.get(i);
            eventViewHolder.eventDescription.setText(event.getDescription());
            eventViewHolder.eventAddress.setText(event.getLocation());
            eventViewHolder.eventUserName.setText(event.getHost().getName());
        } else {
            eventViewHolder.eventDescription.setText("No event");
        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        private final TextView eventDescription;
        private final TextView eventAddress;
        private final TextView eventUserName;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventDescription = itemView.findViewById(R.id.event_description);
            eventAddress = itemView.findViewById(R.id.event_address);
            eventUserName = itemView.findViewById(R.id.event_user_name);
        }
    }
}
