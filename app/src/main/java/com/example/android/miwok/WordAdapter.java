package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by grzegorzwilusz on 3/20/18.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(@NonNull Context context, ArrayList<Word> words, int colorResourceId) {
        super(context,0, words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        View linearLayoutView = listItemView.findViewById(R.id.linear_layout_background);
        // Find a color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the linearLayoutView
        linearLayoutView.setBackgroundColor(color);

        // Get the {@link Word} object located at this position in the list
        Word currentWordsPair = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current Word object and
        // set this text on the name TextView
        miwokTextView.setText(currentWordsPair.getMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWordsPair.getDefaultTranslation());

        ImageView imageWordView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWordsPair.hasImage()) {
            imageWordView.setImageResource(currentWordsPair.getResourceId());
            imageWordView.setVisibility(View.VISIBLE); // to make sure the view is visible
        } else {
            imageWordView.setVisibility(View.GONE); // GONE to completely hide an image (with took no extra empty space)
        }

        return listItemView;
    }
}
