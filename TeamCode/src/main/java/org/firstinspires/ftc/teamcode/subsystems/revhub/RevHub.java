package org.firstinspires.ftc.teamcode.subsystems.revhub;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.revextensions2.ExpansionHubEx;
import org.openftc.revextensions2.ExpansionHubMotor;

/**
 * Created by Antoine on 7/4/2021
 */
public class RevHub {

    Telemetry telemetry;

    ExpansionHubEx hub;

    public RevHub(HardwareMap map, Telemetry telemetry, String hubName) {
        this.telemetry = telemetry;

        hub = map.get(ExpansionHubEx.class, hubName);
    }

    public void setLedColor(int r, int g, int b) {
        hub.setLedColor(r, g, b);
    }

    public double getTotalCurrentDraw() {
        return hub.getTotalModuleCurrentDraw(ExpansionHubEx.CurrentDrawUnits.AMPS);
    }

    public double getVoltage5v() {
        return hub.read5vMonitor(ExpansionHubEx.VoltageUnits.VOLTS);
    }

    public double getVoltage12v() {
        return hub.read12vMonitor(ExpansionHubEx.VoltageUnits.VOLTS);
    }

    public double getHubInternalTemp() {
        return hub.getInternalTemperature(ExpansionHubEx.TemperatureUnits.FAHRENHEIT);
    }

    public boolean checkHubTempWarning() {
        return hub.isModuleOverTemp();
    }

    public void hubPowerMonitor() {
        telemetry.addData("12 Volt", getVoltage12v());
        telemetry.addData("5 Volt", getVoltage5v());
        telemetry.addData("Current", getTotalCurrentDraw());
    }

    public void hubTempMonitor() {
        telemetry.addData("Internal Hub Temperature", getHubInternalTemp());
    }
}
