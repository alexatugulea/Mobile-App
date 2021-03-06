package com.example.instagram1805

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignInActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)



        clickOnLoginBtn()
        clickOnSignUpBtn()

    }

    override fun onStart() {
        super.onStart()

        if(FirebaseAuth.getInstance().currentUser != null)
        {
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    private fun loginUser()
    {
        val email = email_login.text.toString()
        val password = password_login.text.toString()

        when
        {
            email.isEmpty() -> Toast.makeText(this, "email is required.", Toast.LENGTH_LONG).show()
            password.isEmpty() -> Toast.makeText(this, "password is required.", Toast.LENGTH_LONG).show()

            else ->
            {
                val customProgressDialog = CustomProgressDialog.setProgressDialog(this, "Loading..")
                customProgressDialog.show()

                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        customProgressDialog.dismiss()

                        val intent = Intent(this@SignInActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        val message = task.exception!!.toString()
                        Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
                        FirebaseAuth.getInstance().signOut()
                        customProgressDialog.dismiss()
                    }
                }
            }
        }
    }

    //Actions
    private fun clickOnSignUpBtn(){
        signup_link_btn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun clickOnLoginBtn(){
        login_btn.setOnClickListener {
            loginUser()
        }
    }


}
