package com.example.picturedeal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String CV_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.process_btn);
        Button getnew=findViewById(R.id.get_new);
        getnew.setOnClickListener(this);
        button.setOnClickListener(this);
        iniLoadOpenCV();
    }
    private void iniLoadOpenCV(){
        boolean success= OpenCVLoader.initDebug();
        if (success){
            Log.i(CV_TAG,"OpenCV加载成功....");
        }else {
            Toast.makeText(this.getApplicationContext(),"OpenCV没有成功加载...",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        ImageView iv=findViewById(R.id.show_img);
        Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(),R.drawable.lena);
        switch (view.getId()){
            case R.id.process_btn:
                ImageProcessUtil.convert2Gray(bitmap);
                break;
            case R.id.get_new:
                getnew(bitmap);
                break;
                default:
                    break;
        }
        iv.setImageBitmap(bitmap);
    }
    public static Bitmap getnew(Bitmap bitmap){
        Log.d(CV_TAG,"获取子图");
        Rect roi=new Rect(1000,1000,200,100);//获取一个矩形
        Bitmap roimap=Bitmap.createBitmap(roi.width,roi.height, Bitmap.Config.ARGB_8888);
        Mat src=new Mat();
        Utils.bitmapToMat(bitmap,src);
        Mat roiMat=src.submat(roi);//创建一个和roi一样的mat对象
        Mat roiDst=new Mat();
        Imgproc.cvtColor(roiMat,roiDst,Imgproc.COLOR_BGR2GRAY);
        Utils.matToBitmap(roiDst,roimap);
        roiDst.release();
        roiMat.release();
        src.release();
        return roimap;
    }
}
