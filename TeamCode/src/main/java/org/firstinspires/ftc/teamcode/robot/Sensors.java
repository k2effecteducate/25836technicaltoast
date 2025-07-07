package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;


public class Sensors {
    private LinearOpMode opMode;
    // public DigitalChannel slideTouch1;
    //  public DigitalChannel slideTouch2;
    //   public IMU pinpoint;
    //  public IMU yEncoder;
    //public ColorSensor colorSensor;
    public AnalogInput distanceSensor;


    public Sensors(LinearOpMode myOpMode) {
        opMode = myOpMode;
    }

    public void init() {
        //  pinpoint = opMode.hardwareMap.get(IMU.class, "imu");
        //  slideTouch1 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch1");
        // slideTouch1.setMode(DigitalChannel.Mode.INPUT);
        //  slideTouch2 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch2");
        //  slideTouch2.setMode(DigitalChannel.Mode.INPUT);
        //  colorSensor = opMode.hardwareMap.get(ColorSensor.class,"color");
        distanceSensor = opMode.hardwareMap.get(AnalogInput.class, "distance");
        //   distanceSensor.setMode(DigitalChannel.Mode.INPUT);


    }

    public boolean isObjectDetected() {

        return distanceSensor.getVoltage() < 0.18;
    }
}



