package tech.ntic.view.activities

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import tech.ntic.App
import tech.ntic.R
import tech.ntic.utils.AppConstants
import tech.ntic.view.base.BaseActivity
import tech.ntic.viewModel.LoginViewModel
import tech.ntic.viewModel.ViewModelFactory


class LoginActivity : BaseActivity(), View.OnClickListener {


    override fun onClick(view: View?) {
        when(view){
            BtnLogin -> {
                checked()
            }
            TextForgot -> {
                showPasswordForgotActivity()
            }
        }
    }

    private lateinit var viewModel: LoginViewModel

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun initBeforeView() {
        with(applicationContext as App) {
            viewModel = ViewModelProviders.of(this@LoginActivity, ViewModelFactory(this)).get(
                LoginViewModel::class.java
            )
        }
    }

    private fun initListener(){
        BtnLogin.setOnClickListener(this)
        TextForgot.setOnClickListener(this)
    }

    private fun showPasswordForgotActivity(){
        startActivity(PasswordResetActivity.newIntent(this))
        finish()
    }

    override fun getContentViewId(): Int = R.layout.activity_login

    override fun initViews() {
        initListener()
    }

    private fun checked(){
        val constants = AppConstants(this)
        if (Username.text.toString() == constants.getString(AppConstants.USERNAME)
            && Password.text.toString() == constants.getString(AppConstants.PASSWORD)){
            changeActivity()
        }else {
            Toast.makeText(this, "wrong username or password", Toast.LENGTH_LONG).show()
        }
    }

    private fun changeActivity(){
        startActivity(ConnectedActivity.newIntent(this))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}
