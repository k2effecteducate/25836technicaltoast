package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.decode.SensorPinpointDriveToPoint;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Odometry {
    private LinearOpMode opMode;
    public DcMotorEx frontRight;
    public DcMotorEx frontLeft;
    public DcMotorEx backRight;
    public DcMotorEx backLeft;
    private PIDController PIDfrontLeft;
    private PIDController PIDfrontRight;
    private PIDController PIDBackLeft;
    private PIDController PIDBackRight;
    public GoBildaPinpointDriver pinpoint;
    // public Pose2D pose2D = pinpoint.getPosition();

    private int frontLeftTarget = 0;
    private int backLeftTarget = 0;
    private int frontRightTarget = 0;
    private int backRightTarget = 0;


    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 96;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public Odometry(LinearOpMode myOpMode) {
        opMode = myOpMode;
    }

    public void init() {


        //    imu = hardwareMap.get(Gyroscope.class, "imu");
        backRight = opMode.hardwareMap.get(DcMotorEx.class, "backRight");
        backLeft = opMode.hardwareMap.get(DcMotorEx.class, "backLeft");
        frontRight = opMode.hardwareMap.get(DcMotorEx.class, "frontRight");
        frontLeft = opMode.hardwareMap.get(DcMotorEx.class, "frontLeft");
        pinpoint = opMode.hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDfrontLeft = new PIDController(1, 0, 0);
        PIDfrontRight = new PIDController(1, 0, 0);
        PIDBackLeft = new PIDController(1, 0, 0);
        PIDBackRight = new PIDController(1, 0, 0);


    }


    public void configurePinpoint() {
        pinpoint.setOffsets(-84.0, -168.0, DistanceUnit.MM); //these are tuned for 3110-0002-0001 Product Insight #1

        // If you're using another kind of odometry pod, uncomment setEncoderResolution and input the
        // number of ticks per unit of your odometry pod.  For example:
        //     pinpoint.setEncoderResolution(13.26291192, DistanceUnit.MM);

        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();
    }

    public void resetToZero() {
        pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
    }

    public void setPosition(double x, double y, double heading) {
        new Pose2D(DistanceUnit.MM, x, y, AngleUnit.DEGREES, heading);
    }

    public void pinpointMovePosition(double x, double y, double heading) {
        Pose2D move = new Pose2D(DistanceUnit.MM, x, y, AngleUnit.DEGREES, heading);
        // nav.driveTo(pinpoint.getPosition(),move, 0.7, 6);
    }


}
