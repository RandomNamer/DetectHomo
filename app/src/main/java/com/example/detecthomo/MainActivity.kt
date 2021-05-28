package com.example.detecthomo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import com.example.detecthomo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            detectHomo.setOnClickListener {
                isHomoTv.text =
                    if (isHomo()) getString(R.string.is_homo) else getString(R.string.not_homo)
            }
            moreInfoTv.apply{
                text = Html.fromHtml(
                    "<a href='https://www.harmonyos.com/cn/home/'>${getString(R.string.more_info)}</a>"
                )
                movementMethod = LinkMovementMethod.getInstance()
            }
            gotoClassinfoBtn.setOnClickListener{
                startActivity(Intent(this@MainActivity, CheckClassActivity::class.java))
            }
        }
        setContentView(binding.root)
    }

    private fun isHomo():Boolean{
        return try {
            Class.forName(getString(R.string.homo_signature)).classLoader.let{
                it!= null && it.parent == null
            }
        }catch(e:ClassNotFoundException){
            false
        }
    }

}