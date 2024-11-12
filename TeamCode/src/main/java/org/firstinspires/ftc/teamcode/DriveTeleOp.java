package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;
import org.firstinspires.ftc.teamcode.robot.ArmServos;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.java_websocket.framing.ContinuousFrame;


@TeleOp(name = "DriveTeleOp", group = "Linear OpMode")

public class DriveTeleOp extends LinearOpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor armMotor;
    private Servo servo2;
    private Servo servo1;
    private DcMotor slideMotor;
    // private Servo servo3;

    @Override
    public void runOpMode() {
        //    imu = hardwareMap.get(Gyroscope.class, "imu");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        slideMotor = hardwareMap.get(DcMotor.class, "slideMotor");


        servo2 = hardwareMap.get(Servo.class, "servo2");
        // servo3 = hardwareMap.get(Servo.class, "servo3");
        double modifier = 1;

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        //  servo2.setDirection(Servo.Direction.REVERSE);

        telemetry.addData("Initialized", "is a win");
        telemetry.update();
        Movement movement = new Movement(this);
        ArmServos armServos = new ArmServos(this);

        movement.init();
        armServos.init();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("on", "on");
            telemetry.update();


            if (gamepad2.a) {
               armServos.armTeleOp(.4,2000);

            }
            if (gamepad2.right_bumper) {
                servo2.setPosition(.5);


            }
            if (gamepad2.x) {
                armServos.armTeleOp(.4, 1000);
            }
            if (gamepad1.b) {
                modifier = 2;
            }
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.left_stick_x;
            double strafe = gamepad1.right_stick_x;
            double MrArm = -gamepad2.right_stick_y;
            double MrHand = -gamepad2.left_stick_y;
            double MrSlideOut = gamepad2.right_trigger;
            double MrSlideIn = -gamepad2.left_trigger;
            backLeft.setPower((drive - strafe + turn) / modifier);
            frontLeft.setPower((drive - strafe - turn) / modifier);
            backRight.setPower((drive + strafe - turn) / modifier);
            frontRight.setPower((drive + strafe + turn) / modifier);
            servo1.setPosition(MrHand);
            armMotor.setPower(MrArm);
            slideMotor.setPower(-gamepad2.left_trigger);
            slideMotor.setPower(gamepad2.right_trigger);
            servo2.setPosition(0);

        }
    }
}







