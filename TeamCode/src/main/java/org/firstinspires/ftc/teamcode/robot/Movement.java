package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Movement {
    private LinearOpMode opMode;
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public PIDController PIDfrontLeft;
    public PIDController PIDfrontRight;
    public PIDController PIDBackLeft;
    public PIDController PIDBackRight;


    private int frontLeftTarget = 0;
    private int backLeftTarget = 0;
    private int frontRightTarget = 0;
    private int backRightTarget = 0;


    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 96;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public Movement(LinearOpMode myOpMode) {
        opMode = myOpMode;
    }

    public void init() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");
        backRight = opMode.hardwareMap.get(DcMotor.class, "backRight");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = opMode.hardwareMap.get(DcMotor.class, "frontLeft");

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
        PIDfrontLeft = new PIDController(.01, 0, .03);
        PIDfrontRight = new PIDController(-0.01, -0, -0);
        PIDBackLeft = new PIDController(.01, 0, .03);
        PIDBackRight = new PIDController(-0.01, -0, -0);


    }

    public void teleOpControls() {
        double modifier = 1;
        if (opMode.gamepad1.b) {
            modifier = 2;
        }
        double drive = -opMode.gamepad1.left_stick_y;
        double turn = opMode.gamepad1.left_stick_x;
        double strafe = opMode.gamepad1.right_stick_x;
        double MrHand = -opMode.gamepad2.left_stick_y;
        backLeft.setPower((drive - strafe + turn) / modifier);
        frontLeft.setPower((drive - strafe - turn) / modifier);
        backRight.setPower((drive + strafe - turn) / modifier);
        frontRight.setPower((drive + strafe + turn) / modifier);

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

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            forward(speed, 0);
            while (opMode.opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {


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
            frontRight.setTargetPosition(frontRightTarget);
            backLeft.setTargetPosition(backLeftTarget);
            backRight.setTargetPosition(backRightTarget);
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








