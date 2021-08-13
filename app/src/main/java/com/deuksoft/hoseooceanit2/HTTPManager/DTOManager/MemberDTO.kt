package com.deuksoft.hoseooceanit2.HTTPManager.DTOManager

import com.google.gson.annotations.SerializedName

data class MemberDTO(
    @SerializedName("name_ko")
    var name_ko: String,
    @SerializedName("department_ko")
    var department_ko: String,
    @SerializedName("position_ko")
    var position_ko: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("photo")
    var photo: String
)
