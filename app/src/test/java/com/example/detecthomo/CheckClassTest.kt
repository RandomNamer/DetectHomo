package com.example.detecthomo

import com.example.detecthomo.reflectutil.ClassProperty
import com.example.detecthomo.reflectutil.ClassPropertyChecker
import org.junit.Test


class CheckClassTest {

    private val classForTest = ClassForTest()

    private val classInfoChecker = ClassPropertyChecker(listOf())

    @Test
    fun testGetClassInfo(){
        classInfoChecker.registerClassProperty(ClassProperty.DECLARED_METHODS)
        val classInfo = classInfoChecker.getFormattedClassProperties(classForTest::class.java.typeName)
        println(classInfo)
    }
}