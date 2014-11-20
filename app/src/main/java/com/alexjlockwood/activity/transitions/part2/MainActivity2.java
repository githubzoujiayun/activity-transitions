package com.alexjlockwood.activity.transitions.part2;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexjlockwood.activity.transitions.R;

import static com.alexjlockwood.activity.transitions.Utils.RADIOHEAD_ALBUM_IDS;
import static com.alexjlockwood.activity.transitions.Utils.RADIOHEAD_ALBUM_NAMES;

public class MainActivity2 extends Activity {
    static final String EXTRA_CURRENT_ITEM_POSITION = "extra_current_item_position";

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, res.getInteger(R.integer.num_columns)));
        mRecyclerView.setAdapter(new CardAdapter());
    }

    private class CardAdapter extends RecyclerView.Adapter<CardHolder> {
        @Override
        public CardHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            return new CardHolder(inflater.inflate(R.layout.image_card_part2, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(CardHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return RADIOHEAD_ALBUM_IDS.length;
        }
    }

    private class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView mImage;
        private final TextView mText;
        private final View mBackground;
        private int mPosition;

        public CardHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mBackground = itemView.findViewById(R.id.card_view);
            mImage = (ImageView) itemView.findViewById(R.id.image);
            mText = (TextView) itemView.findViewById(R.id.card_text);
        }

        public void bind(int position) {
            mImage.setImageResource(RADIOHEAD_ALBUM_IDS[position]);
            mImage.setTransitionName(RADIOHEAD_ALBUM_NAMES[position]);
            mImage.setTag(RADIOHEAD_ALBUM_NAMES[position]);
            mText.setText(RADIOHEAD_ALBUM_NAMES[position]);
            mBackground.setTransitionName("background:" + mImage.getTransitionName());
            mPosition = position;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity2.this, DetailsActivity2.class);
            Pair<View, String> background = Pair.create(mBackground, mBackground.getTransitionName());
            Pair<View, String> image = Pair.create((View) mImage, mImage.getTransitionName());
            intent.putExtra(EXTRA_CURRENT_ITEM_POSITION, mPosition);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                    MainActivity2.this, background, image).toBundle());
        }
    }
}
