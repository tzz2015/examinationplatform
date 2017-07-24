package com.weiman.exam.examinationplatform.view.photo

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.Window

import com.jph.takephoto.app.TakePhotoActivity
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.CropOptions
import com.jph.takephoto.model.LubanOptions
import com.jph.takephoto.model.TResult
import com.jph.takephoto.model.TakePhotoOptions
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.utils.AutoUtils
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.StatusBarUtil

import java.io.File

class CameraActivity : TakePhotoActivity() {
    /**
     * 是否裁剪 默认不裁剪
     */
    private var isCrop: Boolean = false
    /**
     * 最大相片数
     */
    private var maxSize: Int = 0
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_camera)
        AutoUtils.autoView(this)
        // 设置透明状态栏
        StatusBarUtil.setColor(this, CommonUtils.getColor(this, R.color.colorTitle), 0)
        initView()
    }

    private fun initView() {
        maxSize = intent.getIntExtra("maxSize", 1)
        isCrop = intent.getBooleanExtra("isCrop", false)
        initOption()
        //拍摄
        findViewById(R.id.tv_take_camera).setOnClickListener {
            if (isCrop) {
                takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions)
            } else {
                takePhoto.onPickFromCapture(imageUri)
            }
            // finish();
        }
        //相册中选取
        findViewById(R.id.tv_take_photo).setOnClickListener(View.OnClickListener {
            if (maxSize > 1) {
                if (isCrop) {
                    takePhoto.onPickMultipleWithCrop(maxSize, cropOptions)
                } else {
                    takePhoto.onPickMultiple(maxSize)
                }
                return@OnClickListener
            }

            if (isCrop) {
                takePhoto.onPickFromDocumentsWithCrop(imageUri, cropOptions)
            } else {
                takePhoto.onPickFromDocuments()
            }
        })
        findViewById(R.id.tv_cancel).setOnClickListener { finish() }
    }

    /**
     * 初始化参数
     */
    private fun initOption() {
        val file = File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        imageUri = Uri.fromFile(file)
        val builder = TakePhotoOptions.Builder()
        builder.setWithOwnGallery(true)//使用TakePhoto自带相册
        builder.setCorrectImage(true)//纠正拍照的照片旋转角度
        takePhoto.setTakePhotoOptions(builder.create())
        //压缩设置
        val option = LubanOptions.Builder()
                .setMaxHeight(800)
                .setMaxWidth(480)
                .setMaxSize(maxSize)
                .create()
        val config = CompressConfig.ofLuban(option)
        config.enableReserveRaw(true)//是否保存原图
        takePhoto.onEnableCompress(config, true)
    }

    /**
     * 裁剪参数设置
     */
    private val cropOptions: CropOptions
        get() {
            val builder = CropOptions.Builder()
            builder.setAspectX(800).setAspectY(800)
            builder.setWithOwnCrop(true)
            return builder.create()
        }


    override fun takeCancel() {
        super.takeCancel()
        finish()
    }

    override fun takeFail(result: TResult?, msg: String) {
        super.takeFail(result, msg)
        finish()
        CommonUtils.showToast(this, msg)

    }

    override fun takeSuccess(result: TResult) {
        super.takeSuccess(result)

        if (PhotoModel.callback != null) {
            runOnUiThread { PhotoModel.callback.onHanlderSuccess(result.images) }

        }
        finish()
    }

    companion object {
        var tempFile1 = Environment.getExternalStorageDirectory().toString() + "/images/"
        var tempFile2 = Environment.getExternalStorageDirectory().toString() + "/temp/"
    }


}
