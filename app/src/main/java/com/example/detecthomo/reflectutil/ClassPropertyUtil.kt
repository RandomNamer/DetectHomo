package com.example.detecthomo.reflectutil

import android.icu.text.PluralFormat

fun String.asListProperty() = ClassProperty(this, PropertyType.LIST)
fun String.asSingleProperty() = ClassProperty(this, PropertyType.SINGLE)
fun ClassProperty.singularName() = if (this.propertyType == PropertyType.SINGLE) this.propertyName else with(this.propertyName) {
    if (this[length - 2] == 'e') substring(0, length - 2)
    else substring(0, length - 1)
}

enum class PropertyType {
    LIST,
    SINGLE
}

data class ClassProperty(val propertyName: String, val propertyType: PropertyType) {
    companion object {
        val METHODS = "Methods".asListProperty()
        val DECLARED_METHODS = "DeclaredMethods".asListProperty()
        val INSTANCE_METHODS = "InstanceMethods".asListProperty()
        val FIELDS = "Fields".asListProperty()
        val DECLARED_FIELDS = "DeclaredFields".asListProperty()
        val DECLARED_CLASSES = "DeclaredClasses".asListProperty()
        val DECLARED_CONSTRUCTORS = "DeclaredConstructors".asListProperty()
        val DECLARING_CLASS = "DeclaringClass".asSingleProperty()
        val ENCLOSING_CLASS = "EnclosingClass".asSingleProperty()
        val CLASSES = "Classes".asListProperty()
    }
}




