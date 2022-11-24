package be.bxl.will.correctionrecyclerviewmediatheque

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnSignIn : Button
    private lateinit var btnSignUp : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        setupButton()
    }

    private fun setupButton() {
        btnSignUp.setOnClickListener {
            val sharedPref = getSharedPreferences("shared_pref", MODE_PRIVATE)
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            with(sharedPref.edit()) {
                putString(email, password)
                apply()
            }
        }

        btnSignIn.setOnClickListener {
            val sharedPref = getSharedPreferences("shared_pref", MODE_PRIVATE)
            val password = sharedPref.getString(etEmail.text.toString(), null)
            if (password != null && password == etPassword.text.toString()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", etEmail.text.toString())
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Combinaison incorret", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initView() {
        etEmail = findViewById(R.id.et_email_login)
        etPassword = findViewById(R.id.et_password_login)
        btnSignIn = findViewById(R.id.btn_sign_in_login)
        btnSignUp = findViewById(R.id.btn_sign_up_login)
    }
}