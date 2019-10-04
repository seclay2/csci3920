package edu.cudenver.domain;


public class Calculator {
    private double val1;
    private double val2;

    public Calculator(double val1, double val2){
        this.val1=val1;
        this.val2=val2;

    }

    public double getSum(){
        return val1+val2;
    }
}
