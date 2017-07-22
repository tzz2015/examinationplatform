package com.weiman.exam.examinationplatform.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.util.*

object SharedPreUtil {

    private val FILE_NAME = "config"
    @Volatile private var sp: SharedPreferences? = null

    /**
     * 获取布局型变量的值

     * @param context
     * *
     * @param key
     * *
     * @param defValue 获取不到时，给定的默认的值
     * *
     * @return
     */
    fun getBoolean(context: Context, key: String, defValue: Boolean): Boolean {

        return getSP(context).getBoolean(key, defValue)
    }

    /**
     * 保存boolean变量
     */
    fun saveBoolean(context: Context, key: String, value: Boolean) {
        getSP(context).edit().putBoolean(key, value).apply()
    }


    /**
     * 获取字符串型变量的值

     * @param context
     * *
     * @param key
     * *
     * @param defValue 获取不到时，给定的默认的值
     * *
     * @return
     */
    fun getString(context: Context, key: String, defValue: String): String {

        return getSP(context).getString(key, defValue)
    }

    /**
     * 保存字符串变量
     */
    fun saveString(context: Context, key: String, value: String) {
        getSP(context).edit().putString(key, value).apply()
    }

    /**
     * 获取整型变量的值

     * @param context
     * *
     * @param key
     * *
     * @param defValue 获取不到时，给定的默认的值
     * *
     * @return
     */
    fun getInt(context: Context, key: String, defValue: Int): Int {
        return getSP(context).getInt(key, defValue)
    }


    /**
     * 保存整型变量
     */
    fun saveInt(context: Context, key: String, value: Int) {
        getSP(context).edit().putInt(key, value).apply()
    }

    /**
     * 获取Long变量的值

     * @param context
     * *
     * @param key
     * *
     * @param defValue 获取不到时，给定的默认的值
     * *
     * @return
     */
    fun getLong(context: Context, key: String, defValue: Long): Long {
        return getSP(context).getLong(key, defValue)
    }

    /**
     * 保存Long变量
     */
    fun saveLong(context: Context, key: String, value: Long) {
        getSP(context).edit().putLong(key, value).apply()
    }

    /**
     * 11.     * 保存List
     * 12.     * @param tag
     * 13.     * @param datalist
     * 14.
     */
    fun <T> setDataList(context: Context, tag: String, datalist: List<T>?) {
        val editor = getSP(context).edit()

        if (null == datalist || datalist.isEmpty())
            return

        val gson = Gson()
        //转换成json数据，再保存
        val strJson = gson.toJson(datalist)
        editor.putString(tag, strJson)
        editor.apply()

    }

    /**
     * 获取List

     * @param tag
     * *
     * @return
     */
    fun <T> getDataList(context: Context, tag: String): List<T> {
        var datalist: List<T> = ArrayList()
        val strJson = getSP(context).getString(tag, null) ?: return datalist
        val gson = Gson()
        datalist = gson.fromJson<List<T>>(strJson, object : TypeToken<List<T>>() {

        }.type)
        return datalist

    }

    /**
     * 获取List

     * @param tag
     * *
     * @return
     */
    fun <T> getDataList(context: Context, tag: String, cls: Class<T>): List<T> {
        val strJson = getSP(context).getString(tag, null)
        val list = ArrayList<T>()
        try {
            val gson = Gson()
            val arry = JsonParser().parse(strJson).asJsonArray
            for (jsonElement in arry) {
                list.add(gson.fromJson(jsonElement, cls))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return list
    }

    /**
     * 清空sp

     * @param context
     */
    fun clearAll(context: Context) {
        getSP(context).edit().clear().apply()
    }

    /**
     * 清空对应的key
     */
    fun clearByKey(context: Context, key: String) {
        getSP(context).edit().remove(key).apply()
    }

    /**
     * 获取sp实例
     */
    private fun getSP(context: Context): SharedPreferences {
        if (sp == null) {
            synchronized(SharedPreUtil::class.java) {
                if (sp == null) {
                    sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
                }
            }
        }
        return sp!!
    }


}
