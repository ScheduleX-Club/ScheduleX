package org.schedulex.invoke;


import java.io.InputStream;

public class BatchSystem {

    private String path;

    protected BatchSystem(String path){
        this.path = path;
    }


    protected BatchSystem(InputStream inputStream){}

    public void startSystem(){

        new ClassPathDescriptionLoader().load(path);

    }




}
