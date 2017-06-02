package com.fasteque.leanback.sample;

import android.graphics.Color;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.ViewGroup;

import com.fasteque.leanback.widget.card.IconCardView;

/*
 * A IconCardPresenter is used to generate Views and bind Objects to them on demand.
 * It contains an IconCardView.
 */
public class IconCardPresenter extends Presenter {
	private static final String TAG = "IconCardPresenter";

	private static final int CARD_WIDTH = 384;
	private static final int CARD_HEIGHT = 384;

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent) {
		Log.d(TAG, "onCreateViewHolder");
		IconCardView cardView = new IconCardView(parent.getContext());
		return new ViewHolder(cardView);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, Object item) {
		Movie movie = (Movie) item;
		IconCardView cardView = (IconCardView) viewHolder.view;

		Log.d(TAG, "onBindViewHolder");
		if (movie.getCardImageUrl() != null) {
			cardView.setCardBackgroundColor(Color.DKGRAY);
			cardView.setDetailBackgroundColor(Color.GRAY);
			cardView.setPrimaryText(movie.getTitle());
			cardView.setSecondaryText(movie.getStudio());
			cardView.setLayoutDimensions(CARD_WIDTH, CARD_HEIGHT);
			cardView.setIconDimensions(CARD_WIDTH / 2, CARD_HEIGHT / 2);
			cardView.setIcon(R.drawable.ic_tag_faces_white_48dp);
		}
	}

	@Override
	public void onUnbindViewHolder(ViewHolder viewHolder) {
		Log.d(TAG, "onUnbindViewHolder");
		IconCardView cardView = (IconCardView) viewHolder.view;
		// Remove references to images so that the garbage collector can free up memory.
		cardView.setIcon(null);
	}
}
