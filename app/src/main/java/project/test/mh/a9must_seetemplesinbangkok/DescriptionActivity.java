package project.test.mh.a9must_seetemplesinbangkok;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView description = findViewById(R.id.descriptionTv);
        ImageView imageView = findViewById(R.id.imageView);

        // Set to Scroll
        description.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        String mIcon = intent.getStringExtra("icon");
        String mTitle = intent.getStringExtra("title");
        String mDescription = intent.getStringExtra("description");

        Glide.with(imageView).load(mIcon).into(imageView);
        actionBar.setTitle(mTitle);
        description.setText(mDescription);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
