package student.examples.devices;

public interface HasBatery {
    public boolean charge();
    public boolean disCharge();
    public boolean isCharge();
    public int getCharge();
    public void setCharge(int charge);
}
