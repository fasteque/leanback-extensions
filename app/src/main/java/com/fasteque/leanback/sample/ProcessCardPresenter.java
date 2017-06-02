package com.fasteque.leanback.sample;

import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.fasteque.leanback.widget.card.ProcessCardView;

/*
 * A ProcessCardPresenter is used to generate Views and bind Objects to them on demand.
 * It contains a ProcessCardView.
 */
public class ProcessCardPresenter extends Presenter {
	private static final String TAG = "ProcessCardPresenter";

	private static final int CARD_WIDTH = 384;
	private static final int CARD_HEIGHT = 384;
	private Drawable mDefaultCardImage;

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent) {
		Log.d(TAG, "onCreateViewHolder");
		mDefaultCardImage = parent.getResources().getDrawable(R.drawable.movie);
		ProcessCardView cardView = new ProcessCardView(parent.getContext());
		return new ViewHolder(cardView);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, Object item) {
		Movie movie = (Movie) item;
		ProcessCardView cardView = (ProcessCardView) viewHolder.view;

		Log.d(TAG, "onBindViewHolder");
		if (movie.getCardImageUrl() != null) {
			cardView.setLayoutDimensions(CARD_WIDTH, CARD_HEIGHT);
			cardView.setOverlayIcon(R.drawable.ic_tag_faces_white_48dp);

			Glide.with(viewHolder.view.getContext())
					.load(movie.getCardImageUrl())
					.centerCrop()
					.error(mDefaultCardImage)
					.into(cardView.getImage());
			if (MainActivity.LOADING_PHASE == 1) {
				cardView.getOverlayContainer().setVisibility(View.VISIBLE);
				cardView.getOverlayIcon().setVisibility(View.GONE);
				cardView.getOverlayProgress().setVisibility(View.VISIBLE);
			} else if (MainActivity.LOADING_PHASE == 2) {
				cardView.getOverlayContainer().setVisibility(View.VISIBLE);
				cardView.getOverlayIcon().setVisibility(View.VISIBLE);
				cardView.getOverlayProgress().setVisibility(View.GONE);
			} else {
				cardView.getOverlayContainer().setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onUnbindViewHolder(ViewHolder viewHolder) {
		Log.d(TAG, "onUnbindViewHolder");
		ProcessCardView cardView = (ProcessCardView) viewHolder.view;
		// Remove references to images so that the garbage collector can free up memory.
		cardView.setImage(null);
		cardView.setOverlayIcon(null);
	}
}
