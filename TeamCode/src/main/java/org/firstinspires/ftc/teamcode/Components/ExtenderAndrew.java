package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;

/**
 * Created by Owner on 11/10/2017.
 */

public class ExtenderAndrew {
    HardwareMap hardwareMap;
    Telemetry telemetry;
    double INCREMENT = 0.01;
    double MAX = 1.0;
    double MIN = -1.0;

    DcMotor extender1;
    double power = 0;
    double POSITION_TWO = 200;


    public ExtenderAndrew(HardwareMap hardwareMap, Telemetry telemetry) {

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        extender1 = hardwareMap.dcMotor.get("extend");

        extender1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    
    public void init() {

    }

    /*
    
    public void extend(double power) {
        power += INCREMENT;
        if (power >= MAX) {
            power = MAX;
        }
        extender1.setPower(power);
    }

    
    public void retract() {
        power -= INCREMENT;
        if (power <= MIN) {
            power = MIN;
        }
        extender1.setPower(power*-1);
    }
    */
    
    public void extend(double power) {
        extender1.setPower(power*.75);
    }

    
    public void stop() {
        extender1.setPower(0);
    }

    
    public void retract() {

    }

    
    public void extendDistance(int distance) {

    }

    
    public void retractDistance(int distance) {

    }

    
    public boolean isExtended() {
        return false;
    }

    
    public double getDistance() {
        return 0;
    }

    
    public double getStage() {
        return 0;
    }

    
    public void setStage() {

    }
}
