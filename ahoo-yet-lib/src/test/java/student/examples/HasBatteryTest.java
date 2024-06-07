package student.examples;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.examples.devices.HasBatery;
import student.examples.devices.VacuumCleaner;

public class HasBatteryTest {
    private HasBatery hasBatery;

    @BeforeEach
    public void setup() {
        hasBatery = new VacuumCleaner(1, "Atom");
        hasBatery.setCharge(50);
    }

    @Test
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
        Assert.assertEquals(100, hasBatery.getCharge());
    }

    @Test
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
        Assert.assertEquals(0, hasBatery.getCharge());
    }
}
