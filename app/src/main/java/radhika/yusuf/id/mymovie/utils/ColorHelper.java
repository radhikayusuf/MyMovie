package radhika.yusuf.id.mymovie.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radhika Yusuf on 25/06/17.
 */

public class ColorHelper {

    public static List<Integer> getDominateColor(Bitmap bitmap){
        int redBucket = 0;
        int greenBucket = 0;
        int blueBucket = 0;
        int pixelCount = 0;

        for (int y = 0; y < bitmap.getHeight(); y++)
        {
            for (int x = 0; x < bitmap.getWidth(); x++)
            {
                int c = bitmap.getPixel(x, y);

                pixelCount++;
                redBucket += Color.red(c);
                greenBucket += Color.green(c);
                blueBucket += Color.blue(c);
                // does alpha matter?
            }
        }

        List<Integer> a = new ArrayList<>();
        a.add(redBucket / pixelCount);
        Log.wtf("getDominateColor: ", String.valueOf(redBucket / pixelCount));
        a.add(greenBucket / pixelCount);
        Log.wtf("getDominateColor: ", String.valueOf(greenBucket / pixelCount));
        a.add(blueBucket / pixelCount);
        Log.wtf("getDominateColor: ", String.valueOf(blueBucket / pixelCount));

        return a;

//        Color averageColor = Color.rgb(redBucket / pixelCount, greenBucket / pixelCount, blueBucket / pixelCount);
//
//        java.awt.Color
    }

}
