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

}
