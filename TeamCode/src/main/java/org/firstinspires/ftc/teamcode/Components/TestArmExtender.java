package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;

/**
 * Created by Owner on 10/6/2017.
 */

public class TestArmExtender implements ArmExtender{
    HardwareMap hardwareMap;
    Telemetry telemetry;

    DcMotor extender;
    int POSITION_TWO = 200;
    int POSITION_THREE = 300;

    public TestArmExtender(HardwareMap hardwareMap, Telemetry telemetry) {

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        extender = hardwareMap.dcMotor.get("extender");

    }
    @Override
    public void init(){}
    @Override
    public void extend() {
        extender.setPower(.95);
    }

    @Override
    public void retract() {
        extender.setPower(.95);
    }

    @Override
    public void extendDistance(int distance) {
        if (extender.getCurrentPosition() <= POSITION_TWO) {
            extender
        }


    }

    @Override
    public void retractDistance(int distance) {
        if (extender.getCurrentPosition() >= POSITION_TWO) {

        }

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
