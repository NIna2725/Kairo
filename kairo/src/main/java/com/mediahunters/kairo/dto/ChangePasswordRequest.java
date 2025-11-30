package com.mediahunters.kairo.dto;

public class ChangePasswordRequest {

    private String passwordActual;
    private String nuevaPassword;

    // Getters y Setters
    public String getPasswordActual() {
        return passwordActual;
    }

    public void setPasswordActual(String passwordActual) {
        this.passwordActual = passwordActual;
    }

    public String getNuevaPassword() {
        return nuevaPassword;
    }

    public void setNuevaPassword(String nuevaPassword) {
        this.nuevaPassword = nuevaPassword;
    }
}
