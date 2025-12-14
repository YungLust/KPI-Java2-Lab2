package org.example;

import java.time.LocalDate;
import java.time.Period;

public class Undead {
    private String name;
    private String type;
    private String domain;
    private LocalDate appearanceDate;
    private int attackDamage;

    public Undead(String name, String type, String domain, LocalDate appearanceDate, int attackDamage) {
        this.name = name;
        this.type = type;
        this.domain = domain;
        this.appearanceDate = appearanceDate;
        this.attackDamage = attackDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public LocalDate getAppearanceDate() {
        return appearanceDate;
    }

    public void setAppearanceDate(LocalDate appearanceDate) {
        this.appearanceDate = appearanceDate;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int existedFor(){
        return Period.between(appearanceDate,LocalDate.now()).getYears();
    }
}
