package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Interfaces.ArmExtender;

/**
 * Created by Owner on 10/6/2017.
 */

public class ExtenderJack{
    HardwareMap hardwareMap;
    Telemetry telemetry;
    double INCREMENT = 0.01;
    double MAX = 1.0;
    double MIN = -1.0;

    DcMotor extender;
    double power = 0;
    double POSITION_TWO = 200;

    public ExtenderJack(HardwareMap hardwareMap, Telemetry telemetry) {

        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        extender = hardwareMap.dcMotor.get("extender");
        extender.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    
    public void init(){

    }

    public void extendd() {
        power += INCREMENT;
        if (power >= MAX) {
            power = MAX;
        }
        extender.setPower(power);
    }
    
    public void retract() {
        power -= INCREMENT;
        if (power <= MIN) {
            power = MIN;
        }
        extender.setPower(power);
    }
    
    public void extendDistance(int distance) {
        if (extender.getCurrentPosition() <= POSITION_TWO) {

        }


    }

    
    public void retractDistance(int distance) {
        if (extender.getCurrentPosition() >= POSITION_TWO) {

        }

    }

    
    public boolean isExtended() {
        return false;
    }


    
    public double getDistance() {
        return 0;
    }

    
    public double getStage(){return -1;}

    public void setStage(){

    }

    
    public void stop(){}
    
    public void extend(double murica){
        extender.setPower(murica);
    }
}
