package project.test.mh.a9must_seetemplesinbangkok.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import project.test.mh.a9must_seetemplesinbangkok.Class.Model;
import project.test.mh.a9must_seetemplesinbangkok.DescriptionActivity;
import project.test.mh.a9must_seetemplesinbangkok.R;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    Context context;
    ArrayList<Model> templeList;
    Integer number[] = new Integer[30];

    public SliderAdapter(Context context, ArrayList<Model> templeList) {
        this.context = context;
        this.templeList = templeList;

        // RANDOM NUMBER
        for(int i=0; i<number.length; i++){
            number[i] = i;
        }
        Collections.shuffle(Arrays.asList(number));
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        if(templeList.size() >= 30){      // Wait until arraylist is full
            viewHolder.textViewDescription.setText(templeList.get(number[position]).getTitle());
            Glide.with(viewHolder.itemView).load(templeList.get(number[position]).getIcon()).into(viewHolder.imageViewBackground);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DescriptionActivity.class);
                    intent.putExtra("icon", "" + templeList.get(number[position]).getIcon());
                    intent.putExtra("title", "" + templeList.get(number[position]).getTitle());
                    intent.putExtra("description", "" + templeList.get(number[position]).getDescription());
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 5;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }

    }
}