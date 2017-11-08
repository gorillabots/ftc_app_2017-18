package org.firstinspires.ftc.teamcode.OpenCV;

import android.content.Context;
import android.view.View

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.core.Mat;

//Created by Mikko on 2017-11-07

public abstract class CryptoVision implements CameraBridgeViewBase.CvCameraViewListener2
{
    static
    {
        System.loadLibrary("opencv_java3");
    }

    JavaCameraView cameraView;

    public void init(Context context, ViewDisplay viewDisplay)
    {

    }

    @Override
    public void onCameraViewStarted(int width, int height)
    {

    }
}
