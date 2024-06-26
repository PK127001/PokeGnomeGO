package com.example.pokegnomego

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pokegnomego.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val NAME_KEY = "name_key"
        const val PASSWORD_KEY = "password_key"
    }
    private lateinit var sharedpreferences: SharedPreferences
    private var usrname: String? = null
    private var password: String? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usrnameEdit = findViewById<EditText>(R.id.username)
        val passwordEdit = findViewById<EditText>(R.id.password)
        val loginBut = findViewById<Button>(R.id.login_button)
        val registerBut = findViewById<Button>(R.id.register_button)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        usrname = sharedpreferences.getString("NAME_KEY", null)
        password = sharedpreferences.getString("PASSWORD_KEY", null)


        loginBut.setOnClickListener {
            if (TextUtils.isEmpty(usrnameEdit.text.toString()) && TextUtils.isEmpty(passwordEdit.text.toString())) {
                Toast.makeText(this@MainActivity, "Wpisz username i hasło", Toast.LENGTH_SHORT).show()
            } else {
                val editor = sharedpreferences.edit()

                editor.putString(NAME_KEY, usrnameEdit.text.toString())
                editor.putString(PASSWORD_KEY, passwordEdit.text.toString())
                editor.apply()

                // starting new activity.
                val i = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }
        registerBut.setOnClickListener {
            val i = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }

    }
    override fun onStart() {
        super.onStart()
        if (usrname != null && password != null) {
            val i = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(i)
        }
    }

}