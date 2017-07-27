package radhika.yusuf.id.mymovie.utils;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Radhika Yusuf on 26/06/17.
 */

public class LandscapeImageView extends android.support.v7.widget.AppCompatImageView{
    public LandscapeImageView(Context context) {
        super(context);
    }

    public LandscapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LandscapeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = (MeasureSpec.getSize(widthMeasureSpec));
        setMeasuredDimension(width/3, width/2);
    }
}
