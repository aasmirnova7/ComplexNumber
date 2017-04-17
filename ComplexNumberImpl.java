package com.netcracker.edu.java.tasks;


import java.util.Arrays;
import java.util.Objects;

public class ComplexNumberImpl implements ComplexNumber {
    private double re;
    private double im;

    public ComplexNumberImpl(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumberImpl(String value) {
        this.set(value);
    }

    public ComplexNumberImpl() {
        this.re = 0;
        this.im = 0;
    }

    @Override
    public double getRe() {
        return re;
    }

    @Override
    public double getIm() {
        return im;
    }

    @Override
    public boolean isReal() {
        boolean flag = false;
        if (im == 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {
        String re = "";
        String im = "";
        int i = 0;
        if (value.endsWith("i")) {
            do {
                if (value.charAt(i) == 'i') {
                    if ((re == "") || (re == "-") || (re == "+")) {
                        re = re + "1";
                    }
                    im = re;
                    re = "0";
                    break;
                }
                if (((value.charAt(i) == '+') || (value.charAt(i) == '-')) && (i != 0)) {
                    break;
                }
                re = re + value.charAt(i);
                ++i;
            } while (true);

            for (int j = i; j < value.length() - 1; ++j) {
                im = im + value.charAt(j);
            }
            if (i == value.length() - 2) { // 1+i
                im = im + "1";
            }
            this.re = Double.parseDouble(re);
            this.im = Double.parseDouble(im);
        } else {
            this.re = Double.parseDouble(value);
            this.im = 0;
        }
    }

    @Override
    public ComplexNumber copy() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException { 
        ComplexNumber obj = (ComplexNumberImpl) super.clone();
        return obj;
    }

    @Override
    public String toString() {
        String s = "";
        if (re == 0.0) {
            s = im + "i";
        }
        if (im == 0.0) {
            s = re + "";
        }
        if ((re == 0.0) && (im == 0.0)) {
            s = "0.0";
        }
        if ((re != 0.0) && (im > 0.0)) {
            s = re + "+" + im + "i";
        }
        if ((re != 0.0) && (im < 0.0)) {
            s = re + "-" + (-im) + "i";
        }
        return s;
    }


    @Override
    public boolean equals(Object other) {
        if(other == null){ //нулевой объект не сравнивают
            return false;
        }
        if(!(other instanceof ComplexNumber)){ //если объекты относятся к разным классам
            return false;
        }
        final ComplexNumber obj = (ComplexNumber) other; //проверяем поля
        if(this.getIm() != obj.getIm()){
            return false;
        }
        if(this.getRe() != obj.getRe()){
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(ComplexNumber other) {
        int status;
        if ((this.re * this.re + this.im * this.im) < (other.getRe() * other.getRe() + other.getIm() * other.getIm())) {
            status = -1;
        } else {
            status = +1;
        }
        if ((this.re * this.re + this.im * this.im) == (other.getRe() * other.getRe() + other.getIm() * other.getIm())) {
            status = 0;
        }
        return status;
    }

    @Override
    public void sort(ComplexNumber[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    ComplexNumber temp = array[j].copy();
                    array[j] = array[j + 1].copy();
                    array[j + 1] = temp.copy();
                }
            }
        }
        for (ComplexNumber i : array) {
            System.out.println(i.toString());
        }
    }

    @Override
    public ComplexNumber negate() {
        this.re = -this.re;
        this.im = -this.im;
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        this.re += arg2.getRe();
        this.im += arg2.getIm();
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        ComplexNumber temp = this.copy();
        ComplexNumber temp2 = arg2.copy();
        this.re = this.getRe() * arg2.getRe() - this.getIm() * arg2.getIm();
        this.im = temp.getIm() * temp2.getRe() + temp.getRe() * temp2.getIm();
        return this;
    }

    public static void main(String[] Args) {
        ComplexNumber a = new ComplexNumberImpl("+2-i");
        ComplexNumber b = a.copy();
        ComplexNumber c = new ComplexNumberImpl(1, -8);
        ComplexNumber r = new ComplexNumberImpl(0, -1);
        ComplexNumber t = new ComplexNumberImpl(-4, 2.5);
        ComplexNumber[] d = {a, b, c, r};
        System.out.println(t.equals(t));
    }
}
