package com.fasteque.leanback.widget.app;

import android.app.Fragment;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.fasteque.leanback.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoadingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoadingFragment extends Fragment {

	private int backgroundColor;
	private int progressColor;
	private int progressWidth;
	private int progressHeight;
	private ProgressBar progressBar;

	private static final String ARG_BACKGROUND_COLOR = "com.fasteque.leanback.widget.fragment.LoadingFragment.background.color";
	private static final String ARG_PROGRESS_COLOR = "com.fasteque.leanback.widget.fragment.LoadingFragment.progress.color";
	private static final String ARG_PROGRESS_WIDTH = "com.fasteque.leanback.widget.fragment.LoadingFragment.progress.width";
	private static final String ARG_PROGRESS_HEIGHT = "com.fasteque.leanback.widget.fragment.LoadingFragment.progress.height";

	public LoadingFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param backgroundColor
	 * @param progressColor
	 * @param progressWidth the width, either {@link #MATCH_PARENT},
	 *              {@link #WRAP_CONTENT} or a fixed size in pixels
	 * @param progressHeight the height, either {@link #MATCH_PARENT},
	 *               {@link #WRAP_CONTENT} or a fixed size in pixels
	 * @return A new instance of fragment LoadingFragment.
	 */
	public static LoadingFragment newInstance(@ColorInt int backgroundColor, @ColorInt int progressColor, int
			progressWidth, int progressHeight) {
		LoadingFragment fragment = new LoadingFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_BACKGROUND_COLOR, backgroundColor);
		args.putInt(ARG_PROGRESS_COLOR, progressColor);
		args.putInt(ARG_PROGRESS_WIDTH, progressWidth);
		args.putInt(ARG_PROGRESS_HEIGHT, progressHeight);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			backgroundColor = getArguments().getInt(ARG_BACKGROUND_COLOR);
			progressColor = getArguments().getInt(ARG_PROGRESS_COLOR);
			progressWidth = getArguments().getInt(ARG_PROGRESS_WIDTH);
			progressHeight = getArguments().getInt(ARG_PROGRESS_HEIGHT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_loading, container, false);

		FrameLayout loadingContainer = (FrameLayout) view.findViewById(R.id.fragment_loading_container);
		loadingContainer.setBackgroundColor(backgroundColor);

		progressBar = new ProgressBar(container.getContext());
		if (container instanceof FrameLayout) {
			FrameLayout.LayoutParams layoutParams =
					new FrameLayout.LayoutParams(progressWidth, progressHeight, Gravity.CENTER);
			progressBar.setLayoutParams(layoutParams);
		}

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			if (progressBar.getIndeterminateDrawable() != null) {
				progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(progressColor),
						PorterDuff.Mode.SRC_IN);
			}
		} else {
			ColorStateList stateList = ColorStateList.valueOf(progressColor);
			progressBar.setIndeterminateTintMode(PorterDuff.Mode.SRC_IN);
			progressBar.setIndeterminateTintList(stateList);
			progressBar.setProgressBackgroundTintMode(PorterDuff.Mode.SRC_IN);
			progressBar.setProgressBackgroundTintList(stateList);
			progressBar.setIndeterminate(true);
		}

		loadingContainer.addView(progressBar);

		return view;
	}

	/**
	 *
	 * @return
	 */
	public ProgressBar getProgressBar() {
		return progressBar;
	}
}
