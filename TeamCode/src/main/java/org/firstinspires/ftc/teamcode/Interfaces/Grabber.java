package org.firstinspires.ftc.teamcode.Interfaces;

/**
 * Created by Owner on 10/6/2017.
 */

public interface Grabber {
    public void init();
    public void open1();
    public void open2();
    public void close1();
    public void close2();
    public void rotate(double degrees);
    public boolean isHolding();
    public void rotateOne(double power);
    public void rotateTwo (double power);

}
