package org.firstinspires.ftc.teamcode.OpenCV;

//Created by Derek on 2017-06-26
//Copied by Mikko on 2017-11-10
//Provides methods to display and remove a View from the screen.

import android.content.Context;
import android.view.View;

public interface ViewDisplay
{
    void setCurrentView(Context context, View view);
    void removeCurrentView(Context context);
}