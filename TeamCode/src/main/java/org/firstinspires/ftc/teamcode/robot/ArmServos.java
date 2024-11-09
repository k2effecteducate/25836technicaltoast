package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmServos {
    private LinearOpMode opMode;
    private DcMotor armMotor;
    public Servo servo1;
    public Servo servo2;


    private int armTarget = 0;

    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 96;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public ArmServos(LinearOpMode myOpMode) {
        opMode = myOpMode;

    }

    public void init() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");
        armMotor = opMode.hardwareMap.get(DcMotor.class, "armMotor");
        servo1 = opMode.hardwareMap.get(Servo.class, "servo1");
        servo2 = opMode.hardwareMap.get(Servo.class, "servo2");


    }

    public void arm(double speed, long time) {
        armMotor.setPower(speed);
        opMode.sleep(time);
    }

    public void stopMotors() {
        armMotor.setPower(0);
    }

    public void setMotorMode(DcMotor.RunMode mode) {
        armMotor.setMode(mode);
    }

    public void armDistance(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            armTarget = armMotor.getCurrentPosition();
            armMotor.setTargetPosition(armTarget);


            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm(speed, 0);
            while (opMode.opModeIsActive() && armMotor.isBusy()) {


            }
            stopMotors();
            setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void closeServoTurn() {
        servo2.setPosition(.7);
    }
    public void intakeClose(){
        servo1.setPosition(1);
    }

    public void openServoTurn() {
        servo2.scaleRange(0,.01);

    }

    public void intakeOpen() {
        servo1.setPosition(0);
    }
}




