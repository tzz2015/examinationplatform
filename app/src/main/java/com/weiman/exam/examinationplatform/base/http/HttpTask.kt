package com.weiman.exam.examinationplatform.base.http

import com.weiman.exam.examinationplatform.account.bean.LoginInputBean
import com.weiman.exam.examinationplatform.account.bean.UserInfoBean
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**

 * 刘宇飞 创建 on 2017/5/20.
 * 描述：
 */

interface HttpTask {
    /**
     * 登录
     */
    @POST("auth/login")
    fun requestLogin(@Body loginInputBean: LoginInputBean): Observable<BaseResponse<UserInfoBean>>

    /**
     * 注册
     */
    @POST("auth/register")
    fun requestRegister(@Body loginInputBean: LoginInputBean): Observable<BaseResponse<Any>>

    /**
     * 发送重置密码的验证码的邮件
     */
    @POST("auth/reset/send")
    fun requestCode(@Body loginInputBean: LoginInputBean): Observable<BaseResponse<Any>>

    /**
     * 重置密码
     */
    @POST("auth/reset/password")
    fun postResetPsw(@Body loginInputBean: LoginInputBean): Observable<BaseResponse<Any>>

}
