package model;

public class Bicycle 
{
    protected String name;
    protected String code;
    protected double speed, distance;
    protected boolean electric;
    
    public Bicycle() {};

    public Bicycle(String name, String code, double speed, double distance, boolean electric) 
    {
        this.name = name;
        this.code = code;
        this.speed = speed;
        this.distance = distance;
        this.electric = electric;
    }

    public String getName() {
        if (electric)
            return "E-Bicycle";
        else
            return "Bicycle";
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isElectric() {
        return electric;
    }

    public void setElectric(boolean electric) {
        this.electric = electric;
    }
    
    public String needToRecharge()
    {
        if (electric && getDistance() > 60)
            return "Need to recharge batteries";
        else
            return "Don't need to recharge nigga";
    }

    @Override
    public String toString() 
    {
        return String.format(" %-10s  %-5s %7.1f %10.1f %-30s",
                getName(), getCode(), getSpeed(), getDistance(), needToRecharge());
    }
}