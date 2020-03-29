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
    ) : Parcelable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Questions) return false

        if (answers != other.answers) return false
        if (_id != other._id) return false
        if (question != other.question) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}