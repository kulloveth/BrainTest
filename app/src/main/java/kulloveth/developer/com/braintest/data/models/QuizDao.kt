package kulloveth.developer.com.braintest.data.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface QuizDao {


    @Query("SELECT * FROM Quiz WHERE question_id = :id")
    fun getQuizById(id: String): Flowable<Quiz>

    @Query("SELECT * FROM Quiz ")
    fun getQuiz(): Flowable<List<Quiz>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuiz(quiz: Quiz): Completable

    @Query("DELETE FROM Quiz")
    fun deleteAllQuiz()
}