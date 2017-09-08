package org.schedulex.test;

import org.junit.Test;
import org.schedulex.JobInvokePack;
import org.schedulex.StandardJobInvokePackParser;

public class StandardJobInvokePackParserTest {


    @Test
    public void test(){

        StandardJobInvokePackParser invokePackParser = new StandardJobInvokePackParser();

        JobInvokePack[] JobInvokePack = invokePackParser.parser(new TestJob());


        for(JobInvokePack pack : JobInvokePack){

            System.out.println(pack);

        }
    }
}
