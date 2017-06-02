package com.fasteque.leanback.sample;

import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

import static com.fasteque.leanback.sample.MovieList.MOVIE_CATEGORY;

public class CardPresenterSelector extends PresenterSelector {
	@Override
	public Presenter getPresenter(Object o) {
		if (o instanceof Movie) {
			if (((Movie) o).getCategory().equals(MOVIE_CATEGORY[0])) {
				return new IconCardPresenter();
			} else if (((Movie) o).getCategory().equals(MOVIE_CATEGORY[1])) {
				return new LoadingCardPresenter();
			} else if (((Movie) o).getCategory().equals(MOVIE_CATEGORY[2])) {
				return new OverlayCardPresenter();
			} else {
				return new ProcessCardPresenter();
			}
		} else {
			return new CardPresenter();
		}
	}
}
