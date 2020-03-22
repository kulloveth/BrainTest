package kulloveth.developer.com.braintest.data.models

import java.io.Serializable

data class Questions(
    val answerList:List<Answers>,
    val _id:String,
    val question:String
):Serializable