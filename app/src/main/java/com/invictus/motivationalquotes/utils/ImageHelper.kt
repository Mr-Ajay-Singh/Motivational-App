package com.invictus.motivationalquotes.utils

import android.graphics.Bitmap
import splitties.init.appCtx
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

object ImageHelper {

    fun convertBitmapToFile(bitmap: Bitmap, fileName: String): File? {
        var file: File? = null
        return try {
            file = File(appCtx.cacheDir, fileName)
            file.createNewFile()

            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bos) // YOU can also save it in JPEG
            val bitmapdata = bos.toByteArray()

            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file
        }
    }

}
