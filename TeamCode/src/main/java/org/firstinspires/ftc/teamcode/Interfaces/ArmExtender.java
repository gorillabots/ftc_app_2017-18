package org.firstinspires.ftc.teamcode.Interfaces;

/**
 * Created by Owner on 10/6/2017.
 */

public interface ArmExtender {

    public void extend(int percent);
    public void retract(int percent);
    public void extendDistance(int distance);
    public void retractDistance(int distance);
    public boolean isExtended();



}
