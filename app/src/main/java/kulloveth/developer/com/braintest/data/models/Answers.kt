package kulloveth.developer.com.braintest.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Answers(
    val _id:Int,
    @SerializedName("option") val option: String,
    @SerializedName("value") val value: Boolean
) : Parcelable