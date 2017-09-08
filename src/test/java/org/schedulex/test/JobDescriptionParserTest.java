package org.schedulex.test;

import org.junit.Test;
import org.schedulex.AnnotationJobDescriptionParser;
import org.schedulex.JobDescription;
import org.schedulex.JobDescriptionParser;


public class JobDescriptionParserTest {

    @Test
    public void test(){
        JobDescriptionParser parser = new AnnotationJobDescriptionParser();
        JobDescription[] parser1 = parser.parser(TestJob.class);

       for(JobDescription description : parser1){
           System.out.println(description);
       }
    }
}
