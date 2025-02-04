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
        COLLECTION_Y, ARM_UP, INTAKE_DUMP_LEFT, INTAKE_IN_DOWN, SLIDE_IN_A, SLIDE_OUT_Y2, INTAKE_OUT_RIGHT, INTAKE_RESET
    }

    competition.RobotState robotState = RobotState.INTAKE_IN_DOWN;

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
                case INTAKE_IN_DOWN:
                    intoTheDeep.intakeClose();
                    intoTheDeep.straitArm();
                    intoTheDeep.servo2StopSpinning();


                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT_Y2;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.ARM_UP;
                    }


                    break;

                case SLIDE_IN_A:
                    intoTheDeep.intakeOpen();
                    intoTheDeep.slideInTouch();
                    intoTheDeep.servo2StopSpinning();
                    if (gamepad2.dpad_up) {
                        robotState = RobotState.ARM_UP;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT_Y2;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.INTAKE_IN_DOWN;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT_RIGHT;
                    }

                    break;

                case SLIDE_OUT_Y2:
                    intoTheDeep.slidePIDOut();
                    intoTheDeep.intakeOpen();
                    intoTheDeep.servo2StopSpinning();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.INTAKE_IN_DOWN;
                    }

                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP_LEFT;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.COLLECTION_Y;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT_RIGHT;
                    }
                    break;
                case COLLECTION_Y:
                    intoTheDeep.slidePIDOut();
                    intoTheDeep.intakeCollect();
                    intoTheDeep.sensorCollect();
                    if (sensors.isObjectDetected()) {
                        sleep(100);
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.INTAKE_IN_DOWN;
                    }
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP_LEFT;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT_Y2;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT_RIGHT;
                    }
                    break;
                case ARM_UP:
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT_RIGHT;
                    }

                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP_LEFT;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.INTAKE_RESET;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT_Y2;
                    }
                    if (intoTheDeep.isArmUp()) {
                        intoTheDeep.slidePIDUp();
                        return;
                    }
                    intoTheDeep.servo1.setPosition(0);
                    intoTheDeep.servo2StopSpinning();
                    break;
                case INTAKE_DUMP_LEFT:
                    intoTheDeep.slidePIDUp();
                    intoTheDeep.armUp();
                    intoTheDeep.intakeDump();
                    intoTheDeep.servo2SpinClockwise();
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT_RIGHT;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.INTAKE_RESET;
                    }
                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT_Y2;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    break;
                case INTAKE_OUT_RIGHT:
                    intoTheDeep.slidePIDUp();
                    intoTheDeep.armUp();
                    intoTheDeep.servo2SpinCounterClockwise();
                    intoTheDeep.intakeDump();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.INTAKE_RESET;
                    }
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP_LEFT;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.SLIDE_IN_A;
                    }

                    break;
                case INTAKE_RESET:
                    intoTheDeep.slidePIDUp();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT_RIGHT;
                    }

                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP_LEFT;
                    }

                    if (gamepad2.y) {
                        robotState = RobotState.SLIDE_OUT_Y2;
                    }
                    break;


            }
            telemetry.update();
        }
    }
}

