package io.github.raduorleanu.sep4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import io.github.raduorleanu.sep4.databaseHandlers.AddEventDbHandler;
import io.github.raduorleanu.sep4.models.Event;
import io.github.raduorleanu.sep4.util.Constants;

public class AddNewEventActivity extends AppCompatActivity {

    private EditText eventDate;
    private EditText eventDescription;
    private EditText eventListOfRequirements;
    //private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_event);

        eventDate = findViewById(R.id.add_event_date);
        eventDescription = findViewById(R.id.add_event_description);
        eventListOfRequirements = findViewById(R.id.add_event_requirements);
        //addButton = findViewById(R.id.add_event_add_button);

//        Toolbar t = findViewById(R.id.toolbar_);
//        setSupportActionBar(t);
    }

    public void addNewEvent(View view) {

        List<String> req = Arrays.asList(eventListOfRequirements.getText().toString().split(","));

        Event event = new Event(Constants.currentUser, 0, eventDate.getText().toString(),
                eventDescription.getText().toString(),
                Constants.currentUser.getAddress(), req);
        new AddEventDbHandler().addEvent(event);
        finish();
    }
}
