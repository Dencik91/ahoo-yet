package student.examples;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import student.examples.devices.HasBatery;
import student.examples.devices.VacuumCleaner;

public class HasBatteryTest {
    private HasBatery hasBatery;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        hasBatery = new VacuumCleaner(1, "Atom");
        hasBatery.setCharge(50);
    }

    @Test(groups = {"unit"})
    public void testOverCharge() {
        int TARGET_CHARGE = 100;
        int chargeBefore = hasBatery.getCharge();
        hasBatery.charge();
        int chargeAfter = hasBatery.getCharge();

        int deltaCharge = chargeAfter - chargeBefore;
        int steps = (TARGET_CHARGE - chargeAfter) / deltaCharge;
        steps++;

        while (steps-- != 0) {
            hasBatery.charge();
        }
        Assert.assertEquals(hasBatery.getCharge(), 100);
    }

    @Test(groups = {"unit"})
    public void testUnderDischarge() {
        final int TARGET_CHARGE = 0;
        int chargeBefore = hasBatery.getCharge();
        hasBatery.disCharge();
        int chargeAfter = hasBatery.getCharge();

        int deltaCharge = chargeBefore - chargeAfter;
        int steps = chargeAfter / deltaCharge;
        steps++;

        while (steps-- != 0) {
            hasBatery.disCharge();
        }
        Assert.assertEquals(hasBatery.getCharge(), 0);
    }
}
