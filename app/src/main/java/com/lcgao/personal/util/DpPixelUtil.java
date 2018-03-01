package com.lcgao.personal.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by lcgao on 2018/3/1.
 */

public class DpPixelUtil {
    public static float dpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
}
