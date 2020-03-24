package kulloveth.developer.com.braintest.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz(

    val id: String,

    @SerializedName("questions")
    val questions: List<Questions>
) : Parcelable