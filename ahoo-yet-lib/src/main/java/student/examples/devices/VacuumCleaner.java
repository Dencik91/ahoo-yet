package student.examples.devices;

public class VacuumCleaner implements HasPowerOnOff{

    private PowerState powerState;

    public VacuumCleaner() {
        switchOff();
    }

    @Override
    public boolean switchOn() {
        powerState = PowerState.ON;
        return true;
    }

    @Override
    public boolean switchOff() {
        powerState = PowerState.OFF;
        return false;
    }

    @Override
    public boolean isOn() {
        return powerState != PowerState.OFF;
    }
}
