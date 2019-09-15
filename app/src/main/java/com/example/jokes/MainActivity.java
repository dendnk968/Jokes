package com.example.jokes;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.jokes.API.Data.Joke;
import com.example.jokes.API.Data.Jokes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private final String JOKES = "jokes";
    private Jokes jokes;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_jokes:
                    setTitle(R.string.title_jokes);
                    loadFragment(JokesFragment.newInstance(jokes));
                    return true;
                case R.id.navigation_web:
                    setTitle(R.string.title_web);
                    loadFragment(WebFragment.newInstance());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            jokes = new Jokes(new ArrayList<Joke>());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, JokesFragment.newInstance(jokes)).commit();
        } else
            jokes = (Jokes) savedInstanceState.get(JOKES);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(JOKES, jokes);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
    }

}
