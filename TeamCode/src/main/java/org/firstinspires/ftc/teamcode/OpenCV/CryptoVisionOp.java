package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.opencv.android.OpenCVLoader;

//Created by Mikko on 2017-11-10

@Autonomous(name="CryptoVisionOp", group="Tests")
public class CryptoVisionOp extends OpMode
{
    CryptoVision cv;

    @Override
    public void init()
    {
        OpenCVLoader.initDebug();
        //System.loadLibrary("opencv-java3");
        cv = new CryptoVision();
        cv.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        cv.enable();
    }

    @Override
    public void loop()
    {

    }

    @Override
    public void stop()
    {
        cv.disable();
    }
}
