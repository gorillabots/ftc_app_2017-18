package org.firstinspires.ftc.teamcode.Interfaces;

/**
 * Created by Owner on 10/6/2017.
 */

public interface Grabber {
    public void open(int degrees, float seconds);
    public void close(int degrees, float seconds);
    public boolean isHolding();

    public void rotate(int degrees, float seconds);

}
