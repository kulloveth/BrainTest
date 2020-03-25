package kulloveth.developer.com.braintest.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Questions(
    @SerializedName("answers") val answers: ArrayList<Answers>,
    @SerializedName("_id") val _id: String,
    @SerializedName("question") val question: String
    ) : Parcelable