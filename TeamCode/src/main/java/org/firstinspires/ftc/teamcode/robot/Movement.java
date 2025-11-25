package org.firstinspires.ftc.teamcode.robot;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class Movement {
    private LinearOpMode opMode;
    public DcMotorEx frontRight;
    public DcMotorEx frontLeft;
    public DcMotorEx backRight;
    public DcMotorEx backLeft;
    private PIDController PIDfrontLeft;
    private PIDController PIDfrontRight;
    private PIDController PIDBackLeft;
    private PIDController PIDBackRight;


    private int frontLeftTarget = 0;
    private int backLeftTarget = 0;
    private int frontRightTarget = 0;
    private int backRightTarget = 0;
    public static double DISTANCE = -35;

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 19.2;
    static final double WHEEL_DIAMETER_INCHES = 3.75;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public Movement(LinearOpMode myOpMode) {
        opMode = myOpMode;
    }

    public void init() {

        backRight = opMode.hardwareMap.get(DcMotorEx.class, "backRight");
        backLeft = opMode.hardwareMap.get(DcMotorEx.class, "backLeft");
        frontRight = opMode.hardwareMap.get(DcMotorEx.class, "frontRight");
        frontLeft = opMode.hardwareMap.get(DcMotorEx.class, "frontLeft");
        //  frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
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


    public void odemetryForward(double WhereWeAreX, double WhereWeAreY, double WhereWeAreGoingX, double WhereWeAreGoingY, double heading) {
        Follower follower = Constants.createFollower(opMode.hardwareMap);
        follower.activateAllPIDFs();
        Path forwards = new Path(new BezierLine(new Pose(WhereWeAreX, WhereWeAreY), new Pose(WhereWeAreGoingX, WhereWeAreGoingY)));
        forwards.setConstantHeadingInterpolation(heading);
        follower.followPath(forwards);
        while (opMode.opModeIsActive() && follower.isBusy()) {
            follower.update();
        }
    }

    public void odemetryStrafe(double WhereWeAreX, double WhereWeAreY, double WhereWeAreGoingX, double WhereWeAreGoingY, double heading) {
        Follower follower = Constants.createFollower(opMode.hardwareMap);
        follower.activateAllPIDFs();
        Path strafeLeft = new Path(new BezierLine(new Pose(WhereWeAreX, WhereWeAreY), new Pose(WhereWeAreGoingX, WhereWeAreGoingY)));
        strafeLeft.setConstantHeadingInterpolation(heading);
        follower.followPath(strafeLeft);
        while (opMode.opModeIsActive() && follower.isBusy()) {
            follower.update();
        }
    }

    public void odemetryTurn(double WhereWeAreX, double WhereWeAreY, double WhereWeAreGoingX, double WhereWeAreGoingY, double heading) {
        Follower follower = Constants.createFollower(opMode.hardwareMap);
        follower.activateAllPIDFs();
        Path turn = new Path(new BezierLine(new Pose(WhereWeAreX, WhereWeAreY), new Pose(WhereWeAreGoingX, WhereWeAreGoingY)));

        turn.setConstantHeadingInterpolation(heading);
        follower.followPath(turn);
        while (opMode.opModeIsActive() && follower.isBusy()) {
            follower.update();
        }
    }


    public void teleOpControls() {

        double modifier = 1;
        if (opMode.gamepad1.b) {
            modifier = 1;
        }
        double drive = opMode.gamepad1.left_stick_y;
        double turn = -opMode.gamepad1.right_stick_x;
        double strafe = opMode.gamepad1.left_stick_x;
        double MrHand = -opMode.gamepad2.left_stick_y;
        backLeft.setPower((drive - strafe + turn) / modifier);
        frontLeft.setPower((drive + strafe + turn) / modifier);
        backRight.setPower((drive + strafe - turn) / modifier);
        frontRight.setPower((drive - strafe - turn) / modifier);

    }

    public void PIDForward(int targetPosition) {

        double command3 = PIDfrontLeft.update(targetPosition, frontLeft.getCurrentPosition());
        double command2 = PIDBackRight.update(targetPosition, backRight.getCurrentPosition());
        double command1 = PIDBackLeft.update(targetPosition, backLeft.getCurrentPosition());
        double command = PIDfrontRight.update(targetPosition, frontRight.getCurrentPosition());
        frontRight.setPower(command);
        backLeft.setPower(command1);
        backRight.setPower(command2);
        frontLeft.setPower(command3);


    }

    public void PIDStrafeLeft() {
        int targetPosition1 = 1;
        int targetPosition = -1;
        double command3 = PIDfrontLeft.update(targetPosition1, frontLeft.getCurrentPosition());
        double command2 = PIDBackRight.update(targetPosition1, backRight.getCurrentPosition());
        double command1 = PIDBackLeft.update(targetPosition, backLeft.getCurrentPosition());
        double command = PIDfrontRight.update(targetPosition, frontRight.getCurrentPosition());
        frontRight.setPower(command);
        backLeft.setPower(command1);
        backRight.setPower(command2);
        frontLeft.setPower(command3);

    }

    public void PIDTurnLeft() {
        int targetPosition1 = 1;
        int targetPosition = -1;
        double command3 = PIDfrontLeft.update(targetPosition1, frontLeft.getCurrentPosition());
        double command2 = PIDBackRight.update(targetPosition, backRight.getCurrentPosition());
        double command1 = PIDBackLeft.update(targetPosition1, backLeft.getCurrentPosition());
        double command = PIDfrontRight.update(targetPosition, frontRight.getCurrentPosition());
        frontRight.setPower(command);
        backLeft.setPower(command1);
        backRight.setPower(command2);
        frontLeft.setPower(command3);
    }

    public void forward(double speed, long time) {
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);
        opMode.sleep(time);
    }

    public void strafeLeft(double speed, long time) {
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
        opMode.sleep(time);
    }

    public void strafeRight(double speed, long time) {
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
        opMode.sleep(time);
    }

    public void turnLeft(double speed, long time) {
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
        opMode.sleep(time);
    }

    public void turnRight(double speed, long time) {
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
        opMode.sleep(time);
    }

    public void backward(double speed, long time) {
        frontLeft.setPower(-speed);
        frontRight.setPower(-speed);
        backLeft.setPower(-speed);
        backRight.setPower(-speed);
        opMode.sleep(time);
    }

    public void stopMotors() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void setMotorMode(DcMotor.RunMode mode) {
        frontLeft.setMode(mode);
        frontRight.setMode(mode);
        backLeft.setMode(mode);
        backRight.setMode(mode);
    }


    public void forwardDistance(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            frontLeftTarget = frontLeft.getCurrentPosition() + moveCounts;
            backLeftTarget = backLeft.getCurrentPosition() + moveCounts;
            frontRightTarget = frontRight.getCurrentPosition() + moveCounts;
            backRightTarget = backRight.getCurrentPosition() + moveCounts;
            frontLeft.setTargetPosition(frontLeftTarget);
            frontRight.setTargetPosition(frontRightTarget);
            backLeft.setTargetPosition(backLeftTarget);
            backRight.setTargetPosition(backRightTarget);
            opMode.telemetry.addData("position", frontLeftTarget);
            opMode.telemetry.update();
            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            forward(speed, 0);
            while (opMode.opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
                opMode.telemetry.addData("curent position", frontLeft.getCurrentPosition());
                opMode.telemetry.update();

            }

            stopMotors();
            setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void turnLeftDistance(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            frontLeftTarget = frontLeft.getCurrentPosition() + moveCounts;
            backLeftTarget = backLeft.getCurrentPosition() + moveCounts;
            frontRightTarget = frontRight.getCurrentPosition() + moveCounts;
            backRightTarget = backRight.getCurrentPosition() + moveCounts;
            frontLeft.setTargetPosition(frontLeftTarget);
            frontRight.setTargetPosition(-frontRightTarget);
            backLeft.setTargetPosition(backLeftTarget);
            backRight.setTargetPosition(-backRightTarget);
            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            turnLeft(speed, 0);
            while (opMode.opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {


            }

            stopMotors();
            setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }


    public void strafeRightDistance(double speed, long distance) {
        if (opMode.opModeIsActive()) {
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            frontLeftTarget = frontLeft.getCurrentPosition() + moveCounts;
            backLeftTarget = backLeft.getCurrentPosition() + moveCounts;
            frontRightTarget = frontRight.getCurrentPosition() + moveCounts;
            backRightTarget = backRight.getCurrentPosition() + moveCounts;
            frontLeft.setTargetPosition(-frontLeftTarget);
            frontRight.setTargetPosition(frontRightTarget);
            backLeft.setTargetPosition(backLeftTarget);
            backRight.setTargetPosition(-backRightTarget);

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            strafeRight(speed, 0);
            while (opMode.opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            }
            stopMotors();
            setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void backwardDistance(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            frontLeftTarget = frontLeft.getCurrentPosition() + moveCounts;
            backLeftTarget = backLeft.getCurrentPosition() + moveCounts;
            frontRightTarget = frontRight.getCurrentPosition() + moveCounts;
            backRightTarget = backRight.getCurrentPosition() + moveCounts;
            frontLeft.setTargetPosition(-frontLeftTarget);
            frontRight.setTargetPosition(-frontRightTarget);
            backLeft.setTargetPosition(-backLeftTarget);
            backRight.setTargetPosition(-backRightTarget);

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            backward(speed, 0);
            while (opMode.opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {


            }
            stopMotors();
            setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);


        }
    }

    public void strafeLeftDistance(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            frontLeftTarget = frontLeft.getCurrentPosition() + moveCounts;
            backLeftTarget = backLeft.getCurrentPosition() + moveCounts;
            frontRightTarget = frontRight.getCurrentPosition() + moveCounts;
            backRightTarget = backRight.getCurrentPosition() + moveCounts;
            frontLeft.setTargetPosition(frontLeftTarget);
            frontRight.setTargetPosition(-frontRightTarget);
            backLeft.setTargetPosition(-backLeftTarget);
            backRight.setTargetPosition(backRightTarget);

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            strafeLeft(speed, 0);
            while (opMode.opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {


            }
            stopMotors();
            setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }


}








