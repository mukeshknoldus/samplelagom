package com.knoldus.samplelagom.hello.impl;

import com.knoldus.samplelagom.hello.api.MarksPojo;

/**
 * Created by knoldus on 13/11/17.
 */
public class Manipulator {
    public Float retieveData(MarksPojo marksPojo) {
        int c = marksPojo.getComputer();
        int e = marksPojo.getEnglish();
        int h = marksPojo.getHindi();
        int m = marksPojo.getMaths();
        int s = marksPojo.getScience();
        
        Float avg = Float.valueOf((c + e + h + m + s) / 5 );
        return avg;
    }
}
