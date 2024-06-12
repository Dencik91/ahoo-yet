package student.examples;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import student.examples.devices.HasBatery;
import student.examples.devices.VacuumCleaner;


import java.io.File;
import java.io.IOException;

public class HasBatteryTest {
    private HasBatery hasBatery;

    @DataProvider(name = "charge-range-test")
    public Integer[][] readDataFromXML() throws IOException {
        File file = new File(
                "src/test/data/charge-range-test.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(file);
        JsonNode inputNode = rootNode.path("input");
        JsonNode expectedNode = rootNode.path("expected");
        Integer[][] intArray = new Integer[inputNode.size()][2];
        for (int i=0; i<inputNode.size(); i++) {
            intArray[i][0] = inputNode.get(i).asInt();
            intArray[i][1] = expectedNode.get(i).asInt();
        }
        return intArray;

    }

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

    @Test(dataProvider = "charge-range-test")
    public void testSetCharge(Integer[][] data) {
        for (Integer[] row : data) {
            for (Integer value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
