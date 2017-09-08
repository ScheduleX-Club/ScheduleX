package org.schedulex.test;

import org.junit.Test;

import java.util.Date;

public class CronExpressionEx {


    @Test
    public void test(){
        try {
            org.schedulex.expression.CronExpressionEx exp = new org.schedulex.expression.CronExpressionEx("0 0 10,14,16 * * ? ");

            Date dd = new Date();
            for (int i = 1; i <= 3; i++)
            {
                dd = exp.getNextValidTimeAfter(dd);
                System.out.println(dd);
                dd = new java.util.Date(dd.getTime() + 1000);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
