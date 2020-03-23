package kulloveth.developer.com.braintest.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Quiz(

    val id: String,

    @SerializedName("questions")
    val questions: List<Questions>
) : Serializable