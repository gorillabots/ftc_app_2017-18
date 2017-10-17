package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;

/**
 * Created by Owner on 10/6/2017.
 */

public class TestArmExtender implements ArmExtender{

    public TestArmExtender(HardwareMap hardwareMap, Telemetry telemetry) {

    }
    @Override
    public void init(){}
    @Override
    public void extend(int percent) {

    }

    @Override
    public void retract(int percent) {

    }

    @Override
    public void extendDistance(int distance) {

    }

    @Override
    public void retractDistance(int distance) {

    }

    @Override
    public boolean isExtended() {
        return false;
    }


    @Override
    public double getDistance() {
        return 0;
    }

    @Override
    public double getStage(){return -1;}

    public void setStage(){

    }
}
