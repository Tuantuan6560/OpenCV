package com.example.picturedeal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class ImageProcessUtil {
    public static Bitmap convert2Gray(Bitmap bitmap){
        Mat src=new Mat();
        Mat dst=new Mat();
        Utils.bitmapToMat(bitmap,src);
        Imgproc.cvtColor(src,dst,Imgproc.COLOR_BGR2GRAY);
        Utils.matToBitmap(dst,bitmap);
        src.release();
        dst.release();
        return null;
    }
}
