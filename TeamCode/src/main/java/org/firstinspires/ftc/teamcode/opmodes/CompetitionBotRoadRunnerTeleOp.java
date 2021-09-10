package org.firstinspires.ftc.teamcode.opmodes;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.drivetrains.compbot.CompBot;
import org.firstinspires.ftc.teamcode.subsystems.roadrunner.competitionchassis.CompDrive;
import org.firstinspires.ftc.teamcode.subsystems.roadrunner.competitionchassis.CompPoseStorage;
import org.firstinspires.ftc.teamcode.subsystems.telemetry.TelemetryHeaders;

@TeleOp(name = "CompetitionRRTeleOp", group = "Test")
public class CompetitionBotRoadRunnerTeleOp extends LinearOpMode{

	@Override
	public void runOpMode() throws InterruptedException{

		CompBot robot = new CompBot(hardwareMap, telemetry);

		waitForStart();

		if (isStopRequested()) return;

		while (opModeIsActive() && !isStopRequested()) {

			switch (robot.driveMode) {
				case STANDARD_DRIVE:
					robot.drive.setWeightedDrivePower(
							new Pose2d(
									-gamepad1.left_stick_y,
									-gamepad1.left_stick_x,
									-gamepad1.right_stick_x
							)
					);
					break;

				case FIELD_CENTRIC_DRIVE:
					//Create a vector from the gamepad x/y inputs
					//Then, rotate that vector by the inverse of that heading
					Vector2d input = new Vector2d(
						-(gamepad1.left_stick_y),
						-(gamepad1.left_stick_x)
					).rotated(-robot.poseEstimate.getHeading());

					//Pass in the rotated input + right stick value for rotation
					robot.drive.setWeightedDrivePower(
							new Pose2d(
									input.getX(),
									input.getY(),
									-(gamepad1.right_stick_x)
							)
					);
					break;

				case ALIGN_TO_POINT:

					break;
				default:
					robot.driveMode = CompBot.driveModes.STANDARD_DRIVE;
					break;
			}

			robot.update(); //Update all robot
		}
	}
}
