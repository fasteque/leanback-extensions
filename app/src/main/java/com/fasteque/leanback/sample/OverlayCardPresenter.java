package com.fasteque.leanback.sample;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.fasteque.leanback.widget.card.OverlayCardView;

/*
 * A OverlayCardPresenter is used to generate Views and bind Objects to them on demand.
 * It contains an OverlayCardView.
 */
public class OverlayCardPresenter extends Presenter {
	private static final String TAG = "OverlayCardPresenter";

	private static final int CARD_WIDTH = 384;
	private static final int CARD_HEIGHT = 384;
	private Drawable mDefaultCardImage;

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent) {
		Log.d(TAG, "onCreateViewHolder");
		mDefaultCardImage = parent.getResources().getDrawable(R.drawable.movie);
		OverlayCardView cardView = new OverlayCardView(parent.getContext());
		return new ViewHolder(cardView);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, Object item) {
		Movie movie = (Movie) item;
		OverlayCardView cardView = (OverlayCardView) viewHolder.view;

		Log.d(TAG, "onBindViewHolder");
		if (movie.getCardImageUrl() != null) {
			cardView.setCardBackgroundColor(Color.GRAY);
			cardView.setLayoutDimensions(CARD_WIDTH, CARD_HEIGHT);
			cardView.setImageOverlay(R.drawable.ic_play_circle_outline_white_48dp);
			cardView.setImageOverlayDimensions(CARD_WIDTH / 3, CARD_HEIGHT / 3);
			Glide.with(viewHolder.view.getContext())
					.load(movie.getCardImageUrl())
					.centerCrop()
					.error(mDefaultCardImage)
					.into(cardView.getImage());
		}
	}

	@Override
	public void onUnbindViewHolder(ViewHolder viewHolder) {
		Log.d(TAG, "onUnbindViewHolder");
		OverlayCardView cardView = (OverlayCardView) viewHolder.view;
		// Remove references to images so that the garbage collector can free up memory.
		cardView.setImage(null);
		cardView.setImageOverlay(null);
	}
}
