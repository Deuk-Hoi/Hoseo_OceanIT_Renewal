package com.deuksoft.hoseooceanit2.HTTPManager.DTOManager

import com.google.gson.annotations.SerializedName

data class LatelyFieldDTO(
    @SerializedName("detailData")
    var detailData : List<FieldDetail>,

    @SerializedName("photoData")
    var photoData : List<String>
)

data class FieldDetail(
    @SerializedName("rfid")
    var rfid : Int,

    @SerializedName("research_content_ko")
    var research_content_ko : String,

    @SerializedName("research_name_ko")
    var research_name_ko : String,


)
