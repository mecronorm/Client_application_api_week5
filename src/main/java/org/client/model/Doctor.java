package org.client.model;

public class Doctor {
    private String name;
    private String specialization;

    public Doctor(String name, String specialization){
        this.name = name;
        this.specialization = specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
