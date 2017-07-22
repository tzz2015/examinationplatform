package com.weiman.exam.examinationplatform.account.bean

import com.weiman.exam.examinationplatform.App.Companion.context
import com.weiman.exam.examinationplatform.utils.LogUtil
import com.weiman.exam.examinationplatform.utils.SharedPreUtil

/**
 * 创建 by 刘宇飞 on 2017/7/15.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class UserInfoBean private constructor() {

    var token: String = ""
    var id: Long = 0
    var name: String = ""
    var email: String = ""
    var type: String = ""
    var img: String = ""

    companion object {
        private @Volatile var userInfoBean: UserInfoBean? = null
        /**
         * 获取单例
         */
        fun getInstance(): UserInfoBean {
            if (userInfoBean == null) {
                synchronized(this) {
                    if (userInfoBean == null) {
                        userInfoBean = UserInfoBean()
                    }
                }
            }
            return userInfoBean!!
        }
    }

    /**
     * 存储用户数据到本地
     */
    fun saveAccount() {
        SharedPreUtil.saveLong(context, "id", id)
        SharedPreUtil.saveString(context, id.toString() + "token", token)
        SharedPreUtil.saveString(context, id.toString() + "name", name)
        SharedPreUtil.saveString(context, id.toString() + "email", email)
        SharedPreUtil.saveString(context, id.toString() + "type", type)
        SharedPreUtil.saveString(context, id.toString() + "img", img)
    }

    fun clearAccount() {
        SharedPreUtil.clearByKey(context, getUserId().toString() + "token")
        SharedPreUtil.clearByKey(context, getUserId().toString() + "name")
        SharedPreUtil.clearByKey(context, getUserId().toString() + "email")
        SharedPreUtil.clearByKey(context, getUserId().toString() + "type")
        SharedPreUtil.clearByKey(context, getUserId().toString() + "img")
        SharedPreUtil.clearByKey(context, "id")
    }

    fun getUserId(): Long {
        return SharedPreUtil.getLong(context, "id", 0);
    }

    fun getUserToken(): String {
        LogUtil.getInstance().e(SharedPreUtil.getString(context, getUserId().toString() + "token", ""))
        return SharedPreUtil.getString(context, getUserId().toString() + "token", "")
    }

    fun getUserName(): String {
        return SharedPreUtil.getString(context, getUserId().toString() + "name", "")
    }

    fun getUserEmil(): String {
        return SharedPreUtil.getString(context, getUserId().toString() + "email", "")
    }

    fun getUserType(): String {
        return SharedPreUtil.getString(context, getUserId().toString() + "type", "")
    }

    fun getUserImg(): String {
        return SharedPreUtil.getString(context, getUserId().toString() + "img", "")
    }


}