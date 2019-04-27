package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView mAlsoknownas;
    private TextView mdescription;
    private TextView mIngredients;
    private TextView mplaceoforigin;
    //private ImageView mimage;
    private Sandwich msandwich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mAlsoknownas=findViewById(R.id.also_known_tv);
        mdescription=findViewById(R.id.description_tv);
        mIngredients=findViewById(R.id.ingredients_tv);
        mplaceoforigin=findViewById(R.id.origin_tv);
        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
         msandwich = JsonUtils.parseSandwichJson(json);
        if (msandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();

        Picasso.with(this)
                .load(msandwich.getImage())
                .into(ingredientsIv);


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
    setTitle(msandwich.getMainName());
    mplaceoforigin.setText(msandwich.getPlaceOfOrigin());
    mAlsoknownas.setText(TextUtils.join(", ",msandwich.getAlsoKnownAs()));
    mIngredients.setText(TextUtils.join(", ",msandwich.getIngredients()));
    mdescription.setText(msandwich.getDescription());
    }
}
