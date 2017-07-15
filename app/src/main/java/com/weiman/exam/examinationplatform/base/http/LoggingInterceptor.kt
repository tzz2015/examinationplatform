/*
 * Mr.Mantou - On the importance of taste
 * Copyright (C) 2015  XiNGRZ <xxx@oxo.ooo>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.weiman.exam.examinationplatform.base.http


import com.weiman.exam.examinationplatform.utils.LogUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import java.io.IOException

class LoggingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        printRequest(request)


        val response = chain.proceed(request)
        val responseBody = response.body()
        val responseBodyString = response.body().string()
        val newResponse = response.newBuilder().body(ResponseBody.create(responseBody.contentType(), responseBodyString.toByteArray())).build()
        try {
            val logResult = String(responseBodyString.toCharArray())
            val indexOf = request.url().toString().lastIndexOf("/")
            LogUtil.getInstance().e("接口:" + request.url().toString().substring(indexOf + 1, request.url().toString().length) + "--返回数据:" + logResult)
        } catch (e: Exception) {

        }

        return newResponse

    }

    /**
     * 打印请求体
     * @param request
     */
    private fun printRequest(request: Request) {
        try {
            val result = String(bodyToString(request).toCharArray())
            LogUtil.getInstance().e("请求url:" + request.url() + "?" + result)

        } catch (e: Exception) {
            LogUtil.getInstance().e(e)
        }

    }


    private fun bodyToString(request: Request): String {

        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            if (copy != null && copy.body() != null) {
                copy.body().writeTo(buffer)
            } else {
                return ""
            }

            return buffer.readUtf8()
        } catch (e: IOException) {
            return ""
        }

    }

}
