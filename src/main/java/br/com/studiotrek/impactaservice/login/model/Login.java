package br.com.studiotrek.impactaservice.login.model;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class Login implements Serializable {
    private String cookin;
    private String error;

    public Login() {

    }

    public Login(String error) {
        this.error = error;
    }

    public String getCookin() {
        return cookin;
    }

    public void setCookin(String cookin) {
        this.cookin = cookin;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
