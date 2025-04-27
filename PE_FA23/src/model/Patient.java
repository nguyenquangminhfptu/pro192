package model;

import java.util.Date;

public class Patient 
{
    protected String id, name, phone;
    protected Date bday;
    
    public Patient() {};

    public Patient(String id, String name, Date bday, String phone) 
    {
        this.name = name;
        this.id = id;
        this.bday = bday;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }
    
    public void changePhone(String newPhone)
    {
        this.phone = newPhone;
    }
    
    @Override
    public String toString() 
    {
        return String.format(" %-4s %-22s %-31s %10s",
                getId(), getName(), getBday(), getPhone());
    }
}