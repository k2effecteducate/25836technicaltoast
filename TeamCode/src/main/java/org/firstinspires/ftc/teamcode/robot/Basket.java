package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

public class Basket {
    private LinearOpMode opMode;
    public DcMotor armMotor;
    public Servo servo1;
    public Servo servo2;
    public Servo servo3;
    public DcMotor slideMotor;
    public DigitalChannel slideTouch;
    private int armTarget = 0;
    private int slideTarget = 0;
    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 96;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public Basket(LinearOpMode myOpMode) {
        opMode = myOpMode;

    }

    public void init() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");
        armMotor = opMode.hardwareMap.get(DcMotor.class, "armMotor");
        servo1 = opMode.hardwareMap.get(Servo.class, "servo1");
        servo2 = opMode.hardwareMap.get(Servo.class, "servo2");
        slideMotor = opMode.hardwareMap.get(DcMotor.class, "slideMotor");
        slideTouch = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch");
        servo3 = opMode.hardwareMap.get(Servo.class, "servo3");
        slideTouch.setMode(DigitalChannel.Mode.INPUT);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    public void stopMotors() {
        armMotor.setPower(0);
        slideMotor.setPower(0);
    }


    public void servoBasketDrop() {
        servo3.setPosition(.5);

    }

    public void servoBasketNormal() {
        servo3.setPosition(0);

    }

    public void slide(double speed, long time) {
        slideMotor.setPower(speed);
        opMode.sleep(time);
    }

    public void setSlideMotorMode(DcMotor.RunMode mode) {
        slideMotor.setMode(mode);
    }


    public void closeSlideTouch() {
        setSlideMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (!slideTouch.getState()) {
            opMode.telemetry.addData("closing", slideTouch.getState());
            slideMotor.setPower(0);
            return;
        }
        opMode.telemetry.addData("down", slideTouch.getState());
        slideMotor.setPower(-.5);
    }
        public void closeSlide () {
            slideMotor.setPower(-.7);
            opMode.sleep(1200);
        }

    public void slideTeleOp(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            setSlideMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            slideTarget = slideMotor.getCurrentPosition() + moveCounts;
            slideMotor.setTargetPosition(slideTarget);

            setSlideMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            slide(speed, 0);

        }
    }
    public void slideAuto(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            setSlideMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            slideTarget = slideMotor.getCurrentPosition() + moveCounts;
            slideMotor.setTargetPosition(slideTarget);

            setSlideMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            slide(speed, 0);
            while (opMode.opModeIsActive() && slideMotor.isBusy()) {

            }
        }
    }

    public void startSlide() {
        slideTeleOp(.3, 386);

    }

    public void highBasketSlide() {
        slideAuto(.3, 5000);


    }
}

