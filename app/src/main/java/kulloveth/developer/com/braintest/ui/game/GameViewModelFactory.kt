package kulloveth.developer.com.braintest.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kulloveth.developer.com.braintest.data.repository.UserRepository

class GameViewModelFactory(val repository: UserRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameViewModel(repository) as T
    }
}