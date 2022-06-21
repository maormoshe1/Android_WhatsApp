package com.example.whatsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsapp.Contact;
import com.example.whatsapp.R;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, ArrayList<Contact> contacts){
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contact contact = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact, parent, false);
        }
        // Lookup view for data population
        TextView tvDN = (TextView) convertView.findViewById(R.id.tvDN);
        ImageView ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
        TextView tvLastMsg = (TextView) convertView.findViewById(R.id.tvLastMsg);
        TextView tvLastMsgTime = (TextView) convertView.findViewById(R.id.tvLastMsgTime);
        // Populate the data into the template view using the data object
        tvDN.setText(contact.getNickName());
        if(contact.getLast() == null) {
            tvLastMsg.setText("");
        }
        else{
            tvLastMsg.setText(contact.getLast());
        }
        if(contact.getLastdate() == null) {
            tvLastMsgTime.setText("");
        }
        else{
            tvLastMsgTime.setText(contact.getLastdate().substring(11,16));
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
