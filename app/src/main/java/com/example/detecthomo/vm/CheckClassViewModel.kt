package com.example.detecthomo.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.detecthomo.reflectutil.ClassProperty
import com.example.detecthomo.reflectutil.ClassPropertyChecker

class CheckClassViewModel: ViewModel() {
    private val classPropertyChecker = ClassPropertyChecker().apply {
        setClassProperties(
            listOf(
                ClassProperty.METHODS,
                ClassProperty.DECLARED_CLASSES,
                ClassProperty.DECLARED_CONSTRUCTORS,
                ClassProperty.DECLARED_FIELDS,
                ClassProperty.DECLARED_METHODS,
                ClassProperty.CLASSES,
                ClassProperty.DECLARING_CLASS,
                ClassProperty.FIELDS
            )
        )
    }

    private val _classInfoLiveData = MutableLiveData<List<Pair<ClassProperty?, String>>>()
    val classInfoLiveData: LiveData<List<Pair<ClassProperty?, String>>>
        get() = _classInfoLiveData


    fun onGettingClassInfo(classname: String){
        _classInfoLiveData.value = classPropertyChecker.getFormattedClassProperties(classname)
    }

}