package project.test.mh.a9must_seetemplesinbangkok.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import project.test.mh.a9must_seetemplesinbangkok.Adapter.ListViewAdapter;
import project.test.mh.a9must_seetemplesinbangkok.Adapter.SliderAdapter;
import project.test.mh.a9must_seetemplesinbangkok.Class.Model;
import project.test.mh.a9must_seetemplesinbangkok.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment{

    ListView listView;
    ListViewAdapter listViewAdapter;
    SliderView sliderView;
    ArrayList<Model> templeList;
    FirebaseFirestore db;
    SliderAdapter sliderAdapter;

    Integer documentRandom[] = new Integer[30];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

        this.getActivity().setTitle("Home");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Home");

        // SLIDER VIEW
        sliderView = v.findViewById(R.id.imageSlider);

        // LIST VIEW + FIREBASE FIRESTORE
        templeList = new ArrayList<>();
        listView = v.findViewById(R.id.listView);
        db = FirebaseFirestore.getInstance();

        // RANDOM DOCUMENT
        for(int i=0; i<documentRandom.length; i++){
            documentRandom[i] = i;      // Read line 124
        }
        Collections.shuffle(Arrays.asList(documentRandom));

        // BIND TITLE AND ICON (REQUIRE RANDOM NUMBER)
        bindTitleAndIcon();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView)menu.findItem(R.id.search_menu).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @SuppressLint("WrongConstant")
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    if(listViewAdapter != null){
                        listViewAdapter.filter("");
                        listView.clearTextFilter();
                        sliderView.setVisibility(0);
                    }
                }
                else {
                    if(listViewAdapter != null){
                        sliderView.setVisibility(8);
                        listViewAdapter.filter(newText);
                    }
                }
                return true;
            }
        });
    }

    private void bindTitleAndIcon() {
        db.collection("temples").orderBy("id").startAt(1).endAt(30) // start at ID "1"
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot document = task.getResult();
                    for(int i=0; i<30; i++) {

                        if (document.getDocuments().get(documentRandom[i]).exists()) {      // document.getDocuments() stored data start index 0 !!!
                            // TODO
                            ArrayList<String> icon = (ArrayList<String>) document.getDocuments().get(documentRandom[i]).get("icon");
                            Collections.shuffle(icon);

                            Model model = new Model(document.getDocuments().get(documentRandom[i]).get("title").toString(),
                                    document.getDocuments().get(documentRandom[i]).get("description").toString(),
                                    icon.get(0));

                            addToTempList(model);

                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }

                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void addToTempList(Model model) {
        this.templeList.add(model);

        // IF DATA IS FULL THEN DO
        if(templeList.size() == 30){
            // BIND TO ADAPTER
            bindToAdapter();
            // SEND TO SLIDER ADAPTER
            sendToSliderAdapter();
            // SEND TO RANDOM FRAGMENT !!
            sendToRandomFrag();
        }
    }

    private void sendToRandomFrag() {
        RandomFragment.templeRandomList = templeList;
    }

    private void bindToAdapter() {
        if(isAdded()){  // PREVENT APP CRASH
            listViewAdapter = new ListViewAdapter(this.getActivity(), templeList);
            listView.setAdapter(listViewAdapter);
        }

    }

    private void sendToSliderAdapter() {
        sliderAdapter = new SliderAdapter(this.getActivity(), templeList);

        // SLIDER VIEW
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(5); //set scroll delay in seconds
        sliderView.startAutoCycle();
    }
}
