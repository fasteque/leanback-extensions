package com.fasteque.leanback.widget.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.fasteque.leanback.R;


/**
 * A subclass of {@link BaseCardView} with an {@link ImageView} as its main region.
 */
public class ProcessCardView extends BaseCardView {

	private FrameLayout layout;
	private ImageView image;
	private LinearLayout overlayContainer;
	private ProgressBar progressBar;
	private ImageView overlayIcon;

	/**
	 * Create a ProcessCardView.
	 *
	 * @param context
	 *         The Context the view is running in, through which it can access the current theme, resources, etc.
	 */
	public ProcessCardView(Context context) {
		this(context, null);
	}

	/**
	 * Create a ProcessCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param attrs
	 *          The attributes of the XML tag that is inflating the view.
	 */
	public ProcessCardView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.imageCardViewStyle);
	}

	/**
	 * Create a ProcessCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param styleResId
	 *          The resourceId of the theme you want to apply.
	 */
	public ProcessCardView(Context context, int styleResId) {
		super(new ContextThemeWrapper(context, styleResId), null, 0);
		initProgressCardView(styleResId);
	}

	/**
	 * Create a ProcessCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param attrs
	 *          The attributes of the XML tag that is inflating the view.
	 * @param defStyleAttr
	 *          An attribute in the current theme that contains a reference to a style resource that supplies default
	 *          values for the view. Can be 0 to not look for defaults.
	 */
	public ProcessCardView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(getStyledContext(context, attrs, defStyleAttr), attrs, defStyleAttr);
		initProgressCardView(getIconCardViewStyle(context, attrs, defStyleAttr));
	}

	@Override
	public boolean hasOverlappingRendering() {
		return false;
	}

	private void initProgressCardView(int styleResId) {
		setFocusable(true);
		setFocusableInTouchMode(true);
		setCardType(CARD_TYPE_MAIN_ONLY);

		Context context = getContext();

		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.card_view_progress, this);
		TypedArray cardAttrs = context.obtainStyledAttributes(styleResId, R.styleable.ProcessCardView);

		int backgroundColor = cardAttrs.getInt(R.styleable.ProcessCardView_process_background_color,
				ContextCompat.getColor(context, R.color.default_background));
		int drawableResource = cardAttrs.getInt(R.styleable.ProcessCardView_process_image, -1);
		int overLayBackgroundColor = cardAttrs.getInt(R.styleable.ProcessCardView_process_overlay_background_color,
				ContextCompat.getColor(context, R.color.default_black_transparent));
		int progressColor = cardAttrs.getInt(R.styleable.ProcessCardView_process_progress_color,
				ContextCompat.getColor(context, R.color.white));
		int overlayDrawableResource = cardAttrs.getInt(R.styleable.ProcessCardView_process_overlay_icon, -1);

		layout = (FrameLayout) findViewById(R.id.card_view_process_container);
		image = (ImageView) findViewById(R.id.card_view_process_image);
		overlayContainer = (LinearLayout) findViewById(R.id.card_view_process_overlay);
		progressBar = (ProgressBar) findViewById(R.id.card_view_process_overlay_progress);
		overlayIcon = (ImageView) findViewById(R.id.card_view_process_overlay_icon);

		setCardBackgroundColor(backgroundColor);
		if (drawableResource != -1) {
			setImage(drawableResource);
		}
		setOverlayBackgroundColor(overLayBackgroundColor);
		setProgressColor(progressColor);
		if (overlayDrawableResource != -1) {
			setOverlayIcon(overlayDrawableResource);
		}

		cardAttrs.recycle();
	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	public void setLayoutDimensions(int width, int height) {
		ViewGroup.LayoutParams lp = layout.getLayoutParams();
		lp.width = width;
		lp.height = height;
		layout.setLayoutParams(lp);
	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	public void setImageDimensions(int width, int height) {
		ViewGroup.LayoutParams lp = image.getLayoutParams();
		lp.width = width;
		lp.height = height;
		image.setLayoutParams(lp);
	}

	/**
	 *
	 * @param drawable
	 */
	public void setImage(Drawable drawable) {
		image.setImageDrawable(drawable);
	}

	/**
	 *
	 * @param drawable
	 */
	public void setImage(@DrawableRes int drawable) {
		image.setImageResource(drawable);
	}

	/**
	 *
	 * @param color
	 */
	public void setCardBackgroundColor(@ColorInt int color) {
		setBackgroundColor(color);
	}

	/**
	 *
	 * @param color
	 */
	public void setOverlayBackgroundColor(@ColorInt int color) {
		overlayContainer.setBackgroundColor(color);
	}

	/**
	 * @param color
	 */
	public void setProgressColor(@ColorInt int color) {
		progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	public void setOverLayIconDimensions(int width, int height) {
		ViewGroup.LayoutParams lp = overlayIcon.getLayoutParams();
		lp.width = width;
		lp.height = height;
		overlayIcon.setLayoutParams(lp);
	}

	/**
	 *
	 * @param drawable
	 */
	public void setOverlayIcon(Drawable drawable) {
		overlayIcon.setImageDrawable(drawable);
	}

	/**
	 *
	 * @param drawable
	 */
	public void setOverlayIcon(@DrawableRes int drawable) {
		overlayIcon.setImageResource(drawable);
	}

	/**
	 *
	 * @return
	 */
	public FrameLayout getLayout() {
		return layout;
	}

	/**
	 *
	 * @return
	 */
	public ImageView getImage() {
		return image;
	}

	/**
	 *
	 * @return
	 */
	public LinearLayout getOverlayContainer() {
		return overlayContainer;
	}

	/**
	 *
	 * @return
	 */
	public ProgressBar getOverlayProgress() {
		return progressBar;
	}

	/**
	 *
	 * @return
	 */
	public ImageView getOverlayIcon() {
		return overlayIcon;
	}

	private static Context getStyledContext(Context context, AttributeSet attrs, int defStyleAttr) {
		int style = getIconCardViewStyle(context, attrs, defStyleAttr);
		return new ContextThemeWrapper(context, style);
	}

	private static int getIconCardViewStyle(Context context, AttributeSet attrs, int defStyleAttr) {
		int style = attrs == null ? 0 : attrs.getStyleAttribute();

		if (style == 0) {
			TypedArray styledAttrs = context.obtainStyledAttributes(R.styleable.ProcessCardView);
			style = styledAttrs.getResourceId(R.styleable.ProcessCardView_process_theme, 0);
			styledAttrs.recycle();
		}

		return style;
	}
}
