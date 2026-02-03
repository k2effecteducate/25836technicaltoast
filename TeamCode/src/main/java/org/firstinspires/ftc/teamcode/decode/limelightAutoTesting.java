package org.firstinspires.ftc.teamcode.decode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp
public class limelightAutoTesting extends LinearOpMode {

    Limelight3A limelight;

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.start();
        limelight.pipelineSwitch(0);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                LLResult result = limelight.getLatestResult();
                if (result != null && result.isValid()) {
                    Pose3D botpose = result.getBotpose();
                    if (botpose != null) {
                        double x = botpose.getPosition().x;
                        double y = botpose.getPosition().y;
                        telemetry.addData("MT1 Location", "(" + x + ", " + y + ")");
                    }
                }
            }
        }
    }
}


