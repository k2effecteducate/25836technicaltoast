package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Motors {
    private LinearOpMode opMode;
    //     public DcMotorEx motor1;
//    public Servo servo1;
//    public CRServo servo2;
//    public DcMotor slideMotor1;
//    public DigitalChannel slideTouch1;
    private PIDController PIDmotor1;

    private int motor1Target = 0;
    private int motor2Target = 0;
    private DcMotorEx motor1;
    private DcMotorEx motor2;
    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 96;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public Motors(LinearOpMode myOpMode) {
        opMode = myOpMode;

    }

    public void init() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");

        motor1 = opMode.hardwareMap.get(DcMotorEx.class, "motor1");
        motor2 = opMode.hardwareMap.get(DcMotorEx.class, "motor2");
//
//        servo1 = opMode.hardwareMap.get(Servo.class, "servo1");
//        servo2 = opMode.hardwareMap.get(CRServo.class, "servo2");
//        slideMotor1 = opMode.hardwareMap.get(DcMotor.class, "slideMotor1");
//        //  slideMotor2 = opMode.hardwareMap.get(DcMotor.class, "slideMotor2");
//        slideTouch1 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch1");
//        //slideTouch2 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch1");
//        //  servo3 = opMode.hardwareMap.get(Servo.class, "servo3");
//        slideTouch1.setMode(DigitalChannel.Mode.INPUT);
//        //slideTouch2.setMode(DigitalChannel.Mode.INPUT);
//        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        // slideMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        PIDmotor1 = new PIDController(.01, 0, .03);


    }

    public void motor1(double speed, long time) {
        motor1.setPower(speed);
        opMode.sleep(time);
    }

    public void stopMotors() {
        motor2.setPower(0);

        //  slideMotor2.setPower(0);
    }

    public void setMotor1Mode(DcMotor.RunMode mode) {
        motor1.setMode(mode);
    }

    public void setMotor1Position(double speed, double distance, long holdArmTime) {
        if (opMode.opModeIsActive()) {
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            motor1Target = motor1.getCurrentPosition() + moveCounts;
            motor1.setTargetPosition(motor1Target);


            setMotor1Mode(DcMotor.RunMode.RUN_TO_POSITION);
            motor1(speed, 0);
            while (opMode.opModeIsActive() && motor1.isBusy()) {


            }
            if (holdArmTime > 0) {
                opMode.sleep(holdArmTime);
                stopMotors();
                setMotor1Mode(DcMotor.RunMode.RUN_USING_ENCODER);

            }
        }
    }

//    public void setSlidePosition(double speed, double distance) {
//        if (opMode.opModeIsActive()) {
//            setMotorMode2(DcMotor.RunMode.RUN_USING_ENCODER);
//            int moveCounts = (int) (distance * COUNTS_PER_INCH);
//            motor2Target = motor2.getCurrentPosition() + moveCounts;
//            motor2.setTargetPosition(motor2Target);
//
//
//            setMotor1Mode(DcMotor.RunMode.RUN_TO_POSITION);
//            rightSlide(speed, 0);
//
//
//        }
//    }

//    public void rightSlide(double speed, long time) {
//        motor2.setPower(speed);
//        opMode.sleep(time);
//    }

//    public void leftslide(double speed, long time) {
//        // slideMotor2.setPower(speed);
//        opMode.sleep(time);
//    }
//
//    public void armTeleOp(double speed, double distance) {
//        if (opMode.opModeIsActive()) {
//            setMotor1Mode(DcMotor.RunMode.RUN_USING_ENCODER);
//            int moveCounts = (int) (distance * COUNTS_PER_INCH);
//            motor1Target = motor1.getCurrentPosition() + moveCounts;
//            motor1.setTargetPosition(motor1Target);
//            opMode.telemetry.addData("motor1Target", motor1Target);
//
//
//            setMotor1Mode(DcMotor.RunMode.RUN_TO_POSITION);
//            motor1(speed, 0);
//        }
//    }

//    public void closeSlideTouch() {
//        if (!slideTouch1.getState()) {
//            opMode.telemetry.addData("closing", slideTouch1.getState());
//            motor2.setPower(0);
//            stopMotors();
//            return;
//        }
//        opMode.telemetry.addData("down", slideTouch1.getState());
//
//        motor2.setPower(.7);
//    }

    public void setMotorMode2(DcMotor.RunMode mode) {
        motor2.setMode(mode);
        //  slideMotor2.setMode(mode);
    }

}







