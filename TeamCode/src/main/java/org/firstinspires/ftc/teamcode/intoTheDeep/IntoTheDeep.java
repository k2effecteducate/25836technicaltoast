package org.firstinspires.ftc.teamcode.intoTheDeep;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.PIDController;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;


public class IntoTheDeep {
    private LinearOpMode opMode;
    public DcMotorEx armMotor;
    public Servo servo1;
    public CRServo servo2;
    public Servo servo3;
    // public CRServo servo4;
    public DcMotorEx slideMotor1;
    //  public DcMotor slideMotor2;
    public DigitalChannel slideTouch1;
    //    public DigitalChannel slideTouch2;
    private PIDController PIDArm;
    private int armTarget = 0;
    private int slideTarget = 0;
    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 96;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    private PIDController PIDSlide1;
    // private PIDController PIDSlide2;
    public Motors motors;
    public Movement movement;
    public Servos servos;
    public Sensors sensors;
    public static int targetSlideUpPosition = -7480;

    public IntoTheDeep(LinearOpMode myOpMode) {
        opMode = myOpMode;

    }


    public void init() {
        armMotor = opMode.hardwareMap.get(DcMotorEx.class, "armMotor");
        servo1 = opMode.hardwareMap.get(Servo.class, "servo1");
        servo2 = opMode.hardwareMap.get(CRServo.class, "servo2");
        slideMotor1 = opMode.hardwareMap.get(DcMotorEx.class, "slideMotor1");
        slideTouch1 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch1");
        servo3 = opMode.hardwareMap.get(Servo.class, "servo3");
        slideTouch1.setMode(DigitalChannel.Mode.INPUT);
        slideMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        servo1.setDirection(Servo.Direction.REVERSE);
        servo3.setDirection(Servo.Direction.REVERSE);
        PIDArm = new PIDController(.01, 0, .03);
        PIDSlide1 = new PIDController(.02, 0, 0);
        motors = new Motors(opMode);
        movement = new Movement(opMode);
        servos = new Servos(opMode);
        sensors = new Sensors(opMode);
        servos.init();
        motors.init();
        movement.init();
        sensors.init();


    }

    public void sensorCollect() {

        if (sensors.isObjectDetected()) {
            servo2.setPower(0);
            return;
        }
        servo2.setPower(1);
    }

    public void straitArm() {

        int targetPosition = 50;

        double command = PIDArm.update(targetPosition, armMotor.getCurrentPosition());
        //   double command1 = PIDSlide2.update(targetPosition, slideMotor2.getCurrentPosition());
        armMotor.setPower(command);
    }


    public void armUp() {
        int targetPosition = 3000;

        double command = PIDArm.update(targetPosition, armMotor.getCurrentPosition());

        armMotor.setPower(command);
    }

    public void slideInTouch() {
        if (!slideTouch1.getState()) {
            opMode.telemetry.addData("closing1", slideTouch1.getState());
            motors.stopMotors();
            return;
        }
        opMode.telemetry.addData("down1", slideTouch1.getState());

        slideMotor1.setPower(.7);


    }

    public void slidePIDOut() {
        //make it go longer an
        int targetPosition = -2800;
        opMode.telemetry.addData("PID", slideMotor1.getCurrentPosition());

        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());

        slideMotor1.setPower(command);

    }

    public boolean isSlideUp() {
        return slideMotor1.getCurrentPosition() < targetSlideUpPosition;
    }

    public boolean isSlideDown() {
        return !slideTouch1.getState();

    }

    public void slidePIDUp() {

        double command = PIDSlide1.update(targetSlideUpPosition, slideMotor1.getCurrentPosition());
        opMode.telemetry.addData("slide power", command);
        slideMotor1.setPower(command);
    }

    public void slidePIDUpAuto() {
        int targetPosition = -5500;
        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        slideMotor1.setPower(command);
    }

    public void servo2SpinClockwise() {
        servo2.setPower(.65);
    }

    public void servo2SpinCounterClockwise() {
        servo2.setPower(-.65);

    }

    public void servo2StopSpinning() {
        servo2.setPower(0);
    }

    public void intakeClose() {
        servo1.setPosition(.7);
        servo3.setPosition(-.7);
    }

    public void intakeCollect() {
        servo1.setDirection(Servo.Direction.REVERSE);
        servo3.setDirection(Servo.Direction.REVERSE);
        opMode.telemetry.addData("servo", servo1.getPosition());
        servo3.setPosition(.8);
        opMode.telemetry.update();
    }

    public void intakeOpen() {
        servo1.setPosition(.4);
        servo3.setPosition(-.4);
    }

    public void intakeDump() {
        servo1.setPosition(.6);
        servo3.setPosition(.6);
    }

    public void servo2SpinClockwiseSlow() {
        servo2.setPower(.20);
    }

    public void servo2SpinCounterClockwiseSlow() {
        servo2.setPower(-.20);
    }

    public void armShortAuto() {
        int targetPosition = -3500;
        opMode.telemetry.addData("PID", slideMotor1.getCurrentPosition());
        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        slideMotor1.setPower(command);
    }

    public void teleOpIntakeControls() {
        servo1.setPosition(-opMode.gamepad2.left_stick_y);
    }

    public void SlidePID() {
        int targetPosition = 3200;
        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        opMode.telemetry.addData("command", command);
        slideMotor1.setPower(command);
    }

//    public void slideTeleOp(double speed, double distance) {
//        if (opMode.opModeIsActive()) {
//            motors.setSlideMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            int moveCounts = (int) (distance * COUNTS_PER_INCH);
//            slideTarget = slideMotor1.getCurrentPosition() + moveCounts;
//            slideMotor1.setTargetPosition(slideTarget);
//            motors.setSlideMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
//            motors.rightSlide(speed, 0);
//        }
//    }

//    public void slideAuto(double speed, double distance) {
//        if (opMode.opModeIsActive()) {
//            motors.setSlideMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            int moveCounts = (int) (distance * COUNTS_PER_INCH);
//            slideTarget = slideMotor1.getCurrentPosition() + moveCounts;
//            slideMotor1.setTargetPosition(slideTarget);
//
//            motors.setSlideMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
//            motors.rightSlide(speed, 0);
//            while (opMode.opModeIsActive() && slideMotor1.isBusy()) {
//
//            }
//        }
//    }

    public void startSlidePID() {
        int targetPosition = 60;

        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        opMode.telemetry.addData("command", command);
        opMode.telemetry.addData("currentPosition", slideMotor1.getCurrentPosition());

        slideMotor1.setPower(command);
        motors.setSlideMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }
}








