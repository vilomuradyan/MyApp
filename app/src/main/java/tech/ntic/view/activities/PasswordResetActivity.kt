package tech.ntic.view.activities

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_password_reset.*
import tech.ntic.App
import tech.ntic.R
import tech.ntic.utils.AppConstants
import tech.ntic.view.base.BaseActivity
import tech.ntic.viewModel.PasswordResetViewModel
import tech.ntic.viewModel.ViewModelFactory

class PasswordResetActivity : BaseActivity(), View.OnClickListener {


    override fun onClick(view: View?) {
        when(view){
            BtnReset -> {
                checkedEmail()
            }
        }
    }

    private lateinit var viewModel: PasswordResetViewModel

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, PasswordResetActivity::class.java)
        }
    }

    override fun initBeforeView() {
        with(applicationContext as App) {
            viewModel = ViewModelProviders.of(this@PasswordResetActivity, ViewModelFactory(this)).get(
                PasswordResetViewModel::class.java
            )
        }
    }

    private fun initListener(){
        BtnReset.setOnClickListener(this)
    }

    private fun checkedEmail(){
        val constant = AppConstants(this)
        if (Email.text.toString() == constant.getString(AppConstants.EMAIL)){
            showSignUpActivity()
        }else {
            Toast.makeText(this, "wrong Email", Toast.LENGTH_LONG).show()
        }
    }

    private fun showSignUpActivity(){
        val constants = AppConstants(this)
        val intent = SignUpActivity.newIntent(this)
        intent.putExtra(AppConstants.USERNAME, constants.getString(AppConstants.USERNAME))
        intent.putExtra(AppConstants.EMAIL, constants.getString(AppConstants.EMAIL))
        startActivity(intent)
        finish()
    }

    override fun getContentViewId(): Int = R.layout.activity_password_reset

    override fun initViews() {
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}
