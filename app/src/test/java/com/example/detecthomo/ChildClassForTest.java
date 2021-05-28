package com.example.detecthomo;

public class ChildClassForTest extends ClassForTest {

    @Override
    protected void protectedMethod() {
        System.out.println("overrided method!");
    }
    //Java can't override fields

    public ChildClassForTest(){}

    public void childMethod(){}
}
