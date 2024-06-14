package com.example.pokegnomego

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pokegnomego.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val NAME_KEY = "name_key"
        const val PASSWORD_KEY = "password_key"
    }
    private lateinit var sharedpreferences: SharedPreferences
    private var usrname: String? = null
    private var password: String? = null
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usrnameEdit = findViewById<EditText>(R.id.usrname)
        val passwordEdit = findViewById<EditText>(R.id.passwd)
        val registerBut = findViewById<Button>(R.id.register_but)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        usrname = sharedpreferences.getString("NAME_KEY", null)
        password = sharedpreferences.getString("PASSWORD_KEY", null)


        registerBut.setOnClickListener {
            if (TextUtils.isEmpty(usrnameEdit.text.toString()) && TextUtils.isEmpty(passwordEdit.text.toString())) {
                Toast.makeText(this@RegisterActivity, "Wpisz username i hasło", Toast.LENGTH_SHORT).show()
            } else {
                val editor = sharedpreferences.edit()

                editor.putString(NAME_KEY, usrnameEdit.text.toString())
                editor.putString(PASSWORD_KEY, passwordEdit.text.toString())
                editor.apply()

                // starting new activity.
                val i = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }

    }
}