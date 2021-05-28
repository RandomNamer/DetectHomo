package com.example.detecthomo

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.detecthomo.adapter.ClassInfoAdapter
import com.example.detecthomo.databinding.ActivityCheckClassBinding
import com.example.detecthomo.reflectutil.ClassProperty
import com.example.detecthomo.reflectutil.ClassPropertyChecker
import com.example.detecthomo.vm.CheckClassViewModel
import java.lang.StringBuilder
import java.util.regex.Pattern


class CheckClassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckClassBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel:CheckClassViewModel by viewModels()
        binding = ActivityCheckClassBinding.inflate(layoutInflater)
        val classInfoAdapter = ClassInfoAdapter()
        with(binding) {
            setSupportActionBar(toolbar)
            getClassinfoBtn.setOnClickListener { viewModel.onGettingClassInfo(inputClassname.text.toString()) }
            classinfoRv.apply {
                adapter = classInfoAdapter
                layoutManager = LinearLayoutManager(this@CheckClassActivity)
            }
            viewModel.classInfoLiveData.observe(this@CheckClassActivity){
                classInfoAdapter.submitList(it)
            }

        }

        supportActionBar?.apply {
            title = getString(R.string.classinfo_title)
            setDisplayHomeAsUpEnabled(true)
        }
        setContentView(binding.root)
    }

    private fun getClassInfo() {

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

}