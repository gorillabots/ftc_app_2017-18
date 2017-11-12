package org.firstinspires.ftc.teamcode.OpenCV;

//Created by Mikko on 2017-11-07

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class CryptoVision extends OpenCVPipeline
{
    boolean firstRun = true;

    Mat transposed;
    Mat rotated;

    public Mat processFrame(Mat rgba, Mat grey)
    {
        if(firstRun)
        {
            transposed = new Mat(rgba.width(), rgba.width(), CvType.CV_8UC4);
            rotated = new Mat(rgba.width(), rgba.height(), CvType.CV_8UC4);

            firstRun = false;
        }

        //return rgba;
        Core.transpose(rgba, transposed);
        Imgproc.resize(transposed, rgba, rgba.size(), 0, 0, 0);
        Core.flip(rgba, rotated, 1);
        return rotated;
    }
}
