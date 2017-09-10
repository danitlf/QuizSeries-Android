package com.example.dani.quizseries.models;

import java.io.Serializable;

/**
 * Created by dani on 20/08/17.
 */

public class Pergunta  implements Serializable {
    private String label;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private String correta;

    public Pergunta(String label, String opt1, String opt2, String opt3, String opt4, String correta) {
        this.label = label;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.correta = correta;
    }

    public Pergunta() {

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public String getCorreta() {
        return correta;
    }

    public void setCorreta(String correta) {
        this.correta = correta;
    }
}
