package kulloveth.developer.com.braintest.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    @SerializedName("answers") val answers: ArrayList<Answer>,
    @SerializedName("_id") val _id: String,
    @SerializedName("question") val question: String
    ) : Parcelable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Question) return false

        if (answers != other.answers) return false
        if (_id != other._id) return false
        if (question != other.question) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}