package com.fasteque.leanback.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
public class ScreenUtils {

	public static final int UNKNOWN = 0;
	public static final int HD = 10;
	public static final int FHD = 20;
	public static final int UHD_4K = 30;
	public static final int UHD_PLUS_5K = 40;
	public static final int UHD_8K = 50;

	/**
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int convertDpToPixel(@NonNull final Context context, final int dp) {
		float density = context.getResources().getDisplayMetrics().density;
		return Math.round((float) dp * density);
	}

	/**
	 * @param context
	 * @return
	 */
	public static Point getDisplaySize(@NonNull final Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size;
	}

	/**
	 * @param targetWindow
	 * @param modeId
	 */
	@RequiresApi(api = Build.VERSION_CODES.M)
	public static void setPreferredDisplayMode(@NonNull final Window targetWindow, final int modeId) {
		WindowManager.LayoutParams params = targetWindow.getAttributes();
		params.preferredDisplayModeId = modeId;
		targetWindow.setAttributes(params);
	}

	/**
	 * @param display
	 * @return
	 */
	public static @ScreenResolution
	int getScreenResolution(@NonNull final Display display) {
		Point displaySize = new Point();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			getDisplaySizeV23(display, displaySize);
		} else {
			getDisplaySizeV17(display, displaySize);
		}

		if (displaySize.y >= 4320) {
			return UHD_8K;
		} else if (displaySize.y >= 2880) {
			return UHD_PLUS_5K;
		} else if (displaySize.y >= 2160) {
			return UHD_4K;
		} else if (displaySize.y >= 1080) {
			return FHD;
		} else if (displaySize.y >= 720) {
			return HD;
		} else {
			return UNKNOWN;
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	private static void getDisplaySizeV23(@NonNull final Display display, @NonNull final Point outSize) {
		/*
			Still not worth enabling 4K, but that's where video modes can be checked.
		 */
		Display.Mode[] modes = display.getSupportedModes();
		if (modes != null && modes.length > 0) {
			Display.Mode mode = modes[0];
			outSize.x = mode.getPhysicalWidth();
			outSize.y = mode.getPhysicalHeight();
		} else {
			display.getRealSize(outSize);
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	private static void getDisplaySizeV17(@NonNull final Display display, @NonNull final Point outSize) {
		display.getRealSize(outSize);
	}

	/**
	 *
	 */
	@IntDef({UNKNOWN, HD, FHD, UHD_4K, UHD_PLUS_5K, UHD_8K})
	@Retention(RetentionPolicy.SOURCE)
	public @interface ScreenResolution {
	}
}
