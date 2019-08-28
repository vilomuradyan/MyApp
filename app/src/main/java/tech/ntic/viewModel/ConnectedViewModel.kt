package tech.ntic.viewModel

import androidx.lifecycle.ViewModel
import com.example.ntictask.di.component.DIComponent
import io.reactivex.disposables.CompositeDisposable
import tech.ntic.data.repository.RepositoryHelper
import javax.inject.Inject

class ConnectedViewModel : ViewModel(), DIComponent.Injectable {

    @Inject
    lateinit var repositoryHelper: RepositoryHelper
    @Inject
    lateinit var composite: CompositeDisposable

    override fun inject(diComponent: DIComponent) {
        diComponent.inject(this)
    }

    fun dispose(){
        composite.clear()
    }
}