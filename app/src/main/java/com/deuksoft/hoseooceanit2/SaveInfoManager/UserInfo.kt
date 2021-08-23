package com.deuksoft.hoseooceanit2.SaveInfoManager

import android.content.Context
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.UserInfoDTO
import com.google.gson.annotations.SerializedName

class UserInfo(context: Context) {
    var userInfoData = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
    var editor = userInfoData.edit()

    /*
    * 사용자 정보를 저장하고 읽고 삭제하는 곳이다. 사용자가 로그인을 하면 정보를 저장하고, 로그아웃을 하게 되면 제거를 한다.
    * */
    fun setUserInfo(userInfoDTO: UserInfoDTO):Boolean{
        editor.apply {
            putString(USER_ID, userInfoDTO.userId).apply()
            putString(USER_NAME, userInfoDTO.userName).apply()
            putString(USER_EMAIL, userInfoDTO.userEmail).apply()
            putString(USER_NAME_EN, userInfoDTO.userNameEN).apply()
            putString(USER_BELONG, userInfoDTO.userBelong).apply()
            putString(USER_DEPARTMENT, userInfoDTO.userDepartment).apply()
            putString(USER_POSITION, userInfoDTO.userPosition).apply()
            putString(USER_ADD, userInfoDTO.userAdd).apply()
            putString(USER_PHONE, userInfoDTO.userPhone).apply()
            putString(USER_IMAGE, userInfoDTO.userImg).apply()
            putString(TOKEN, userInfoDTO.Token).apply()
        }
        return editor.commit()
    }

    fun getUserInfo():HashMap<String, String>{
        return hashMapOf(
            USER_ID to userInfoData.getString(USER_ID, "")!!.toString(),
            USER_EMAIL to userInfoData.getString(USER_EMAIL, "")!!.toString(),
            USER_NAME to userInfoData.getString(USER_NAME, "")!!.toString(),
            USER_NAME_EN to userInfoData.getString(USER_NAME_EN, "")!!.toString(),
            USER_BELONG to userInfoData.getString(USER_BELONG, "")!!.toString(),
            USER_DEPARTMENT to userInfoData.getString(USER_DEPARTMENT, "")!!.toString(),
            USER_POSITION to userInfoData.getString(USER_POSITION, "")!!.toString(),
            USER_ADD to userInfoData.getString(USER_ADD, "")!!.toString(),
            USER_PHONE to userInfoData.getString(USER_PHONE, "")!!.toString(),
            USER_IMAGE to userInfoData.getString(USER_IMAGE, "")!!.toString(),
            TOKEN to userInfoData.getString(TOKEN, "")!!.toString(),
        )
    }

    fun removeUserInfo(){
        editor.clear().apply()
    }

    companion object{
        const val USER_ID = "USER_ID"
        const val USER_EMAIL = "USER_EMAIL"
        const val USER_NAME = "USER_NAME"
        const val USER_NAME_EN = "USER_NAME_EN"
        const val USER_BELONG = "USER_BELONG"
        const val USER_DEPARTMENT = "USER_DEPARTMENT"
        const val USER_POSITION = "USER_POSITION"
        const val USER_ADD = "USER_ADD"
        const val USER_PHONE = "USER_PHONE"
        const val USER_IMAGE = "USER_IMAGE"
        const val TOKEN = "TOKEN"
    }
}