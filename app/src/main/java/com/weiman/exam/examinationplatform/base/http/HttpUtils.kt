package com.weiman.exam.examinationplatform.base.http

import com.google.gson.FieldNamingPolicy
import com.google.gson.FieldNamingStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Field
import java.util.concurrent.TimeUnit

/**
 * 创建 by 刘宇飞 on 2017/7/15.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class HttpUtils private constructor(){

    private var gson: Gson? = null
    @Volatile private var httpTask: Any? = null
    //构建单例模式
    companion object{
        fun getInstance()=HttpHolder.instance
        val LOGIN:String="fdfdfd"
    }
    private object HttpHolder{
        var instance=HttpUtils()
    }

    private fun getBuilder(apiUrl: String): Retrofit.Builder {
        val builder = Retrofit.Builder()
        builder.client(getOkClient())
        builder.baseUrl(apiUrl)//设置远程地址
        builder.addConverterFactory(NullOnEmptyConverterFactory())
        builder.addConverterFactory(GsonConverterFactory.create(getGson()))
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        return builder
    }

    private fun getOkClient(): OkHttpClient {
        //使用OkHttp拦截器可以指定需要的header给每一个Http请求
        val client = OkHttpClient().newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(LoggingInterceptor())//日志
                //            .addInterceptor(new NotEdcodeLoggingInterceptor())//不加密
                //   .addNetworkInterceptor(new RequestHeaderInterceptor())//请求头
                .build()
        return client
    }

    private fun getGson(): Gson? {
        if (gson == null) {
            synchronized(HttpUtils::class.java) {
                if (gson == null) {
                    val builder = GsonBuilder()
                    builder.setLenient()
                    builder.setFieldNamingStrategy(AnnotateNaming())
                    builder.serializeNulls()
                    gson = builder.create()
                }
            }
        }
        return gson
    }

    private class AnnotateNaming : FieldNamingStrategy {
        override fun translateName(f: Field?): String {
            val a = f?.getAnnotation(ParamNames::class.java)
            return a?.value ?: FieldNamingPolicy.IDENTITY.translateName(f)
        }

    }

    /**
     * 一般请求
     * @param a
     * *
     * @param <T>
     * *
     * @return
    </T> */
    fun <T> createRequest(a: Class<T>): T {
        if (httpTask == null) {
            synchronized(HttpUtils::class.java) {
                if (httpTask == null) {
                    httpTask = getBuilder(Api.HOST_URL).build().create(a)
                }
            }
        }
        return httpTask as T
    }

}