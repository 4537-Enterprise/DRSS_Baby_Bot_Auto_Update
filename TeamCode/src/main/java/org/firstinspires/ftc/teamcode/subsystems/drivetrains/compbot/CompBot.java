package org.firstinspires.ftc.teamcode.subsystems.drivetrains.compbot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmodes.CompetitionBotRoadRunnerTeleOp;
import org.firstinspires.ftc.teamcode.subsystems.roadrunner.competitionchassis.CompDrive;
import org.firstinspires.ftc.teamcode.subsystems.roadrunner.competitionchassis.CompPoseStorage;
import org.firstinspires.ftc.teamcode.subsystems.telemetry.TelemetryHeaders;

/**
 * Created by Antoine on 9/9/2021
 */
public class CompBot {

    private HardwareMap map;

    public CompDrive drive;
    public Pose2d poseEstimate;

    public enum driveModes {
        STANDARD_DRIVE,
        FIELD_CENTRIC_DRIVE,
        ALIGN_TO_POINT
    }
    public driveModes driveMode = driveModes.STANDARD_DRIVE;

    private Telemetry telemetry;
    TelemetryHeaders headers = new TelemetryHeaders();

    TelemetryPacket packet = new TelemetryPacket();
    FtcDashboard dashboard = FtcDashboard.getInstance();

    public CompBot(HardwareMap map, Telemetry telemetry) {
        this.map = map;
        this.telemetry = telemetry;

        drive = new CompDrive(map); //Initialize the drivetrain
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); //Disable velocity control because it is not necessary in TeleOp
        drive.setPoseEstimate(CompPoseStorage.currentPose); //Retrieve our pose from the Competition Pose Storage static field
    }

    public void update() {
        drive.update();
        poseEstimate = drive.getPoseEstimate(); //Read the current position

        updateTelemetry();
        updateDashboard();
    }

   void updateTelemetry() {
        //Print Positional Telemetry
        telemetry.addLine(headers.drivetrain);
        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.addData("drive mode", driveMode);

        telemetry.update();
    }

    void updateDashboard() {
        packet.addLine(headers.drivetrain);
        packet.put("x", poseEstimate.getX());
        packet.put("y", poseEstimate.getY());
        packet.put("heading", poseEstimate.getHeading());
        packet.put("drive mode", driveMode);

        dashboard.sendTelemetryPacket(packet);
    }
}
