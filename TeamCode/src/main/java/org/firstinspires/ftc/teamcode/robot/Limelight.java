package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.ArrayList;
import java.util.List;

public class Limelight {
    private LinearOpMode opMode;
    public Limelight3A limelight;


    public Limelight(LinearOpMode myOpMode) {
        opMode = myOpMode;
    }

    public void init() {
        limelight = opMode.hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);


    }

    public void start() {
        limelight.start();
    }

    public boolean isAprilTagFound(int aprilID) {

        LLResult result = limelight.getLatestResult();
        List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
        boolean foundTag = false;
        for (LLResultTypes.FiducialResult fr : fiducialResults) {
            opMode.telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
            if (fr.getFiducialId() == aprilID) {
                foundTag = true;
            }

            if (foundTag) {
                // stop Odometry movement

            } else {
                //odometry movement
            }
        }
        return foundTag;
    }


    public List<Integer> getAprilTagIDs() {

        LLResult result = limelight.getLatestResult();
        List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();

        List<Integer> foundAprilTagIDs = new ArrayList<Integer>();

        for (LLResultTypes.FiducialResult fr : fiducialResults) {
            opMode.telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees());
            foundAprilTagIDs.add(fr.getFiducialId());


        }
        return foundAprilTagIDs;
    }

    public void printTelemetry() {
        LLResult result = limelight.getLatestResult();
        LLStatus status = limelight.getStatus();
        opMode.telemetry.addData("Name", "%s", status.getName());
        opMode.telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                status.getTemp(), status.getCpu(), (int) status.getFps());
        opMode.telemetry.addData("Pipeline", "Index: %d, Type: %s",
                status.getPipelineIndex(), status.getPipelineType());

        if (result.isValid()) {
            // Access general information
            Pose3D botpose = result.getBotpose();
            double captureLatency = result.getCaptureLatency();
            double targetingLatency = result.getTargetingLatency();
            double parseLatency = result.getParseLatency();
            opMode.telemetry.addData("LL Latency", captureLatency + targetingLatency);
            opMode.telemetry.addData("Parse Latency", parseLatency);
            opMode.telemetry.addData("PythonOutput", java.util.Arrays.toString(result.getPythonOutput()));

            opMode.telemetry.addData("tx", result.getTx());
            opMode.telemetry.addData("txnc", result.getTxNC());
            opMode.telemetry.addData("ty", result.getTy());
            opMode.telemetry.addData("tync", result.getTyNC());

            opMode.telemetry.addData("Botpose", botpose.toString());

            // Access barcode results
            List<LLResultTypes.BarcodeResult> barcodeResults = result.getBarcodeResults();
            for (LLResultTypes.BarcodeResult br : barcodeResults) {
                opMode.telemetry.addData("Barcode", "Data: %s", br.getData());
            }

            // Access classifier results
            List<LLResultTypes.ClassifierResult> classifierResults = result.getClassifierResults();
            for (LLResultTypes.ClassifierResult cr : classifierResults) {
                opMode.telemetry.addData("Classifier", "Class: %s, Confidence: %.2f", cr.getClassName(), cr.getConfidence());
            }

            // Access detector results
            List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
            for (LLResultTypes.DetectorResult dr : detectorResults) {
                opMode.telemetry.addData("Detector", "Class: %s, Area: %.2f", dr.getClassName(), dr.getTargetArea());
            }


            // Access color results
            List<LLResultTypes.ColorResult> colorResults = result.getColorResults();
            for (LLResultTypes.ColorResult cr : colorResults) {
                opMode.telemetry.addData("Color", "X: %.2f, Y: %.2f", cr.getTargetXDegrees(), cr.getTargetYDegrees());
            }
        } else {
            opMode.telemetry.addData("Limelight", "No data available");
        }
        opMode.telemetry.update();

    }


}





