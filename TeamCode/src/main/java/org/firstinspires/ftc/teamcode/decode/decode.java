package org.firstinspires.ftc.teamcode.decode;


import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Odometry;
import org.firstinspires.ftc.teamcode.robot.PIDController;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;

import java.util.Collection;

@Configurable
public class decode {

    private LinearOpMode opMode;
    public DcMotorEx motor1;
    public DcMotorEx motor2;
    public CRServo servo1;
    public CRServo servo2;
    public CRServo servo3;
    public CRServo servo4;
    //    public DcMotorEx slideMotor1;
//    public DigitalChannel slideTouch1;
//    private PIDController PIDArm;
//    private int armTarget = 0;
//    private int slideTarget = 0;
//    static final double COUNTS_PER_MOTOR_REV = 537.7;
//    static final double DRIVE_GEAR_REDUCTION = 1.0;
//    static final double WHEEL_DIAMETER_INCHES = 96;
//    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
//    private PIDController PIDSlide1;
//    // private PIDController PIDSlide2;
//    //  public Motors motors;
    public Movement movement;
    public Servos servos;
    public Odometry odometry;
    private Follower follower;
    public Sensors sensors;
    //   public static int targetSlideUpPosition = -7480;
    public static double flyWheelOne = 40;
    public static double flyWheelTwo = 35;
    private ElapsedTime runtime = new ElapsedTime();
    public static int counterDistance = 0;
    public static double lastCountOfVoltage = .05;
    public static double voltageChangedThreshold = 0.03;

    public decode(LinearOpMode myOpMode) {
        opMode = myOpMode;

    }


    public void init() {
        servo1 = opMode.hardwareMap.get(CRServo.class, "servo1");
        servo2 = opMode.hardwareMap.get(CRServo.class, "servo2");
        servo3 = opMode.hardwareMap.get(CRServo.class, "servo3");
        servo4 = opMode.hardwareMap.get(CRServo.class, "servo4");
        motor1 = opMode.hardwareMap.get(DcMotorEx.class, "motor1");
        motor2 = opMode.hardwareMap.get(DcMotorEx.class, "motor2");
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        follower = Constants.createFollower(opMode.hardwareMap);


        movement = new Movement(opMode);
        odometry = new Odometry(opMode);
        servos = new Servos(opMode);
        odometry.init();
        sensors = new Sensors(opMode);
        servos.init();
        //  motors.init();
        movement.init();
        sensors.init();


    }

    public void collection() {
        servo1.setPower(-1);
        servo2.setPower(.7);
        servo3.setPower(1);
    }

    public void shootPusher() {
        servo1.setPower(-.7);
        servo3.setPower(1);
    }

    public void transfer() {
        motor2.setPower(-.8);
    }

    public void spitOutCollection() {
        servo1.setPower(1);
        servo2.setPower(-1);
        servo3.setPower(-1);
    }

    public void shootAuto() {
        motor1.setPower(1);
        opMode.sleep(10000);
        motor1.setPower(0);
    }

    public void teleOpShoot() {

        motor1.setPower(-.9);

    }

    public void autoShoot() {
        motor1.setPower(-.9);

    }

    public void teleOpShootReverse() {
        motor1.setPower(-.2);
        motor2.setPower(.2);

    }

    public void ShootStop() {
        motor1.setPower(0);
        motor2.setPower(0);

    }

    public void collectionRest() {
        servo1.setPower(0);
        servo2.setPower(0);
        servo3.setPower(0);
    }

    public void resetOdometry() {
        follower.setStartingPose(new Pose());
    }

    public void lineUp() {
        follower.activateAllPIDFs();

        Path forwards = new Path(new BezierLine(new Pose(0, 0), new Pose(0, 0)));
        forwards.setConstantHeadingInterpolation(0);
        follower.followPath(forwards);
        while (opMode.opModeIsActive() && follower.isBusy() && !opMode.gamepad1.dpad_down) {
            follower.update();
        }

    }

    public void resetDistanceCounter() {
        counterDistance = 0;

    }

