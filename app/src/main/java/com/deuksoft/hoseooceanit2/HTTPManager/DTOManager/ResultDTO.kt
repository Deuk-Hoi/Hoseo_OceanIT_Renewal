package com.deuksoft.hoseooceanit2.HTTPManager.DTOManager

import com.google.gson.annotations.SerializedName

data class ResultDTO(
    @SerializedName("rrid")
    var rrid : String,
    @SerializedName("group")
    var group: String,
    @SerializedName("classify_ko")
    var classify_ko : String,
    @SerializedName("title_ko")
    var title_ko : String,
    @SerializedName("academic_ko")
    var academic_ko : String,
    @SerializedName("writer_ko")
    var writer_ko : String,
    @SerializedName("media_ko")
    var media_ko : String,
    @SerializedName("announe_nation_ko")
    var announe_nation_ko : String,
    @SerializedName("relation_subject_ko")
    var relation_subject_ko : String,
    @SerializedName("abstract_ko")
    var abstract_ko : String,
    @SerializedName("date")
    var date : String,
    @SerializedName("application_num")
    var application_num : String
)