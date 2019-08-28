package tech.ntic.view.activities

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_sign_up.*
import tech.ntic.App
import tech.ntic.R
import tech.ntic.utils.AppConstants
import tech.ntic.view.base.BaseActivity
import tech.ntic.viewModel.SignUpViewModel
import tech.ntic.viewModel.ViewModelFactory

class SignUpActivity : BaseActivity(), View.OnClickListener {


    override fun onClick(view: View?) {
        when (view) {
            SignUp -> {
                addUserAccount()
            }
        }
    }

    private lateinit var viewModel: SignUpViewModel

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SignUpActivity::class.java)
        }
    }

    override fun initBeforeView() {
        with(applicationContext as App) {
            viewModel = ViewModelProviders.of(this@SignUpActivity, ViewModelFactory(this)).get(
                SignUpViewModel::class.java
            )
        }
    }

    override fun getContentViewId(): Int = R.layout.activity_sign_up

    override fun initViews() {
        initData()
        initListener()
    }

    private fun initListener() {
        SignUp.setOnClickListener(this)
    }

    private fun addUserAccount() {
        val constants = AppConstants(this)
        constants.saveString(AppConstants.USERNAME, Username.text.toString())
        constants.saveString(AppConstants.EMAIL, Email.text.toString())
        constants.saveString(AppConstants.PASSWORD, Password.text.toString())
        changeActivity()
    }

    private fun changeActivity() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    private fun initData(){
        if (intent.getStringExtra(AppConstants.EMAIL) != null
            && intent.getStringExtra(AppConstants.USERNAME) != null){
            Username.setText(intent.getStringExtra(AppConstants.USERNAME))
            Email.setText(intent.getStringExtra(AppConstants.EMAIL))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}
