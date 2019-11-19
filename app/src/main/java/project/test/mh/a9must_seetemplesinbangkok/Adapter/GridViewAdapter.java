package project.test.mh.a9must_seetemplesinbangkok.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import project.test.mh.a9must_seetemplesinbangkok.Class.Model;
import project.test.mh.a9must_seetemplesinbangkok.DescriptionActivity;
import project.test.mh.a9must_seetemplesinbangkok.R;

public class GridViewAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Model> templeList;

    public GridViewAdapter(Context context, int textViewResourceId, ArrayList<Model> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        templeList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.grid_view_item, null);

        TextView textView = v.findViewById(R.id.text_grid_view);
        ImageView imageView = v.findViewById(R.id.image_grid_view);

        textView.setText(templeList.get(position).getTitle());
        Glide.with(imageView).load(templeList.get(position).getIcon()).into(imageView);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("icon", "" + templeList.get(position).getIcon());
                intent.putExtra("title", "" + templeList.get(position).getTitle());
                intent.putExtra("description", "" + templeList.get(position).getDescription());
                context.startActivity(intent);
            }
        });

        return v;
    }
}
