package kulloveth.developer.com.braintest.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kulloveth.developer.com.braintest.data.models.Quiz
import kulloveth.developer.com.braintest.data.models.QuizDao

@Database(entities = [Quiz::class], version = 1)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao

    companion object {

        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(application: Application): QuizDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(application).also { INSTANCE = it }
            }

        private fun buildDatabase(application: Application) =
            Room.databaseBuilder(
                application,
                QuizDatabase::class.java, "quiz-db"
            )
                .build()
    }
}