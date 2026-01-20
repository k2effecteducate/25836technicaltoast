package org.firstinspires.ftc.teamcode.decode;


import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Configurable
@TeleOp(name = " testV", group = "TeleOp")
public class testVelocity extends LinearOpMode {

    private DcMotorEx flywheelMotor;

    private static final double TICKS_PER_REV = 28;
    private static final double GEAR_RATIO = 1.0;
    private static final double TARGET_RPM = 400;

    @Override
    public void runOpMode() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "motor1");

        flywheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            double ticksPerSecond = (TARGET_RPM / 60.0) * TICKS_PER_REV * GEAR_RATIO;
            flywheelMotor.setVelocity(ticksPerSecond);

            // Debug info
            telemetry.addData("Target RPM", TARGET_RPM);
            telemetry.addData("Target Ticks/Sec", ticksPerSecond);
            telemetry.addData("Motor Velocity", flywheelMotor.getVelocity());
            telemetry.addData("Encoder Position", flywheelMotor.getCurrentPosition());
            telemetry.addData("Motor Mode", flywheelMotor.getMode());
            telemetry.update();
        }
    }
}

