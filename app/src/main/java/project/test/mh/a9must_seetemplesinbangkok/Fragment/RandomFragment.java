package project.test.mh.a9must_seetemplesinbangkok.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;

import project.test.mh.a9must_seetemplesinbangkok.Adapter.GridViewAdapter;
import project.test.mh.a9must_seetemplesinbangkok.Class.Model;
import project.test.mh.a9must_seetemplesinbangkok.R;

public class RandomFragment extends Fragment {

    GridView gridView;
    GridViewAdapter gridAdapter;
    Button btn_random;
    static ArrayList<Model> templeRandomList;
    ArrayList<Model> temp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_random, container, false);

        this.getActivity().setTitle("Random");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Random");

        gridView = v.findViewById(R.id.random_fragment_grid_view);
        btn_random = v.findViewById(R.id.btn_random_fragment);

        random9Temple();

        return v;
    }

    private void random9Temple() {
        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = new ArrayList<>(9);
                Collections.shuffle(templeRandomList);

                for(int i=0; i<9; i++){
                    temp.add(templeRandomList.get(i));
                }

                bindToAdapterAndView(temp);
            }
        });
    }

    private void bindToAdapterAndView(ArrayList<Model> temp) {
        gridAdapter = new GridViewAdapter(this.getActivity(), R.layout.grid_view_item, temp);
        gridView.setAdapter(gridAdapter);
    }
}
