package model;

import java.util.Date;

public class Candidate 
{
    protected String id, name, email;
    protected Date bday;
    protected double grades;
    
    public Candidate() {};

    public Candidate(String id, String name, Date bday, double grades, String email) 
    {
        this.name = name;
        this.id = id;
        this.bday = bday;
        this.grades = grades;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public double getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }
    
    @Override
    public String toString() 
    {
        return String.format(" %-4s %-24s %-31s %4.2f  %-25s",
                getId(), getName(), getBday(), getGrades(), getEmail());
    }
}