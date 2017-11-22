package org.firstinspires.ftc.teamcode.Interfaces;

/**
 * Created by Owner on 10/6/2017.
 */

public interface ArmExtender {

    public void init();
    public void extend(double power);
    public void stop();
    public void retract();
    public void extendDistance(int distance);
    public void retractDistance(int distance);
    public boolean isExtended();
    public double getDistance();
    public double getStage();
    public void setStage();



}
