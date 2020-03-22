package kulloveth.developer.com.braintest.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Questions(
    @SerializedName("answers") val answers: List<Answers>,
    @SerializedName("_id") val _id: String,
    @SerializedName("question") val question: String
    ) : Serializable