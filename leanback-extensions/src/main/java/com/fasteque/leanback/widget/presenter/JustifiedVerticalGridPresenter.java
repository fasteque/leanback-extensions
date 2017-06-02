package com.fasteque.leanback.widget.presenter;

import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.support.v17.leanback.widget.VerticalGridView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasteque.leanback.R;


/**
 * A presenter that renders objects in a {@link VerticalGridView}, filling rows starting from the left.
 */

public class JustifiedVerticalGridPresenter extends VerticalGridPresenter {

	private VerticalGridView gridView;

	/**
	 * Constructs a JustifiedVerticalGridPresenter with the given parameters.
	 *
	 * @param focusZoomFactor Controls the zoom factor used when an item view is focused. One of
	 *         {@link android.support.v17.leanback.widget.FocusHighlight#ZOOM_FACTOR_NONE},
	 *         {@link android.support.v17.leanback.widget.FocusHighlight#ZOOM_FACTOR_SMALL},
	 *         {@link android.support.v17.leanback.widget.FocusHighlight#ZOOM_FACTOR_XSMALL},
	 *         {@link android.support.v17.leanback.widget.FocusHighlight#ZOOM_FACTOR_MEDIUM},
	 *         {@link android.support.v17.leanback.widget.FocusHighlight#ZOOM_FACTOR_LARGE}
	 * enabled dimming on focus.
	 */
	public JustifiedVerticalGridPresenter(int focusZoomFactor) {
		super(focusZoomFactor);
	}

	@Override
	protected ViewHolder createGridViewHolder(ViewGroup parent) {
		View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.presenter_justified_vertical_grid,
				parent, false);

		gridView = (VerticalGridView) root.findViewById(android.support.v17.leanback.R.id.browse_grid);

		return new ViewHolder(gridView);
	}

	/**
	 *
	 * @return The {@link VerticalGridView} used by this presenter.
	 */
	public VerticalGridView getGridView() {
		return gridView;
	}
}