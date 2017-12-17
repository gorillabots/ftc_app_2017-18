package org.firstinspires.ftc.teamcode.Vision;

//Created by Mikko on 2017-11-07

import android.util.Log;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class CryptoVision extends OpenCVPipeline
{
    Mat hsv = null;
    Mat kernel = new Mat(5, 5, CvType.CV_8U, Scalar.all(0));

    public Mat processFrame(Mat rgba, Mat grey)
    {
        Mat rotated = rotateMat(rgba);

        if(hsv == null)
        {
            hsv = new Mat(rotated.width(), rotated.height(), CvType.CV_8U);
        }

        Imgproc.cvtColor(rotated, hsv, Imgproc.COLOR_RGB2HSV);

        //Erode, dilate, and blur to clean up the image
        Mat eroded = new Mat();
        Imgproc.erode(hsv, eroded, kernel);
        Mat dilated = new Mat();
        Imgproc.dilate(eroded, dilated, kernel);
        Mat blurred = new Mat();
        Imgproc.blur(dilated, blurred, new Size(6, 6));

        Mat ranged1 = new Mat(blurred.width(), blurred.height(), blurred.type());
        Core.inRange(blurred, new Scalar(90, 120, 10), new Scalar(130, 250, 150), ranged1);

        Mat masked = new Mat();
        blurred.copyTo(masked, ranged1);

        Mat bgr = new Mat();
        Imgproc.cvtColor(masked, bgr, Imgproc.COLOR_HSV2RGB);

        return bgr;
    }

    Mat mat1 = null;
    Mat mat2 = null;
    Mat mat3 = null;

    public Mat rotateMat(Mat input)
    {
        if(mat1 == null)
        {
            mat1 = new Mat(input.width(), input.height(), CvType.CV_8UC4);
        }

        if(mat2 == null)
        {
            mat2 = new Mat(input.height(), input.width(), CvType.CV_8UC4);
        }

        if(mat3 == null)
        {
            mat3 = new Mat(input.height(), input.width(), CvType.CV_8UC4);
        }

        Core.transpose(input, mat1);
        Imgproc.resize(mat1, mat2, mat2.size(), 0, 0, 0);
        Core.flip(mat2, mat3, 1);
        return mat3;
    }
}
