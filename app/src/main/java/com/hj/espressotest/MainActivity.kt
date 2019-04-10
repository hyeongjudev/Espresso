package com.hj.espressotest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val isAllInfoInputted: Boolean
        get() = etUserID.text.toString().isNotEmpty()
                && etPassword.text.toString().isNotEmpty()

    private val mPresenter: MainContract.Presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {
        etUserID.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
        btnLogin.setOnClickListener {
            mPresenter.login(etUserID.text.toString(), etPassword.text.toString())
        }
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            btnLogin.isEnabled = isAllInfoInputted
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun showSuccess() {
        Toast.makeText(this, "Login Success",Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SuccessActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        this.finish()

    }

    override fun showFail() {
        Toast.makeText(this, "Login Fail",Toast.LENGTH_SHORT).show()
    }
}
