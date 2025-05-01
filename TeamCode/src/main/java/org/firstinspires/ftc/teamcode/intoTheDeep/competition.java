package org.firstinspires.ftc.teamcode.intoTheDeep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.robot.Basket;
import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Movement;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;


@TeleOp(name = "competition ", group = "Linear OpMode")

public class competition extends LinearOpMode {

    public enum RobotState {
        COLLECTION_Y, ARM_UP, INTAKE_DUMP_LEFT, INTAKE_IN_DOWN, SLIDE_IN_A, SLIDE_OUT_Y2, INTAKE_OUT_RIGHT, INTAKE_RESET, SPIT_OUT_B, DISABLE, COLLECT_START
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
                    intoTheDeep.intakeClose();
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
                    if (gamepad2.start) {
                        robotState = RobotState.COLLECT_START;
                    }
                    if (gamepad2.b) {
                        robotState = RobotState.SPIT_OUT_B;
                    }

                    break;

                case SLIDE_OUT_Y2:
                    intoTheDeep.straitArm();
                    intoTheDeep.slidePIDOut();
                    intoTheDeep.intakeClose();
                    intoTheDeep.servo2StopSpinning();
                    if (gamepad2.b) {
                        robotState = RobotState.SPIT_OUT_B;
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
                        robotState = RobotState.COLLECTION_Y;
                    }
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT_RIGHT;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.COLLECT_START;
                    }
                    if (gamepad2.b) {
                        robotState = RobotState.SPIT_OUT_B;
                    }
                    break;
                case COLLECTION_Y:
                    intoTheDeep.straitArm();
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
                    if (gamepad2.b) {
                        robotState = RobotState.SPIT_OUT_B;
                    }
                    if (gamepad2.back) {
                        robotState = RobotState.DISABLE;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.COLLECT_START;
                    }

                    break;
                case ARM_UP:

                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }

                    if (gamepad2.x) {
                        robotState = RobotState.INTAKE_RESET;
                    }

                    intoTheDeep.armUp();
                    intoTheDeep.intakeClose();
                    intoTheDeep.servo2StopSpinning();
                    break;
                case INTAKE_DUMP_LEFT:
                    intoTheDeep.slidePIDUp();
                    intoTheDeep.armUp();
                    intoTheDeep.intakeClose();
                    intoTheDeep.servo2SpinClockwiseSlow();
                    if (gamepad2.dpad_left) {
                        robotState = RobotState.INTAKE_OUT_RIGHT;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.INTAKE_RESET;
                    }

                    break;
                case INTAKE_OUT_RIGHT:
                    intoTheDeep.slidePIDUp();
                    intoTheDeep.armUp();
                    intoTheDeep.servo2SpinCounterClockwiseSlow();
                    intoTheDeep.intakeClose();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.x) {
                        robotState = RobotState.INTAKE_RESET;
                    }
                    if (gamepad2.dpad_right) {
                        robotState = RobotState.INTAKE_DUMP_LEFT;
                    }


                    break;
                case INTAKE_RESET:
                    intoTheDeep.servo2StopSpinning();
                    intoTheDeep.intakeClose();
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

                    break;
                case SPIT_OUT_B:
                    intoTheDeep.servo2SpinCounterClockwise();
                    intoTheDeep.slidePIDOut();
                    intoTheDeep.intakeCollect();
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.dpad_down) {
                        robotState = RobotState.INTAKE_IN_DOWN;
                    }

                    if (gamepad2.y) {
                        robotState = RobotState.COLLECTION_Y;
                    }


                    break;
                case DISABLE:
                    intoTheDeep.intakeCollect();
                    intoTheDeep.straitArm();
                    intoTheDeep.slidePIDOut();
                    servos.disableServo1();


                    if (gamepad2.y) {
                        robotState = RobotState.COLLECTION_Y;
                    }
                    if (gamepad2.a) {
                        robotState = RobotState.SLIDE_IN_A;
                    }
                    if (gamepad2.b) {
                        robotState = RobotState.SPIT_OUT_B;
                    }
                    if (gamepad2.start) {
                        robotState = RobotState.COLLECT_START;
                    }
                case COLLECT_START:
                    intoTheDeep.straitArm();
                    intoTheDeep.slidePIDOut();
                    intoTheDeep.intakeCollect();
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
                    if (gamepad2.b) {
                        robotState = RobotState.SPIT_OUT_B;
                    }
                    if (gamepad2.back) {
                        robotState = RobotState.DISABLE;
                    }

                    break;


            }
            telemetry.update();
        }
    }
}

