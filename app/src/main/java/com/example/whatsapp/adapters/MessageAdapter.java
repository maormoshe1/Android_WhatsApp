package com.example.whatsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.whatsapp.Message;
import com.example.whatsapp.R;

import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<Message> {
    public MessageAdapter(Context context, ArrayList<Message> messages){
        super(context, 0, messages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Message message = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            if(message.getSent()) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.sent_msg, parent, false);
            }
            else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.recive_msg, parent, false);
            }
        }
        // Lookup view for data population
        TextView tvMsg = (TextView) convertView.findViewById(R.id.tvMsg);
        TextView tvMsgTime = (TextView) convertView.findViewById(R.id.tvMsgTime);
        // Populate the data into the template view using the data object
        tvMsg.setText(message.getContent());
        tvMsgTime.setText(message.getCreated().substring(11,16));

        // Return the completed view to render on screen
        return convertView;
    }
}

