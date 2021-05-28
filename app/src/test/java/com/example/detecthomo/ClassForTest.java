package com.example.detecthomo;

public class ClassForTest {
    //field:
    public int publicField;
    private int privateField = 2;
    protected int protectedField = 3;

    //methods:
    private void privateMethod(){ System.out.println("private method"); }

    public void publicMethod(int arg){ System.out.println("public method"); }

    protected void protectedMethod(){ System.out.println("protectedMethod"); }

    //constructors:

    public ClassForTest(){}

    public ClassForTest(int arg1, int arg2, int arg3){
        publicField = arg1;
        privateField = arg2;
        protectedField = arg3;
    }

    private ClassForTest(int arg){
        publicField = arg;
    }

}
