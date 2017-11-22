package org.firstinspires.ftc.teamcode.Components;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Created by Chris on 11/8/2016.
 */

public class ColorHelper {

    //convert to OF for the HSV values
    static float hsvValues[] = {0F, 0F, 0F};
    static float hsvValuesFloor[] = {0F, 0F, 0F};



    public static String getFloorColor(ColorSensor colorSensor)
    {

        String currentcolor = "not white";

        if (isFloorWhite(colorSensor))
        {
        // white line?
            currentcolor = "white";
        }
        else {
         // Not White?
            currentcolor = "not white";
        }

        return currentcolor;
    }

    public static void printColorRGB(Telemetry telemetry, ColorSensor color)
    {
        // show the values for RGB
        telemetry.addData("R", color.red());
        telemetry.addData("G", color.green());
        telemetry.addData("B", color.blue());
    }

    public static void printColorHSV(Telemetry telemetry, ColorSensor color)
    {
        Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, hsvValuesFloor);

        // show the hsv values
        telemetry.addData("H", hsvValuesFloor[0]);
        telemetry.addData("S", hsvValuesFloor[1]);
        telemetry.addData("V", hsvValuesFloor[2]);

    }

    /**
     * this checks if the floor color is white
     * @param color Bottom colorsensor
     * @return true if floor is white
     */
    public static boolean isFloorWhite(ColorSensor color)
    {
        // convert the RGB values to HSV values
        Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, hsvValuesFloor);

        return hsvValuesFloor[2] > .8;
    }

    public static boolean isFloorWhiteTest(ColorSensor color)
    {
        return (color.red() > 10 && color.green() > 10 && color.blue() > 10);
    }



    public static float getFloorValue()
    {
        return hsvValuesFloor[2];
    }

    public static boolean isLineRed(ColorSensor color){
        Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, hsvValuesFloor);
        return (hsvValuesFloor[0] < 5)&&(hsvValuesFloor[0]>3);
        //&&hsvValuesFloor[1]>.9 && hsvValuesFloor[1]<1.1 && hsvValuesFloor[2]>.9 && hsvValuesFloor[2] <.98
    }

    public static boolean isLineBlue(ColorSensor color){
        Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, hsvValuesFloor);
        return  (hsvValuesFloor[0] < 226)&&(hsvValuesFloor[0]>224);
        //&&hsvValuesFloor[1]>.9 && hsvValuesFloor[1]<1.1 && hsvValuesFloor[2]>.61 && hsvValuesFloor[2] <.67
    }

}