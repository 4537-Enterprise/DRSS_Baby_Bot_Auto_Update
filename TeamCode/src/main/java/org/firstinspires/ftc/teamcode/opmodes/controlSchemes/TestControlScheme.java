package org.firstinspires.ftc.teamcode.opmodes.controlSchemes;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

public class TestControlScheme {
    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;

    public ButtonReader testButton;
    public ButtonReader testButton2;

    public TestControlScheme(GamepadEx gamepadEx1, GamepadEx gamepadEx2) {
        this.gamepadEx1 = gamepadEx1;
        this.gamepadEx2 = gamepadEx2;

        testButton = new ButtonReader(
                gamepadEx1, GamepadKeys.Button.A
        );

        testButton2 = new ButtonReader(
                gamepadEx1, GamepadKeys.Button.B
        );
    }

    public void readButtons() {
        gamepadEx1.readButtons();
        gamepadEx2.readButtons();
    }
}
