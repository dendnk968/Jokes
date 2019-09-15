package com.example.jokes;

import android.app.Activity;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jokes.API.APIHelper;
import com.example.jokes.API.Data.Joke;
import com.example.jokes.API.Data.Jokes;

import java.security.PublicKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JokesFragment extends Fragment implements View.OnClickListener {
    private EditText count;
    private Button button;
    private Jokes jokes;
    private ListView listView;
    private JokesAdapter adapter;
    private final String JOKES = "jokes";

    public JokesFragment() {
    }

    public JokesFragment(Jokes jokes) {
        this.jokes = jokes;
    }

    // TODO: Rename and change types and number of parameters
    public static JokesFragment newInstance(Jokes jokes) {
        JokesFragment fragment = new JokesFragment(jokes);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(JOKES, jokes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            jokes = (Jokes) savedInstanceState.get(JOKES);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jokes, container, false);
        count = view.findViewById(R.id.count);
        listView = view.findViewById(R.id.jokes_list);
        button = view.findViewById(R.id.button);
        adapter = new JokesAdapter(getActivity(), jokes.getJokes());
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (count.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Введите количество шуток", Toast.LENGTH_LONG).show();
            return;
        }
        int countJokes = Integer.parseInt(count.getText().toString());
        ((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(view.getWindowToken(), 0);
        APIHelper.getInstance().getJokesCount(countJokes).enqueue(new Callback<Jokes>() {
            @Override
            public void onResponse(Call<Jokes> call, Response<Jokes> response) {
                Jokes newJokes = response.body();
                if (!newJokes.getResult().equals("success")) {
                    Toast.makeText(getContext(), "Что-то пошло не так...", Toast.LENGTH_LONG).show();
                    return;
                }
                jokes.setJokes(newJokes.getJokes());
                adapter.clear();
                adapter.addAll(jokes.getJokes());
                adapter.setNotifyOnChange(true);
            }

            @Override
            public void onFailure(Call<Jokes> call, Throwable t) {
                Toast.makeText(getContext(), "Что-то пошло не так...", Toast.LENGTH_LONG).show();
            }
        });
    }
}
