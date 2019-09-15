package com.example.jokes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jokes.API.Data.Joke;

import java.util.List;

public class JokesAdapter extends ArrayAdapter<Joke> {
    private final Activity context;
    private final List<Joke> jokes;

    public JokesAdapter(Activity context, List<Joke> jokes) {
        super(context, android.R.layout.simple_expandable_list_item_1, jokes);
        this.context = context;
        this.jokes = jokes;
    }

    static class ViewHolder {
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null, true);
            holder = new ViewHolder();
            holder.textView = (TextView) rowView.findViewById(android.R.id.text1);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textView.setText(jokes.get(position).getJoke());
        return rowView;
    }


}
