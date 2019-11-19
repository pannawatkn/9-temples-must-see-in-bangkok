package project.test.mh.a9must_seetemplesinbangkok.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import project.test.mh.a9must_seetemplesinbangkok.R;

public class ContactFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        this.getActivity().setTitle("Contact");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Contact");

        return v;
    }
}
