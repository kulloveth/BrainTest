package kulloveth.developer.com.braintest.data.repository

import kulloveth.developer.com.braintest.data.FirebaseSource

class UserRepository {

    fun signInUser(email: String, password: String) = FirebaseSource.signIn(email, password)

    fun signupUser(email: String, password: String) = FirebaseSource.signUp(email, password)

    fun signOutUser() = FirebaseSource.signOut()

    fun currentUser() = FirebaseSource.currenUser()
}