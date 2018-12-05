package io.github.raduorleanu.sep4;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import io.github.raduorleanu.sep4.adapters.UserListAdapter;
import io.github.raduorleanu.sep4.interfaces.IRepository;
import io.github.raduorleanu.sep4.models.User;
import io.github.raduorleanu.sep4.repositories.AddFriendsRepository;
import io.github.raduorleanu.sep4.viewModels.UserViewModel;

public class AddFriendsActivity extends AppCompatActivity {

    private RecyclerView searchResults;
    private RecyclerView addedFriends;
    private Button applyButton;
    private Button searchButton;
    private TextInputEditText searchField;
    private IRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        searchResults = findViewById(R.id.recyclerview_added_friends);
        addedFriends = findViewById(R.id.recyclerview_all_friends);
        applyButton = findViewById(R.id.add_friends_apply_button);
        searchButton = findViewById(R.id.search_button);
        searchField = findViewById(R.id.name_field);

        Toolbar t = findViewById(R.id.toolbar_);
        t.setTitle("Add friends");
        setSupportActionBar(t);
        repository = AddFriendsRepository.getRepository();
        AddFriendsRepository.getRepository().setApplyButton(applyButton);

        searchButton.setOnClickListener(new SearchClick());

        fixDamnLayout();
        populateUserViews();
    }

    class SearchClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Editable s = searchField.getText();
            if(s == null) return;
            AddFriendsRepository r = (AddFriendsRepository) repository;
            r.fetchDataFromDatabase(searchField.getText().toString().trim());
        }
    }

    public void populateUserViews() {
        final UserListAdapter searchResultAdapter = new UserListAdapter(this, repository);
        final UserListAdapter addedFriendsAdapter = new UserListAdapter(this, repository);

        // adapter type for the repository to know who called
        searchResultAdapter.setAdapterType(0);
        addedFriendsAdapter.setAdapterType(1);

        searchResults.setAdapter(searchResultAdapter);
        addedFriends.setAdapter(addedFriendsAdapter);

        searchResults.setLayoutManager(new LinearLayoutManager(this));
        addedFriends.setLayoutManager(new LinearLayoutManager(this));

        UserViewModel searchResultModel = ViewModelProviders.of(this).get(UserViewModel.class);
        UserViewModel addedFriendsModel = ViewModelProviders.of(this).get(UserViewModel.class);

        searchResultModel.setRepository(repository);
        searchResultModel.setRightAdapter(searchResultAdapter);
        searchResultModel.setLeftAdapter(addedFriendsAdapter);

        addedFriendsModel.setRepository(repository);
        addedFriendsModel.setRightAdapter(searchResultAdapter);
        addedFriendsModel.setLeftAdapter(addedFriendsAdapter);

        searchResultModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                assert users != null;
                searchResultAdapter.setUsers(new ArrayList<>(users));
            }
        });

        addedFriendsModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                assert users != null;
                searchResultAdapter.setUsers(new ArrayList<>(users));
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void fixDamnLayout() {

        // get display size in pixels
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        // get recycle list layout
        ViewGroup.LayoutParams params1 = addedFriends.getLayoutParams();
        ViewGroup.LayoutParams params2 = searchResults.getLayoutParams();

        // get 50% of screen height
        Double h = height * 0.6;
        int res = h.intValue();

        // remove clipping -- list item height is 64 pixels
        int reminder = res % 64;
        int nicelySplit = res - reminder;

        // set layout height
        params1.height = nicelySplit;
        params2.height = nicelySplit;
        addedFriends.setLayoutParams(params1);
        searchResults.setLayoutParams(params2);
    }
}
