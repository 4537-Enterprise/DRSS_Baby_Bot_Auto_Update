package org.firstinspires.ftc.teamcode.opmodes;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.roadrunner.competitionchassis.CompDrive;
import org.firstinspires.ftc.teamcode.subsystems.roadrunner.competitionchassis.CompPoseStorage;
import org.firstinspires.ftc.teamcode.subsystems.telemetry.TelemetryHeaders;

@TeleOp(name = "CompetitionRRTeleOp", group = "Test")
public class CompetitionBotRoadRunnerTeleOp extends LinearOpMode{

	//GamepadEx gamepadEx1 = new GamepadEx(gamepad1);
	//GamepadEx gamepadEx2 = new GamepadEx(gamepad2);

	double speedModifier = 1;

	enum driveModes {
		STANDARD_DRIVE,
		FIELD_CENTRIC_DRIVE,
		ALIGN_TO_POINT
	}

	/*ButtonReader driveModeButton = new ButtonReader(
			gamepadEx1, GamepadKeys.Button.A
	);*/

	TelemetryHeaders headers = new TelemetryHeaders();

	@Override
	public void runOpMode() throws InterruptedException{

		CompDrive drive = new CompDrive(hardwareMap); //Initialize the drivetrain
		drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); //Disable velocity control because it is not necessary in TeleOp
		drive.setPoseEstimate(CompPoseStorage.currentPose); //Retrieve our pose from the Competition Pose Storage static field

		driveModes driveMode = driveModes.STANDARD_DRIVE;

		waitForStart();

		if (isStopRequested()) return;

		while (opModeIsActive() && !isStopRequested()) {

			Pose2d poseEstimate = drive.getPoseEstimate(); //Read the current position

			switch (driveMode) {
				case STANDARD_DRIVE:
					drive.setWeightedDrivePower(
							new Pose2d(
									-gamepad1.left_stick_y,
									-gamepad1.left_stick_x,
									-gamepad1.right_stick_x
							)
					);

				case FIELD_CENTRIC_DRIVE:
					/*//Create a vector from the gamepad x/y inputs
					//Then, rotate that vector by the inverse of that heading
					Vector2d input = new Vector2d(
						-(gamepad1.left_stick_y),
						-(gamepad1.left_stick_x)
					).rotated(-poseEstimate.getHeading());

					//Pass in the rotated input + right stick value for rotation
					drive.setWeightedDrivePower(
							new Pose2d(
									input.getX(),
									input.getY(),
									-(gamepad1.right_stick_x)
							)
					);*/

				case ALIGN_TO_POINT:

				default:
					driveMode = driveModes.STANDARD_DRIVE;
			}

			drive.update(); //Update all RoadRunner

			//Print Positional Telemetry
			telemetry.addLine(headers.drivetrain);
			telemetry.addData("x", poseEstimate.getX());
			telemetry.addData("y", poseEstimate.getY());
			telemetry.addData("heading", poseEstimate.getHeading());
			telemetry.addData("drive mode", driveMode);
			telemetry.update();

			//Controller Update
			//gamepadEx1.readButtons();
			//Switch to standard drive on button press
			//if (gamepad1.b) {driveMode = driveModes.STANDARD_DRIVE;}
			//Switch to field centric on button press
			//if (gamepad1.a) {driveMode = driveModes.FIELD_CENTRIC_DRIVE;}
		}
	}
}
