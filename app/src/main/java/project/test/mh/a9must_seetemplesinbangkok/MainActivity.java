package project.test.mh.a9must_seetemplesinbangkok;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import project.test.mh.a9must_seetemplesinbangkok.Fragment.ContactFragment;
import project.test.mh.a9must_seetemplesinbangkok.Fragment.HomeFragment;
import project.test.mh.a9must_seetemplesinbangkok.Fragment.RandomFragment;

public class MainActivity extends AppCompatActivity{

    BottomNavigationView bottomNav;
    Fragment home, random, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --------------- NOTHING IN HERE -------------
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);
        // ---------------------------------------------

        home = new HomeFragment();
        random = new RandomFragment();
        contact = new ContactFragment();

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, home).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = home;
                            break;
                        case R.id.nav_random:
                            selectedFragment = random;
                            break;
                        case R.id.nav_contact:
                            selectedFragment = contact;
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