    public void checkArtifactWithCounter() {

        if (hasVoltageChanged()) {
            counterDistance = counterDistance + 1;
        }
    }

    public boolean hasVoltageChanged() {

        double difference = Math.abs(lastCountOfVoltage - sensors.distanceSensor.getVoltage());
        opMode.telemetry.addData("difference", difference);
        lastCountOfVoltage = sensors.distanceSensor.getVoltage();
        opMode.telemetry.addData("lastCountOfVoltage", lastCountOfVoltage);
        if (difference >= voltageChangedThreshold) {
            return true;
        }

        return false;


    }


    public void isArtifactThere() {
        opMode.telemetry.addData("counterDistance", counterDistance);
        if (counterDistance < 3) {
            checkArtifactWithCounter();
            collection();
        } else {
            collectionRest();
        }
    }

    public boolean iswallThere() {
        double difference = Math.abs(lastCountOfVoltage - sensors.distanceSensor.getVoltage());
        opMode.telemetry.addData("difference", difference);
        lastCountOfVoltage = sensors.distanceSensor.getVoltage();
        opMode.telemetry.addData("lastCountOfVoltage", lastCountOfVoltage);
        if (difference >= voltageChangedThreshold) {
            return true;
        }

        return false;

    }


//
//    public void armUp() {
//        int targetPosition = 3000;
//
//        double command = PIDArm.update(targetPosition, motor1.getCurrentPosition());
//
//        motor1.setPower(command);
//    }

//    public void slideInTouch() {
//        if (!slideTouch1.getState()) {
//            opMode.telemetry.addData("closing1", slideTouch1.getState());
//            motors.stopMotors();
//            return;
//        }
//        opMode.telemetry.addData("down1", slideTouch1.getState());
//
//        slideMotor1.setPower(.7);
//
//
//    }

//    public void slidePIDOut() {
//        //make it go longer an
//        int targetPosition = -2800;
//        opMode.telemetry.addData("PID", slideMotor1.getCurrentPosition());
//
//        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
//
//        slideMotor1.setPower(command);
//
//    }
//
//    public boolean isSlideUp() {
//        return slideMotor1.getCurrentPosition() < targetSlideUpPosition;
//    }
//
//    public boolean isSlideDown() {
//        return !slideTouch1.getState();
//
//    }
//
//    public void slidePIDUp() {
//
//        double command = PIDSlide1.update(targetSlideUpPosition, slideMotor1.getCurrentPosition());
//        opMode.telemetry.addData("slide power", command);
//        slideMotor1.setPower(command);
//    }
//
//    public void slidePIDUpAuto() {
//        int targetPosition = -5500;
//        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
//        slideMotor1.setPower(command);
//    }
//
//    public void servo2SpinClockwise() {
//        servo2.setPower(.65);
//    }
//
//    public void servo2SpinCounterClockwise() {
//        servo2.setPower(-.65);
//
//    }
//
//    public void servo2StopSpinning() {
//        servo2.setPower(0);
//    }
//
//    public void stopServos() {
//        servo2.setPower(0);
//    }
//
//    public void intakeClose() {
//        servo1.setPower(.7);
//
//    }
//
//    public void intakeCollect() {
//
//    }
//
//
//    public void servo2SpinClockwiseSlow() {
//        servo2.setPower(.20);
//    }
//
//    public void servo2SpinCounterClockwiseSlow() {
//        servo2.setPower(-.20);
//    }

//    public void armShortAuto() {
//        int targetPosition = -3500;
//        opMode.telemetry.addData("PID", slideMotor1.getCurrentPosition());
//        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
//        slideMotor1.setPower(command);
//    }
//
//
//    public void SlidePID() {
//        int targetPosition = 3200;
//        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
//        opMode.telemetry.addData("command", command);
//        slideMotor1.setPower(command);
//    }

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
//
//    public void startSlidePID() {
//        int targetPosition = 60;
//
//        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
//        opMode.telemetry.addData("command", command);
//        opMode.telemetry.addData("currentPosition", slideMotor1.getCurrentPosition());
//
//        slideMotor1.setPower(command);
//        motors.setSlideMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    // }

}









