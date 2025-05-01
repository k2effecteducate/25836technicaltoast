package org.firstinspires.ftc.teamcode.robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.intoTheDeep.IntoTheDeep;


@Config
@TeleOp(name = "TestServos", group = "Linear OpMode")
public class TestServos extends LinearOpMode {


    @Override
    public void runOpMode() {
        Movement movement = new Movement(this);
        Motors motors = new Motors(this);
        Servos servos = new Servos(this);
        IntoTheDeep intoTheDeep = new IntoTheDeep(this);
        Sensors sensors = new Sensors(this);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        movement.init();
        motors.init();
        servos.init();
        sensors.init();
        intoTheDeep.init();
        // intoTheDeep.slideInTouch();
        telemetry.addData("Init", "It is ON!");
        telemetry.update();
        double position = 0;
        // wait for start
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                intoTheDeep.servo2SpinClockwise();
            }
//            telemetry.addData("servo position", servos.servo1.getPosition());
//            servos.servo1.setPosition(position);
//            if (gamepad1.a) {
//                position = position + .3;
//            }
//            if (gamepad1.b) {
//                position = position - .3;
//            }
//            telemetry.update();
        }
    }
}