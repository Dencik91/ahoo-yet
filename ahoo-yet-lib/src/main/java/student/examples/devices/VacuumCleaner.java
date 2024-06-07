package student.examples.devices;

public class VacuumCleaner extends Device implements HasPowerStates, HasBatery {

    private PowerState powerState;
    private int charge;
    private final int MIN_CHARGE = 10;

    public VacuumCleaner() {
        init();
    }

    public VacuumCleaner(int id, String name) {
        super(id, name);
        setCharge(0);
        switchOff();
    }

    private void init() {
        setCharge(0);
        switchOff();
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        if ((charge >= 0) && (charge <= 100)){
            this.charge = charge;
        }
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

    @Override
    public boolean charge() {

        setCharge(charge + 5);
        return false;
    }

    @Override
    public boolean disCharge() {
        setCharge(charge - 5);
        return false;
    }

    @Override
    public boolean isCharge() {
        return charge >= MIN_CHARGE;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n |" +
                "\n + --- >" +
                ("  VacuumCleaner powerState=" + powerState + ", charge=" + charge);
    }
}
