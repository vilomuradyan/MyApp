package tech.ntic.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ntictask.di.component.DIComponent
import io.reactivex.disposables.CompositeDisposable
import tech.ntic.data.database.entity.Profile
import tech.ntic.data.repository.RepositoryHelper
import tech.ntic.utils.iomain
import javax.inject.Inject

class PasswordResetViewModel : ViewModel(), DIComponent.Injectable {

    var profileResult: MutableLiveData<Profile> = MutableLiveData()
    var profileError : MutableLiveData<String> = MutableLiveData()

    @Inject
    lateinit var repositoryHelper: RepositoryHelper
    @Inject
    lateinit var composite: CompositeDisposable

    override fun inject(diComponent: DIComponent) {
        diComponent.inject(this)
    }

    fun profile() {
        composite.add(repositoryHelper.profile().iomain().subscribe(
            {
                profileResult.postValue(it)
            },
            {
                profileError.postValue(it.message)
            }
        ))
    }

    fun insert(profile: Profile){
        composite.add(repositoryHelper.insert(profile).iomain().subscribe(
            {
                profileResult.postValue(profile)
            },
            {
                profileError.postValue(it.message)
            }
        ))
    }

    fun dispose(){
        composite.clear()
    }
}