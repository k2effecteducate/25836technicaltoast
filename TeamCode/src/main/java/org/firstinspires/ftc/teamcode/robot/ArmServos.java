package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmServos {
    private LinearOpMode opMode;
    public DcMotorEx armMotor;
    public Servo servo1;
    public Servo servo2;
    public Servo servo3;
    public DcMotor slideMotor;
    public DigitalChannel slideTouch;
    private PIDController PIDArm;


    private int armTarget = 0;
    private int slideTarget = 0;

    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 96;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public ArmServos(LinearOpMode myOpMode) {
        opMode = myOpMode;

    }

    public void init() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");

        armMotor = opMode.hardwareMap.get(DcMotorEx.class, "armMotor");
        servo1 = opMode.hardwareMap.get(Servo.class, "servo1");
        servo2 = opMode.hardwareMap.get(Servo.class, "servo2");
        slideMotor = opMode.hardwareMap.get(DcMotor.class, "slideMotor");
        slideTouch = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch");
        servo3 = opMode.hardwareMap.get(Servo.class, "servo3");
        slideTouch.setMode(DigitalChannel.Mode.INPUT);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
        slideMotor.setPower(0);
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

    public void closeServoTurn() {
        servo2.setPosition(.65);
    }

    public void intakeClose() {
        servo1.setPosition(1);
    }

    public void openServoTurn() {
        servo2.setPosition(0);

    }

    public void intakeOpen() {
        servo1.setPosition(0);
    }

    public void lock() {
        setArmMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setArmMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setTargetPosition(0);
        arm(.2, 0);
    }

    public void unlock() {
        setArmMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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

    public void teleOpArmControls() {
        armMotor.setPower(opMode.gamepad2.right_stick_y);
    }

    public void teleOpIntakeControls() {
        servo1.setPosition(-opMode.gamepad2.left_stick_y);
    }

    public void transfer() {
        int targetPosition = 1800;

        double command = PIDArm.update(targetPosition, armMotor.getCurrentPosition());
        opMode.telemetry.addData("command", command);

        armMotor.setPower(command);
    }

    public void armBack() {
        int targetPosition = 55;

        double command = PIDArm.update(targetPosition, armMotor.getCurrentPosition());
        armMotor.setPower(command);
        opMode.telemetry.addData("armMotorCurrentPosition", armMotor.getCurrentPosition());
        opMode.telemetry.addData("armMotorCommand", command);


    }

    public void collection() {
        int targetPosition = 4000;

        double command = PIDArm.update(targetPosition, armMotor.getCurrentPosition());
        armMotor.setPower(command);

    }

    public void armReset() {
        opMode.telemetry.addData("armMotorCurrentPosition", armMotor.getCurrentPosition());

        int targetPosition = 4700;

        double command = PIDArm.update(targetPosition, armMotor.getCurrentPosition());
        armMotor.setPower(command);
        opMode.telemetry.addData("armMotorCommand", command);
    }

    public void armHang() {
        int targetPosition = 3000;

        double command = PIDArm.update(targetPosition, armMotor.getCurrentPosition());
        armMotor.setPower(command);
    }
}






