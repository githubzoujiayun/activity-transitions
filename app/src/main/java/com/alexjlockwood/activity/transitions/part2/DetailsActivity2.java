package com.alexjlockwood.activity.transitions.part2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexjlockwood.activity.transitions.R;

import static com.alexjlockwood.activity.transitions.Utils.RADIOHEAD_ALBUM_IDS;
import static com.alexjlockwood.activity.transitions.Utils.RADIOHEAD_ALBUM_NAMES;

public class DetailsActivity2 extends Activity {

    private static final SparseArray<Bitmap> BITMAP_CACHE = new SparseArray<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_part2);
        getWindow().getEnterTransition().excludeTarget(android.R.id.navigationBarBackground, true);

        View infoText = findViewById(R.id.text_container);
        TextView titleText = (TextView) infoText.findViewById(R.id.title);
        ImageView backgroundImage = (ImageView) findViewById(R.id.background_image);

        int selectedPosition = getIntent().getExtras().getInt(MainActivity2.EXTRA_CURRENT_ITEM_POSITION);
        backgroundImage.setTransitionName(RADIOHEAD_ALBUM_NAMES[selectedPosition]);
        findViewById(R.id.details_container).setTransitionName("background:" + RADIOHEAD_ALBUM_NAMES[selectedPosition]);
        backgroundImage.setImageResource(RADIOHEAD_ALBUM_IDS[selectedPosition]);
        titleText.setText(RADIOHEAD_ALBUM_NAMES[selectedPosition]);

        getWindow().getDecorView().getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getWindow().getDecorView().getViewTreeObserver().removeOnPreDrawListener(this);
                startPostponedEnterTransition();
                return true;
            }
        });

        int imageResource = RADIOHEAD_ALBUM_IDS[selectedPosition];
        Bitmap bitmap = BITMAP_CACHE.get(imageResource);
        if (BITMAP_CACHE.get(imageResource) == null) {
            backgroundImage.setImageResource(RADIOHEAD_ALBUM_IDS[selectedPosition]);
            bitmap = (((BitmapDrawable) backgroundImage.getDrawable()).getBitmap());
            BITMAP_CACHE.put(imageResource, bitmap);
        } else {
            backgroundImage.setImageBitmap(bitmap);
        }

    }
}
