package org.schedulex.test;

import org.junit.Test;
import org.schedulex.common.utils.PackageScanner;

import java.util.List;

/**
 * Created by Norman S L Dai on 2017/9/8.
 */
public class PackageScannerTest {

    @Test
    public void test(){

        List<Class> classList = PackageScanner.getClasses("org.schedulex.test");

        System.out.println(classList);

    }





}
