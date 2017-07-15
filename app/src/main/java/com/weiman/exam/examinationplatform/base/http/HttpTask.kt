package com.weiman.exam.examinationplatform.base.http

import com.weiman.exam.examinationplatform.account.bean.UserInfoBean
import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable
import java.util.*

/**

 * 刘宇飞 创建 on 2017/5/20.
 * 描述：
 */

interface HttpTask {
    /**
     * 登录
     */
    @GET("user/loginsms")
    fun requestLogin(@QueryMap pram: HashMap<String, Any>): Observable<BaseResponse<UserInfoBean>>
}
