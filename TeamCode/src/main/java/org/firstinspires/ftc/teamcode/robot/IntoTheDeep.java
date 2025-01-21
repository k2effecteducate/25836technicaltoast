package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;


public class IntoTheDeep {
    private LinearOpMode opMode;
    public DcMotorEx armMotor;
    public Servo servo1;
    public Servo servo2;
    public Servo servo3;
    public CRServo servo4;
    public DcMotor slideMotor1;
    public DcMotor slideMotor2;
    public DigitalChannel slideTouch1;
    public DigitalChannel slideTouch2;
    private PIDController PIDArm;
    private int armTarget = 0;
    private int slideTarget = 0;
    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 96;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    private PIDController PIDSlide1;
    private PIDController PIDSlide2;
    public Motors motors;
    public Movement movement;
    public Servos servos;
    public Sensors sensors;


    public IntoTheDeep(LinearOpMode myOpMode) {
        opMode = myOpMode;

    }


    public void init() {
        armMotor = opMode.hardwareMap.get(DcMotorEx.class, "armMotor");
        servo4 = opMode.hardwareMap.get(CRServo.class, "servo4");
        servo1 = opMode.hardwareMap.get(Servo.class, "servo1");
        servo2 = opMode.hardwareMap.get(Servo.class, "servo2");
        slideMotor1 = opMode.hardwareMap.get(DcMotor.class, "slideMotor1");
        slideMotor2 = opMode.hardwareMap.get(DcMotor.class, "slideMotor2");
        slideTouch1 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch1");
        slideTouch2 = opMode.hardwareMap.get(DigitalChannel.class, "slideTouch2");
        servo3 = opMode.hardwareMap.get(Servo.class, "servo3");
        slideTouch1.setMode(DigitalChannel.Mode.INPUT);
        slideTouch2.setMode(DigitalChannel.Mode.INPUT);
        slideMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        PIDArm = new PIDController(.01, 0, .03);
        PIDSlide1 = new PIDController(-0.01, -0, -0);
        PIDSlide2 = new PIDController(-0.01, -0, -0);
        motors = new Motors(opMode);
        movement = new Movement(opMode);
        servos = new Servos(opMode);
        sensors = new Sensors(opMode);
        servos.init();
        motors.init();
        movement.init();
        sensors.init();


    }


    public void slidePIDTogether() {
        int targetPosition = 100;

        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        double command1 = PIDSlide2.update(targetPosition, slideMotor2.getCurrentPosition());
        slideMotor1.setPower(command);
        slideMotor2.setPower(command1);
    }

    public void slideInTouch() {
        if (!slideTouch2.getState()) {
            opMode.telemetry.addData("closing2", slideTouch2.getState());
            slideMotor2.setPower(0);
            motors.stopMotors();
            return;
        }
        if (!slideTouch1.getState()) {
            opMode.telemetry.addData("closing1", slideTouch1.getState());
            slideMotor1.setPower(0);
            motors.stopMotors();
            return;
        }
        opMode.telemetry.addData("down1", slideTouch1.getState());

        slideMotor1.setPower(.7);
        slideMotor2.setPower(.7);

    }

    public void SlidePIDOut() {
        int targetPosition = 3200;

        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        double command1 = PIDSlide2.update(targetPosition, slideMotor2.getCurrentPosition());
        slideMotor1.setPower(command);
        slideMotor2.setPower(command1);
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
        motors.setArmMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors.setArmMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setTargetPosition(0);
        motors.arm(.2, 0);
    }

    public void unlock() {
        motors.setArmMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void transfer() {
        int targetPosition = 1840;

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
        int targetPosition = 3100;

        double command = PIDArm.update(targetPosition, armMotor.getCurrentPosition());
        armMotor.setPower(command);
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

    public void slideTeleOp(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            motors.setSlideMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            slideTarget = slideMotor1.getCurrentPosition() + moveCounts;
            slideMotor1.setTargetPosition(slideTarget);

            motors.setSlideMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            motors.rightSlide(speed, 0);

        }
    }

    public void slideAuto(double speed, double distance) {
        if (opMode.opModeIsActive()) {
            motors.setSlideMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
            int moveCounts = (int) (distance * COUNTS_PER_INCH);
            slideTarget = slideMotor1.getCurrentPosition() + moveCounts;
            slideMotor1.setTargetPosition(slideTarget);

            motors.setSlideMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            motors.rightSlide(speed, 0);
            while (opMode.opModeIsActive() && slideMotor1.isBusy()) {

            }
        }
    }

    public void startSlidePID() {
        //  slideTeleOp(.3, 386);
        int targetPosition = 60;

        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        opMode.telemetry.addData("command", command);
        opMode.telemetry.addData("currentPosition", slideMotor1.getCurrentPosition());

        slideMotor1.setPower(command);
        motors.setSlideMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    public void highBasketSlide() {

        int targetPosition = 3200;

        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        opMode.telemetry.addData("command", command);

        slideMotor1.setPower(command);
    }

    public void transferSlide() {
        int targetPosition = -200;

        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        opMode.telemetry.addData("command", command);

        slideMotor1.setPower(command);
    }

    public void startSlide() {
        int targetPosition = 400;

        double command = PIDSlide1.update(targetPosition, slideMotor1.getCurrentPosition());
        opMode.telemetry.addData("command", command);

        slideMotor1.setPower(command);
    }
}






