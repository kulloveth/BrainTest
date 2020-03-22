package kulloveth.developer.com.braintest.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Quiz(
    @SerializedName("questions")
    val questions: List<Questions>
):Serializable