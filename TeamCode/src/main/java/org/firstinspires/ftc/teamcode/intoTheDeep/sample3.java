package org.firstinspires.ftc.teamcode.intoTheDeep;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;
import org.firstinspires.ftc.teamcode.robot.Movement;


@Autonomous(name = "sample3", group = "Linear OpMode")
public class sample3 extends LinearOpMode {
    public static int DISTANCE_AWAY_FROM_WALL = 150;
    public static int DISTANCE_GOING_TO_BASKET = -510;
    public static int LINE_UP_TO_BASKET = 490;
    public static int TURN_LEFT_DISTANCE = 320;
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        IntoTheDeep intoTheDeep = new IntoTheDeep(this);
        Sensors sensors = new Sensors(this);
     
        movement.init();
        motors.init();
        servos.init();
        sensors.init();
        intoTheDeep.init();
        intoTheDeep.slideInTouch();
        telemetry.addData("Init", "It is ON!");
        telemetry.addData("distance sensor", sensors.distanceSensor.getVoltage());
        telemetry.addData("slide position", intoTheDeep.slideMotor1.getCurrentPosition());

        telemetry.update();
        // wait for start
        waitForStart();
        runtime.reset();
        //change values for the 3 below
        movement.forwardDistance(.5, DISTANCE_AWAY_FROM_WALL);
        sleep(100);
        movement.strafeLeftDistance(.5, LINE_UP_TO_BASKET);
        sleep(100);
        movement.turnLeftDistance(.2, DISTANCE_GOING_TO_BASKET);
        sleep(100);
        intoTheDeep.intakeClose();
        movement.forwardDistance(-.5, 240);
        sleep(100);
//        intoTheDeep.armUp();
        intoTheDeep.intakeClose();
//        intoTheDeep.slidePIDUp();
//        sleep(300);

        while (opModeIsActive() && sensors.isObjectDetected()) {
            telemetry.addData("distance sensor", sensors.distanceSensor.getVoltage());
            telemetry.addData("slide position", intoTheDeep.slideMotor1.getCurrentPosition());

            intoTheDeep.intakeClose();
            intoTheDeep.armUp();
            intoTheDeep.slidePIDUp();
            telemetry.update();
            if (intoTheDeep.isSlideUp()) {

                intoTheDeep.servo2SpinCounterClockwise();
            }
        }
        intoTheDeep.servo2StopSpinning();

        while (opModeIsActive() && !intoTheDeep.isSlideDown()) {
            intoTheDeep.slideInTouch();
        }
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() <= 1) {
            intoTheDeep.straitArm();
            intoTheDeep.intakeClose();
        }
        movement.turnLeftDistance(.3, TURN_LEFT_DISTANCE);
        movement.strafeLeftDistance(.3, 300);
        //  movement.forwardDistance(.3, 70);
        runtime.reset();
        intoTheDeep.intakeCollect();
        while (opModeIsActive() && runtime.seconds() <= 1.5) {
            intoTheDeep.slidePIDOut();
        }
        while (opModeIsActive() && !sensors.isObjectDetected()) {
            intoTheDeep.servo2SpinClockwise();
        }
        intoTheDeep.intakeOpen();
        while (opModeIsActive() && !intoTheDeep.isSlideDown()) {
            intoTheDeep.slideInTouch();
        }
        movement.backwardDistance(.3, 200);
        sleep(100);
        movement.turnLeftDistance(.3, -800);

        while (opModeIsActive() && sensors.isObjectDetected()) {
            telemetry.addData("distance sensor", sensors.distanceSensor.getVoltage());
            telemetry.addData("slide sensor", intoTheDeep.slideMotor1.getTargetPosition());

            intoTheDeep.intakeClose();
            intoTheDeep.armUp();
            intoTheDeep.slidePIDUp();
            telemetry.update();
            if (intoTheDeep.isSlideUp()) {

                intoTheDeep.servo2SpinCounterClockwise();
            }
        }


    }
}
