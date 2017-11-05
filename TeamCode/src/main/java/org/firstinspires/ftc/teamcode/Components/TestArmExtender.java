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
    double INCREMENT = 0.01;
    double MAX = 1.0;
    double MIN = -1.0;

    DcMotor extender;
    double power = 0;
    double POSITION_TWO = 200;

    public TestArmExtender(HardwareMap hardwareMap, Telemetry telemetry) {

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        extender = hardwareMap.dcMotor.get("extender");

    }
    @Override
    public void init(){}
    @Override
    public void extend() {
        power += INCREMENT;
        if (power >= MAX) {
            power = MAX;
        }
    }
    @Override
    public void retract() {
        power -= INCREMENT;
        if (power <= MIN) {
            power = MIN;
        }
    }
    @Override
    public void extendDistance(int distance) {
        if (extender.getCurrentPosition() <= POSITION_TWO) {

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
