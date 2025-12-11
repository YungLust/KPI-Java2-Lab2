package org.example;


import java.time.LocalDate;
import java.time.Period;

public class User {
    private String firstName;
    private String lastName;
    private LocalDate dayOfBirth;
    private String livingLocation;
    private int salary;
    private int age;

    public User(String firstName, String lastName, LocalDate dayOfBirth, String livingLocation, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
        this.livingLocation = livingLocation;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getLivingLocation() {
        return livingLocation;
    }

    public void setLivingLocation(String livingLocation) {
        this.livingLocation = livingLocation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge(){
        return Period.between(dayOfBirth,LocalDate.now()).getYears();
    }
}
