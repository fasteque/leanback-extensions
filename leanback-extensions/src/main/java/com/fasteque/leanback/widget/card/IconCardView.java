package com.fasteque.leanback.widget.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fasteque.leanback.R;

/**
 * A subclass of {@link BaseCardView} with an {@link ImageView} as its main region.
 */
public class IconCardView extends BaseCardView {

	private RelativeLayout layout;
	private LinearLayout textContainer;
	private ImageView icon;
	private TextView primaryText;
	private TextView secondaryText;

	/**
	 * Create an IconCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 */
	public IconCardView(Context context) {
		this(context, null);
	}

	/**
	 * Create an IconCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param attrs
	 *          The attributes of the XML tag that is inflating the view.
	 */
	public IconCardView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.imageCardViewStyle);
	}

	/**
	 * Create an IconCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param styleResId
	 *          The resourceId of the theme you want to apply.
	 */
	public IconCardView(Context context, int styleResId) {
		super(new ContextThemeWrapper(context, styleResId), null, 0);
		initIconCardView(styleResId);
	}

	/**
	 * Create an IconCardView.
	 *
	 * @param context
	 *          The Context the view is running in, through which it can access the current theme, resources, etc.
	 * @param attrs
	 *          The attributes of the XML tag that is inflating the view.
	 * @param defStyleAttr
	 *          An attribute in the current theme that contains a reference to a style resource that supplies default
	 *          values for the view. Can be 0 to not look for defaults.
	 */
	public IconCardView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(getStyledContext(context, attrs, defStyleAttr), attrs, defStyleAttr);
		initIconCardView(getIconCardViewStyle(context, attrs, defStyleAttr));
	}

	@Override
	public boolean hasOverlappingRendering() {
		return false;
	}

	private void initIconCardView(int styleResId) {
		setFocusable(true);
		setFocusableInTouchMode(true);
		setCardType(CARD_TYPE_MAIN_ONLY);

		Context context = getContext();

		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.card_view_icon, this);
		TypedArray cardAttrs = context.obtainStyledAttributes(styleResId, R.styleable.IconCardView);

		int backgroundColor = cardAttrs.getInt(R.styleable.IconCardView_icon_background_color,
						ContextCompat.getColor(context, R.color.default_background));
		int textBackgroundColor = cardAttrs.getInt(R.styleable.IconCardView_icon_text_background_color,
						ContextCompat.getColor(context, R.color.default_accent));
		int primaryTextColor = cardAttrs.getInt(R.styleable.IconCardView_icon_primary_text_color,
						ContextCompat.getColor(context, R.color.white));
		int secondaryTextColor = cardAttrs.getInt(R.styleable.IconCardView_icon_secondary_text_color,
						ContextCompat.getColor(context, R.color.white));

		int drawableResource = cardAttrs.getInt(R.styleable.IconCardView_icon_image, -1);

		layout = (RelativeLayout) findViewById(R.id.card_view_icon_container);
		textContainer = (LinearLayout) findViewById(R.id.card_view_icon_text_container);
		icon = (ImageView) findViewById(R.id.card_view_icon_image);
		primaryText = (TextView) findViewById(R.id.card_view_icon_primary_text);
		secondaryText = (TextView) findViewById(R.id.card_view_icon_secondary_text);

		setCardBackgroundColor(backgroundColor);
		setDetailBackgroundColor(textBackgroundColor);
		setPrimaryTextColor(primaryTextColor);
		setSecondaryTextColor(secondaryTextColor);
		if (drawableResource != -1) {
			setIcon(drawableResource);
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
	 * @param drawable
	 */
	public void setIcon(Drawable drawable) {
		icon.setImageDrawable(drawable);
	}

	/**
	 *
	 * @param drawable
	 */
	public void setIcon(@DrawableRes int drawable) {
		icon.setImageResource(drawable);
	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	public void setIconDimensions(int width, int height) {
		ViewGroup.LayoutParams lp = icon.getLayoutParams();
		lp.width = width;
		lp.height = height;
		icon.setLayoutParams(lp);
	}

	/**
	 *
	 * @param titleText
	 */
	public void setPrimaryText(CharSequence titleText) {
		primaryText.setText(titleText);
	}

	/**
	 *
	 * @param detailText
	 */
	public void setSecondaryText(CharSequence detailText) {
		secondaryText.setText(detailText);
	}

	/**
	 *
	 * @param color
	 */
	public void setPrimaryTextColor(@ColorInt int color) {
		primaryText.setTextColor(color);
	}

	/**
	 *
	 * @param color
	 */
	public void setSecondaryTextColor(@ColorInt int color) {
		secondaryText.setTextColor(color);
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
	public void setDetailBackgroundColor(@ColorInt int color) {
		textContainer.setBackgroundColor(color);
	}

	/**
	 *
	 * @return
	 */
	public RelativeLayout getLayout() {
		return layout;
	}

	/**
	 *
	 * @return
	 */
	public LinearLayout getTextContainer() {
		return textContainer;
	}

	/**
	 *
	 * @return
	 */
	public ImageView getIcon() {
		return icon;
	}

	/**
	 *
	 * @return
	 */
	public TextView getPrimaryText() {
		return primaryText;
	}

	/**
	 *
	 * @return
	 */
	public TextView getSecondaryText() {
		return secondaryText;
	}

	private static Context getStyledContext(Context context, AttributeSet attrs, int defStyleAttr) {
		int style = getIconCardViewStyle(context, attrs, defStyleAttr);
		return new ContextThemeWrapper(context, style);
	}

	private static int getIconCardViewStyle(Context context, AttributeSet attrs, int defStyleAttr) {
		int style = attrs == null ? 0 : attrs.getStyleAttribute();

		if (style == 0) {
			TypedArray styledAttrs = context.obtainStyledAttributes(R.styleable.IconCardView);
			style = styledAttrs.getResourceId(R.styleable.IconCardView_icon_theme, 0);
			styledAttrs.recycle();
		}

		return style;
	}
}
