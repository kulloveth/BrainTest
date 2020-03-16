package kulloveth.developer.com.braintest.data.repository

import kulloveth.developer.com.braintest.data.FirebaseSource

class UserRepository(private val firebaseSource: FirebaseSource) {

    fun signInUser(email: String, password: String) = firebaseSource.signIn(email, password)

    fun signupUser(email: String, password: String) = firebaseSource.signUp(email, password)

    fun signOutUser() = firebaseSource.signOut()

    fun currentUser() = firebaseSource.currenUser()
}