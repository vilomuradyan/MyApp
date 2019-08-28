package tech.ntic.view.activities

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_connected.*
import tech.ntic.App
import tech.ntic.R
import tech.ntic.view.base.BaseActivity
import tech.ntic.viewModel.ConnectedViewModel
import tech.ntic.viewModel.ViewModelFactory

class ConnectedActivity : BaseActivity(), View.OnClickListener {


    override fun onClick(view: View?) {
        when(view){
            Close -> {
                chnageActivity()
            }
        }
    }

    private lateinit var viewModel: ConnectedViewModel

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ConnectedActivity::class.java)
        }
    }

    override fun initBeforeView() {
        with(applicationContext as App) {
            viewModel = ViewModelProviders.of(this@ConnectedActivity, ViewModelFactory(this)).get(
                ConnectedViewModel::class.java
            )
        }
    }

    override fun getContentViewId(): Int = R.layout.activity_connected

    override fun initViews() {
        initListener()
    }

    private fun chnageActivity(){
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    private fun initListener(){
        Close.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}
