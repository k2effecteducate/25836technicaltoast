package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

public class Motors {
    private LinearOpMode opMode;
    public DcMotorEx armMotor;
    public Servo servo1;
    public CRServo servo2;
    public DcMotor slideMotor1;
    public DigitalChannel slideTouch1;
    private PIDController PIDArm;

    private int armTarget = 0;
    private int slideTarget = 0;

    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 96;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public Motors(LinearOpMode myOpMode) {
        opMode = myOpMode;

    }

    public void init() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");

        armMotor = opMode.hardwareMap.get(DcMotorEx.class, "armMotor");
        servo1 = opMode.hardwareMap.get(Servo.class, "servo1");
        servo2 = opMode.hardwareMap.get(CRServo.class, "servo2");
        slideMotor1 = opMode.hardwareMap.get(DcMotor.class, "slideMotor1");
        //  slideMotor2 = opMode.hardwareMap.get(DcMotor.class, "slideMotor2");
        slideTouch1 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch1");
        //slideTouch2 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch1");
        //  servo3 = opMode.hardwareMap.get(Servo.class, "servo3");
        slideTouch1.setMode(DigitalChannel.Mode.INPUT);
        //slideTouch2.setMode(DigitalChannel.Mode.INPUT);
        slideMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // slideMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        PIDArm = new PIDController(.01, 0, .03);


    }

    public void arm(double speed, long time) {
        armMotor.setPower(speed);
        opMode.sleep(time);
    }

    public void stopMotors() {
        armMotor.setPower(0);
        slideMotor1.setPower(0);
        //  slideMotor2.setPower(0);
    }

    public void setArmMotorMode(DcMotor.RunMode mode) {
        armMotor.setMode(mode);
    }

    public void setArmPosition(double speed, double distance, long holdArmTime) {
        if (opMode.opModeIsActive()) {
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            armTarget = armMotor.getCurrentPosition() + moveCounts;
            armMotor.setTargetPosition(armTarget);


            setArmMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm(speed, 0);
            while (opMode.opModeIsActive() && armMotor.isBusy()) {


            }
            if (holdArmTime > 0) {
                opMode.sleep(holdArmTime);
                stopMotors();
                setArmMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }
        }
    }

    public void setSlidePosition(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            setSlideMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            slideTarget = slideMotor1.getCurrentPosition() + moveCounts;
            slideMotor1.setTargetPosition(slideTarget);


            setArmMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightSlide(speed, 0);


        }
    }

    public void rightSlide(double speed, long time) {
        slideMotor1.setPower(speed);
        opMode.sleep(time);
    }

    public void leftslide(double speed, long time) {
        // slideMotor2.setPower(speed);
        opMode.sleep(time);
    }

    public void armTeleOp(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            setArmMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            armTarget = armMotor.getCurrentPosition() + moveCounts;
            armMotor.setTargetPosition(armTarget);
            opMode.telemetry.addData("armMotorTarget", armTarget);


            setArmMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm(speed, 0);
        }
    }

    public void closeSlideTouch() {
        if (!slideTouch1.getState()) {
            opMode.telemetry.addData("closing", slideTouch1.getState());
            slideMotor1.setPower(0);
            stopMotors();
            return;
        }
        opMode.telemetry.addData("down", slideTouch1.getState());

        slideMotor1.setPower(.7);
    }

    public void setSlideMotorMode(DcMotor.RunMode mode) {
        slideMotor1.setMode(mode);
        //  slideMotor2.setMode(mode);
    }

}







