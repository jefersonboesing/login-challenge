package com.example.tallerchallenge.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.tallerchallenge.feature.welcome.WelcomeActivity
import com.example.tallerchallenge_view.R
import com.example.tallerchallenge_view.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setupViews()
    }

    private fun setupObservers() {
        viewModel.isLoginEnabled.observe(this) {
            binding.btLogin.isEnabled = it
        }
        viewModel.navigateToWelcome.observe(this) { shouldNavigate ->
            if (shouldNavigate) navigateToWelcome()
        }
        viewModel.errorMessage.observe(this) {
            binding.txtError.text = it
        }
    }

    private fun setupViews() {
        binding.etUsername.doOnTextChanged { text, start, before, count ->
            viewModel.onUsernameChange(text.toString())
        }
        binding.etPassword.doOnTextChanged { text, start, before, count ->
            viewModel.onPasswordChange(text.toString())
        }
        binding.btLogin.setOnClickListener {
            viewModel.onLoginClick()
        }
    }

    private fun navigateToWelcome() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}