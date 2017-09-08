package org.schedulex.test;

import org.junit.Test;
import org.schedulex.JobInvokePack;
import org.schedulex.JobInvokePackContainer;
import org.schedulex.JobInvokePackParser;
import org.schedulex.StandardJobInvokePackParser;


public class JobInvokePackParserTest {



    @Test
    public void test(){

        JobInvokePackContainer packContainer = JobInvokePackContainer.getInstance();

        JobInvokePackParser packParser = new StandardJobInvokePackParser();
        JobInvokePack[] packs = packParser.parser(new TestJob());
        JobInvokePack[] packs1 = packParser.parser(new TestJob2());

        packContainer.addJobDescriptions(packs);
        packContainer.addJobDescriptions(packs1);

        // pls use the debug model test the parsed instance object
    }




}
