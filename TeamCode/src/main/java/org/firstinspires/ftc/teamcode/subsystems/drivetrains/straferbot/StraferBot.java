package org.firstinspires.ftc.teamcode.subsystems.drivetrains.straferbot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.drivetrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.revhub.RevHub;
import org.openftc.revextensions2.ExpansionHubEx;

/**
 * Created by Antoine on 7/4/2021
 */
public class StraferBot {

    private HardwareMap map;

    public MecanumDrive drive;
    public RevHub controlHub;

    private Telemetry telemetry;
    TelemetryPacket packet = new TelemetryPacket();
    FtcDashboard dashboard = FtcDashboard.getInstance();

    public StraferBot(HardwareMap map, Telemetry telemetry) {
        this.map = map;
        this.telemetry = telemetry;

        drive = new MecanumDrive(map, telemetry);
        controlHub = new RevHub(map, "Control Hub");
    }

    public void updateDriveTrain(GamepadEx gamepad) {
        drive.setMotorPowers(gamepad.getLeftX(), gamepad.getLeftY(), gamepad.getRightX());
    }

    public void checkWarnings() {
        if (controlHub.checkTempWarning()) {
            kill();
        }
    }

    public void kill() {
        drive.stopMotors();
    }
}
