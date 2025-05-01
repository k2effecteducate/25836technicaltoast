package org.firstinspires.ftc.teamcode.intoTheDeep;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Motors;
import org.firstinspires.ftc.teamcode.robot.Sensors;
import org.firstinspires.ftc.teamcode.robot.Servos;
import org.firstinspires.ftc.teamcode.robot.Movement;

@Config
@Autonomous(name = "park", group = "Linear OpMode")
public class park extends LinearOpMode {

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
        intoTheDeep.slideInTouch();
        telemetry.addData("Init", "It is ON!");
        telemetry.update();
        // wait for start
        waitForStart();
        movement.forwardDistance(.3, 50);
        movement.strafeLeft(-.3, 3000);

    }
}
