package kulloveth.developer.com.braintest.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Answers(
    @SerializedName("option") val option: String,
    @SerializedName("value") val value: Boolean
) : Serializable