package com.weiman.exam.examinationplatform.base.http

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


/**
 * 创建 by 刘宇飞 on 2017/7/15.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *> {
        val delegate = retrofit?.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter<ResponseBody, Any> { value ->
            if (value.contentLength()==0L)
                return@Converter null
           return@Converter delegate?.convert(value)
        }
    }
}