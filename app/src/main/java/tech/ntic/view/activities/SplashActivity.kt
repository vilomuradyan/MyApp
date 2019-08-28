package tech.ntic.view.activities

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import tech.ntic.App
import tech.ntic.R
import tech.ntic.utils.AppConstants
import tech.ntic.view.base.BaseActivity
import tech.ntic.viewModel.SplashViewModel
import tech.ntic.viewModel.ViewModelFactory
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    private val destroyCompositeDisposable = CompositeDisposable()
    private lateinit var viewModel: SplashViewModel

    override fun initBeforeView() {
        with(applicationContext as App){
            viewModel = androidx.lifecycle.ViewModelProviders.of(this@SplashActivity , ViewModelFactory(this)).get(
                SplashViewModel::class.java)
        }
    }

    override fun getContentViewId(): Int = R.layout.activity_splash
    override fun initViews() {


        destroyCompositeDisposable.add(
            Completable.timer(2000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        val constant = AppConstants(this)
                        if (constant.getBoolean(AppConstants.STATUS) == false){
                            constant.saveBoolean(AppConstants.STATUS, true)
                            showSignUp()
                        }else {
                            showLoginActivity()
                        }
                    },
                    {}
                ))

    }

    private fun showSignUp(){
        val intent = SignUpActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    private fun showLoginActivity(){
        val intent = LoginActivity.newIntent(this)
        startActivity(intent)
        finish()
    }


    override fun onDestroy() {
        destroyCompositeDisposable.dispose()
        viewModel.dispose()
        super.onDestroy()
    }
}
