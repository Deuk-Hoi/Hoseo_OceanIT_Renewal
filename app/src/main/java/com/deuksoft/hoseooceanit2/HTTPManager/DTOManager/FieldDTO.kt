package com.deuksoft.hoseooceanit2.HTTPManager.DTOManager

import com.google.gson.annotations.SerializedName

data class FieldDTO(
    @SerializedName("rrid")
    var rrid: String,
    @SerializedName("classify_ko")
    var classify_ko: String,
    @SerializedName("research_name_ko")
    var research_name_ko: String,
    @SerializedName("business_name_ko")
    var business_name_ko: String,
    @SerializedName("department_name_ko")
    var department_name_ko: String,
    @SerializedName("subjectivity_agency_ko")
    var subjectivity_agency_ko: String,
    @SerializedName("support_agency_ko")
    var support_agency_ko: String,
    @SerializedName("participation_agency_ko")
    var participation_agency_ko: String,
    @SerializedName("research_goal_ko")
    var research_goal_ko: String,
    @SerializedName("research_content_ko")
    var research_content_ko: String,
    @SerializedName("expectation_result_ko")
    var expectation_result_ko: String,
    @SerializedName("research_manager_ko")
    var research_manager_ko: String,
    @SerializedName("research_belong_ko")
    var research_belong_ko: String,
    @SerializedName("date_start")
    var date_start: String,
    @SerializedName("date_end")
    var date_end: String
)
