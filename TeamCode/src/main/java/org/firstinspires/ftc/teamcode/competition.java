package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.teamcode.robot.IntoTheDeep;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;


@TeleOp(name = "competition ", group = "Linear OpMode")

public class competition extends LinearOpMode {

    public enum RobotState {
        COLLECTION, ARM_UP, INTAKE_DUMP, INTAKE_IN, SLIDE_IN, SLIDE_OUT, INTAKE_OUT, INTAKE_RESET
    }

    competition.RobotState robotState = RobotState.INTAKE_IN;

    @Override
    public void runOpMode() {
        telemetry.addData("Initialized", "Press Start");
        telemetry.update();
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        IntoTheDeep intoTheDeep = new IntoTheDeep(this);
        Sensors sensors = new Sensors(this);

        movement.init();
        servos.init();
        motors.init();
        intoTheDeep.init();
        sensors.init();


        // Wait for the game to start (driver presses PLAY)

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("servo2", servos.servo1.getPosition());
            telemetry.addData("state", robotState);
            movement.teleOpControls();
            switch (robotState) {
                case INTAKE_IN:
                    intoTheDeep.intakeClose();
                    intoTheDeep.straitArm();
                    intoTheDeep.servo2StopSpinning();


                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.ARM_UP;
                    }


                    break;

                case SLIDE_IN:
                    intoTheDeep.intakeOpen();
                    intoTheDeep.slideInTouch();
                    // intoTheDeep.straitArm();
                    intoTheDeep.servo2StopSpinning();
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.ARM_UP;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.INTAKE_IN;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT;
                    }

                    break;

                case SLIDE_OUT:
                    intoTheDeep.slidePIDOut();
                    intoTheDeep.intakeOpen();
                    intoTheDeep.servo2StopSpinning();
                    //intoTheDeep.intakeOpen();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.INTAKE_IN;
                    }

                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP;
                    }
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.COLLECTION;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.COLLECTION;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT;
                    }
                    break;
                case COLLECTION:
                    // intoTheDeep.straitArm();
                    intoTheDeep.slidePIDOut();
                    intoTheDeep.intakeCollect();
                    intoTheDeep.sensorCollect();
                    if (sensors.isObjectDetected()) {
                        sleep(100);
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.INTAKE_IN;
                    }
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT;
                    }
                    break;
                case ARM_UP:
                    intoTheDeep.armUp();
                    intoTheDeep.slidePIDUp();
                    intoTheDeep.servo1.setPosition(0);
                    intoTheDeep.servo2StopSpinning();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT;
                    }

                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.INTAKE_RESET;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT;
                    }
                    break;
                case INTAKE_DUMP:
                    intoTheDeep.slidePIDUp();
                    intoTheDeep.armUp();
                    intoTheDeep.intakeDump();
                    intoTheDeep.servo2SpinClockwise();
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.INTAKE_RESET;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.INTAKE_IN;
                    }
                    break;
                case INTAKE_OUT:
                    intoTheDeep.slidePIDUp();
                    intoTheDeep.armUp();
                    intoTheDeep.servo2SpinCounterClockwise();
                    intoTheDeep.intakeDump();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.INTAKE_RESET;
                    }
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.INTAKE_IN;
                    }

                    break;
                case INTAKE_RESET:
                    intoTheDeep.servo2StopSpinning();
                    intoTheDeep.openServo();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT;
                    }

                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP;
                    }

                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT;
                    }
                    break;


            }
            telemetry.update();
        }
    }
}

