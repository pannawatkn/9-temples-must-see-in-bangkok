package project.test.mh.a9must_seetemplesinbangkok.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import project.test.mh.a9must_seetemplesinbangkok.Class.Model;
import project.test.mh.a9must_seetemplesinbangkok.DescriptionActivity;
import project.test.mh.a9must_seetemplesinbangkok.R;

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<Model> modellist;
    ArrayList<Model> arrayList;

    public ListViewAdapter(Context context, List<Model> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder{
        TextView mTitleTv;
        ImageView mIconIv;
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row_temples, null);

            holder.mTitleTv = view.findViewById(R.id.textViewTitle);
//            holder.mDescTv = view.findViewById(R.id.textViewDes);
            holder.mIconIv = view.findViewById(R.id.ivIcon);

            view.setTag(holder);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        holder.mTitleTv.setText(modellist.get(position).getTitle());
//        holder.mDescTv.setText(modellist.get(position).getDesc());
//        holder.mIconIv.setImageResource(modellist.get(position).getIcon());
        Glide.with(holder.mIconIv).load(modellist.get(position).getIcon()).into(holder.mIconIv);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(mContext, DescriptionActivity.class);
                    intent.putExtra("icon", "" + modellist.get(position).getIcon());
                    intent.putExtra("title", "" + modellist.get(position).getTitle());
                    intent.putExtra("description", "" + modellist.get(position).getDescription());
                    mContext.startActivity(intent);
            }
        });


        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (Model model : arrayList){
                if (model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
