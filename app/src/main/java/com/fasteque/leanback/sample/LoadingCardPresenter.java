package com.fasteque.leanback.sample;

import android.graphics.Color;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.ViewGroup;

import com.fasteque.leanback.widget.card.LoadingCardView;

/*
 * A LoadingCardPresenter is used to generate Views and bind Objects to them on demand.
 * It contains a LoadingCardView.
 */
public class LoadingCardPresenter extends Presenter {
	private static final String TAG = "LoadingCardPresenter";

	private static final int CARD_WIDTH = 384;
	private static final int CARD_HEIGHT = 384;

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent) {
		Log.d(TAG, "onCreateViewHolder");
		LoadingCardView cardView = new LoadingCardView(parent.getContext());
		return new ViewHolder(cardView);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, Object item) {
		Movie movie = (Movie) item;
		LoadingCardView cardView = (LoadingCardView) viewHolder.view;

		Log.d(TAG, "onBindViewHolder");
		if (movie.getCardImageUrl() != null) {
			cardView.setCardBackgroundColor(Color.WHITE);
			cardView.setProgressColor(Color.CYAN);
			cardView.setLayoutDimensions(CARD_WIDTH, CARD_HEIGHT);
		}
	}

	@Override
	public void onUnbindViewHolder(ViewHolder viewHolder) {
		// Nothing to clean up.
	}
}
