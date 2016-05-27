package com.example.nit.classnotes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nit.classnotes.R;

/**
 * Created by NIT on 4/9/2016.
 */
public class ListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] title;
    private String[] description;

    public ListAdapter(Activity context, String[] title,String[] description) {
        super(context, R.layout.list_item, title);
        this.context = context;
        this.title = title;
        this.description = description;
    }

    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater =  context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);
        TextView textView1 =(TextView) rowView.findViewById(R.id.info_text);
        TextView textView2 = (TextView)rowView.findViewById(R.id.textView);
textView1.setText(title[position]);
textView2.setText(description[position]);

final ImageView imageView = (ImageView)rowView.findViewById(R.id.imageView2);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.likea);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), title[position], Toast.LENGTH_LONG).show();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), description[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
