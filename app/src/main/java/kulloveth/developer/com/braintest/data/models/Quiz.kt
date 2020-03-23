package kulloveth.developer.com.braintest.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    val id: Int,
    @ColumnInfo(name = "questions_table")
    @SerializedName("questions")
    val questions: List<Questions>
) : Serializable