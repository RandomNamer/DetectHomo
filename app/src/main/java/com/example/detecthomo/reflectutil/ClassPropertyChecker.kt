package com.example.detecthomo.reflectutil

import java.lang.StringBuilder
import java.util.regex.Pattern

class ClassPropertyChecker(properties: List<ClassProperty> = listOf()) {

    private var classProperties: MutableList<ClassProperty> = properties.toMutableList()

    private var displayOptionRegex: String = "\\\\w+\\\\"

    private var pattern = Pattern.compile(displayOptionRegex)

    private fun setDisplayOptionRegex(r: String) {
        displayOptionRegex = r
        pattern = Pattern.compile(displayOptionRegex)
    }

    fun registerClassProperty(p: ClassProperty) {
        classProperties.add(p)
    }

    fun setClassProperties(l: List<ClassProperty>) {
        classProperties = l.toMutableList()
    }

    fun unregisterAllClassInfoTypes() {
        classProperties.clear()
    }

    fun getFormattedClassProperties(classname: String): List<Pair<ClassProperty?, String>> {
        var sb = StringBuilder()
        val result = mutableListOf<Pair<ClassProperty?, String>>()
        try {
            val clazz = Class.forName(classname)

            classProperties.forEach {
                result.add(it to getFormattedClassProperty(clazz, it))
            }
        } catch (e: ClassNotFoundException) {
            result.add(Pair<ClassProperty?, String>(null, "Class not found"))
        }
        return result
    }

    private fun getFormattedClassProperty(clazz: Class<*>, classProperty: ClassProperty): String {
        val sb = StringBuilder()
        classProperty.let {
            try {
                if (it.propertyType == PropertyType.LIST) {
                    (Class.forName("java.lang.Class")
                        .getDeclaredMethod("get${it.propertyName}")
                        .invoke(clazz) as Array<*>).run {
                        if (!isNotEmpty()) {
                            sb.appendLine("No ${it.singularName()} found in class.")
                        }
                        forEach { property ->
                            sb.appendLine(property.toString())
                        }
                    }
                } else {
                    (Class.forName("java.lang.Class")
                        .getDeclaredMethod("get$it")
                        .invoke(clazz) as Class<*>).run {
                        if (this == null) {
                            sb.appendLine("No ${it.singularName()} found in class")
                        } else {
                            sb.appendLine("${toString()}")
                        }
                    }
                }
            } catch (e: NoSuchMethodException) {
                sb.appendLine("Can't get ${it.singularName()} property from the class.")
            } catch (e: SecurityException) {
                sb.appendLine("Getting property ${it.singularName()} from class is not allowed.")
            }
        }
        return sb.toString()
    }


}
