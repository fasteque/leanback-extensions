package com.fasteque.leanback.widget.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.fasteque.leanback.R;


/**
 * A subclass of {@link BaseCardView}.
 */
public class LoadingCardView extends BaseCardView {

	private RelativeLayout loadingLayout;
	private ProgressBar progressBar;

	/**
	 * Create a LoadingCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 */
	public LoadingCardView(Context context) {
		this(context, null);
	}

	/**
	 * Create a LoadingCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param attrs
	 *          The attributes of the XML tag that is inflating the view.
	 */
	public LoadingCardView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.imageCardViewStyle);
	}

	/**
	 * Create a LoadingCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param styleResId
	 *          The resourceId of the theme you want to apply.
	 */
	public LoadingCardView(Context context, int styleResId) {
		super(new ContextThemeWrapper(context, styleResId), null, 0);
		initLoadingCardView(styleResId);
	}

	/**
	 * Create a LoadingCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param attrs
	 *          The attributes of the XML tag that is inflating the view.
	 * @param defStyleAttr
	 *          An attribute in the current theme that contains a reference to a style resource that supplies default
	 *          values for the view. Can be 0 to not look for defaults.
	 */
	public LoadingCardView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(getStyledContext(context, attrs, defStyleAttr), attrs, defStyleAttr);
		initLoadingCardView(getLoadingCardViewStyle(context, attrs, defStyleAttr));
	}

	@Override
	public boolean hasOverlappingRendering() {
		return false;
	}

	private void initLoadingCardView(int styleResId) {
		setFocusable(false);
		setFocusableInTouchMode(false);
		setCardType(CARD_TYPE_MAIN_ONLY);

		Context context = getContext();

		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.card_view_loading, this);
		TypedArray cardAttrs = context.obtainStyledAttributes(styleResId, R.styleable.LoadingCardView);

		loadingLayout = (RelativeLayout) findViewById(R.id.card_view_loading_container);
		progressBar = (ProgressBar) findViewById(R.id.card_view_loading_progress);

		int backgroundColor = cardAttrs.getInt(R.styleable.LoadingCardView_loading_background_color,
				ContextCompat.getColor(context, R.color.default_background));

		int progressColor = cardAttrs.getInt(R.styleable.LoadingCardView_loading_progress_color,
				ContextCompat.getColor(context, R.color.default_accent));

		setCardBackgroundColor(backgroundColor);
		setProgressColor(progressColor);

		cardAttrs.recycle();
	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	public void setLayoutDimensions(int width, int height) {
		ViewGroup.LayoutParams lp = loadingLayout.getLayoutParams();
		lp.width = width;
		lp.height = height;
		loadingLayout.setLayoutParams(lp);
	}

	/**
	 * @param color
	 */
	public void setCardBackgroundColor(@ColorInt int color) {
		setBackgroundColor(color);
	}

	/**
	 * @param color
	 */
	public void setProgressColor(@ColorInt int color) {
		progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
	}

	/**
	 * @return
	 */
	public boolean isLoading() {
		return progressBar.getVisibility() == View.VISIBLE;
	}

	/**
	 * @param isLoading
	 */
	public void setLoading(boolean isLoading) {
		progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
	}

	/**
	 *
	 * @return
	 */
	public RelativeLayout getLayout() {
		return loadingLayout;
	}

	/**
	 *
	 * @return
	 */
	public ProgressBar getProgressBar() {
		return progressBar;
	}

	private static Context getStyledContext(Context context, AttributeSet attrs, int defStyleAttr) {
		int style = getLoadingCardViewStyle(context, attrs, defStyleAttr);
		return new ContextThemeWrapper(context, style);
	}

	private static int getLoadingCardViewStyle(Context context, AttributeSet attrs, int defStyleAttr) {
		int style = attrs == null ? 0 : attrs.getStyleAttribute();

		if (style == 0) {
			TypedArray styledAttrs = context.obtainStyledAttributes(R.styleable.LoadingCardView);
			style = styledAttrs.getResourceId(R.styleable.LoadingCardView_loading_theme, 0);
			styledAttrs.recycle();
		}

		return style;
	}
}