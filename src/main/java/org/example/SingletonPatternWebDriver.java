package org.example;

import org.openqa.selenium.WebDriver;

public class SingletonPatternWebDriver {

    //private instance of the class
    private static SingletonPatternWebDriver instance;


    //Private constructor
    private SingletonPatternWebDriver(){}


    //public method
    public void getInstance(){

        if(instance==null){
            synchronized (instance){
            instance=new SingletonPatternWebDriver();}

        }
    }


}
