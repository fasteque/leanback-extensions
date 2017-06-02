package com.fasteque.leanback.sample;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import static com.fasteque.leanback.sample.MainActivity.LOADING_PHASE;
import static com.fasteque.leanback.sample.MovieList.MOVIE_CATEGORY;

public class MainFragment extends BrowseFragment {
	private static final String TAG = "MainFragment";

	private static final int BACKGROUND_UPDATE_DELAY = 300;
	private static final int GRID_ITEM_WIDTH = 200;
	private static final int GRID_ITEM_HEIGHT = 200;
	private static final int NUM_ROWS = 4;
	private static final int NUM_COLS = 6;

	private final Handler mHandler = new Handler();
	private ArrayObjectAdapter mRowsAdapter;
	private Drawable mDefaultBackground;
	private DisplayMetrics mMetrics;
	private Timer mBackgroundTimer;
	private URI mBackgroundURI;
	private BackgroundManager mBackgroundManager;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onActivityCreated(savedInstanceState);

		prepareBackgroundManager();
		setupUIElements();
		loadRows();
		setupEventListeners();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (null != mBackgroundTimer) {
			Log.d(TAG, "onDestroy: " + mBackgroundTimer.toString());
			mBackgroundTimer.cancel();
		}
	}

	private void loadRows() {
		List<Movie> list = MovieList.setupMovies();

		mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
		CardPresenterSelector cardPresenterSelector = new CardPresenterSelector();

		int i;
		for (i = 0; i < NUM_ROWS; i++) {
			if (i != 0) {
				Collections.shuffle(list);
			}
			ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenterSelector);
			for (int j = 0; j < NUM_COLS; j++) {
				listRowAdapter.add(list.get(j % 4));
			}
			HeaderItem header = new HeaderItem(i, MovieList.MOVIE_CATEGORY[i]);
			mRowsAdapter.add(new ListRow(header, listRowAdapter));
		}

		HeaderItem gridHeader = new HeaderItem(i, "Misc");

		GridItemPresenter mGridPresenter = new GridItemPresenter();
		ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
		gridRowAdapter.add(getResources().getString(R.string.justified_vertical_grid_view));
		gridRowAdapter.add(getString(R.string.loading_fragment));
		mRowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));

		setAdapter(mRowsAdapter);

	}

	private void prepareBackgroundManager() {
		mBackgroundManager = BackgroundManager.getInstance(getActivity());
		mBackgroundManager.attach(getActivity().getWindow());
		mDefaultBackground = getResources().getDrawable(R.drawable.default_background);
		mMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
	}

	private void setupUIElements() {
		setTitle(getString(R.string.browse_title)); // Badge, when set, takes precedent
		// over title
		setHeadersState(HEADERS_ENABLED);
		setHeadersTransitionOnBackEnabled(true);

		// set fastLane (or headers) background color
		setBrandColor(getResources().getColor(R.color.fastlane_background));
		// set search icon color
		setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
	}

	private void setupEventListeners() {
		setOnSearchClickedListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Toast.makeText(getActivity(), "Implement your own in-app search", Toast.LENGTH_LONG)
						.show();
			}
		});

		setOnItemViewClickedListener(new ItemViewClickedListener());
		setOnItemViewSelectedListener(new ItemViewSelectedListener());
	}

	protected void updateBackground(String uri) {
		int width = mMetrics.widthPixels;
		int height = mMetrics.heightPixels;
		Glide.with(getActivity())
				.load(uri)
				.centerCrop()
				.error(mDefaultBackground)
				.into(new SimpleTarget<GlideDrawable>(width, height) {
					@Override
					public void onResourceReady(GlideDrawable resource,
					                            GlideAnimation<? super GlideDrawable>
							                            glideAnimation) {
						mBackgroundManager.setDrawable(resource);
					}
				});
		mBackgroundTimer.cancel();
	}

	private void startBackgroundTimer() {
		if (null != mBackgroundTimer) {
			mBackgroundTimer.cancel();
		}
		mBackgroundTimer = new Timer();
		mBackgroundTimer.schedule(new UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY);
	}

	private void refreshView() {
		// Refresh the grid without actually reloading it from scratch.
		ArrayObjectAdapter adapter = ((ArrayObjectAdapter) getAdapter());
		if (adapter != null && adapter.size() > 0) {
			adapter.notifyArrayItemRangeChanged(0, adapter.size());
		}
	}

	private final class ItemViewClickedListener implements OnItemViewClickedListener {
		@Override
		public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
		                          RowPresenter.ViewHolder rowViewHolder, Row row) {

			if (item instanceof Movie) {
				if (((Movie) item).getCategory().equals(MOVIE_CATEGORY[3])) {
					LOADING_PHASE = 1;
					refreshView();

					Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							LOADING_PHASE = 2;
							refreshView();
						}
					}, 3000);

				} else {
					Toast.makeText(getActivity(), "I'm a card, you clicked me!", Toast.LENGTH_SHORT).show();
				}
			} else if (item instanceof String) {
				if (((String) item).indexOf(getString(R.string.loading_fragment)) >= 0) {
					Intent intent = new Intent(getActivity(), LoadingActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(getActivity(), ((String) item), Toast.LENGTH_SHORT)
							.show();
				}
			}
		}
	}

	private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
		@Override
		public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
		                           RowPresenter.ViewHolder rowViewHolder, Row row) {
			if (item instanceof Movie) {
				mBackgroundURI = ((Movie) item).getBackgroundImageURI();
				startBackgroundTimer();
			}

		}
	}

	private class UpdateBackgroundTask extends TimerTask {

		@Override
		public void run() {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					if (mBackgroundURI != null) {
						updateBackground(mBackgroundURI.toString());
					}
				}
			});

		}
	}

	private class GridItemPresenter extends Presenter {
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent) {
			TextView view = new TextView(parent.getContext());
			view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
			view.setFocusable(true);
			view.setFocusableInTouchMode(true);
			view.setBackgroundColor(getResources().getColor(R.color.default_background));
			view.setTextColor(Color.WHITE);
			view.setGravity(Gravity.CENTER);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(ViewHolder viewHolder, Object item) {
			((TextView) viewHolder.view).setText((String) item);
		}

		@Override
		public void onUnbindViewHolder(ViewHolder viewHolder) {
		}
	}
}
