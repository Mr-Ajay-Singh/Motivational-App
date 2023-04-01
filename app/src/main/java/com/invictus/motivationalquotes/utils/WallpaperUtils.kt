package com.invictus.motivationalquotes.utils

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.invictus.motivationalquotes.BuildConfig
import com.invictus.motivationalquotes.R
import com.invictus.motivationalquotes.db.MotivationSharedPreferences.context
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import splitties.init.appCtx
import splitties.toast.UnreliableToastApi
import splitties.toast.toast
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


fun setWallpaperUsingBitmap(context: Context,bitmap: Bitmap){
    GlobalScope.launch(Dispatchers.IO){
        try{

            val myWallpaperManager = WallpaperManager.getInstance(context)
            myWallpaperManager.setBitmap(bitmap)

        }catch (e: Exception){
            Timber.d("==>")
        }
    }
    toast(context.getString(R.string.wallpaper_set_successfully))

}

@OptIn(DelicateCoroutinesApi::class, UnreliableToastApi::class)
fun setWallpaper(context: Context, imageLink:String){
    GlobalScope.launch(Dispatchers.IO){
        try{
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageLink)
                .allowHardware(false) // Disable hardware bitmaps.
                .build()

            val result = (loader.execute(request) as SuccessResult).drawable
            val bitmap = (result as BitmapDrawable).bitmap
            val myWallpaperManager = WallpaperManager.getInstance(context)
            myWallpaperManager.setBitmap(bitmap)

        }catch (e: Exception){
            Timber.d("==>")
        }
    }
    toast(context.getString(R.string.wallpaper_set_successfully))

}

@OptIn(UnreliableToastApi::class)
fun downloadWallpaper(context: Context, imageLink: String){
    try{
        val fileNameCreation = imageLink.split("/")
        val filename = if(fileNameCreation.isNotEmpty()) fileNameCreation[fileNameCreation.size-1] else "image.jpg"
        val folderName = "Motivational Quotes"

        val folder = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), folderName)
        if (!folder.exists()) {
            folder.mkdir()
        }

        // Get the path for the new file
        val file = File(folder, filename)

        // Create a new request for the image download
        val request = DownloadManager.Request(Uri.parse(imageLink))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$folderName/$filename")
        } else {
            request.setDestinationUri(Uri.fromFile(file))
        }

        // Get the download manager and enqueue the download request
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }catch (e: Exception){
        Timber.d("==>Download Exception $e")
    }


    toast(context.getString(R.string.downloading_image))
}



fun shareFun(bitmap: Bitmap?, context: Context) {

    GlobalScope.launch(Dispatchers.Main) {
        val imageFile = bitmap
            ?.let { ImageHelper.convertBitmapToFile(it, "purchasePremiumShare.jpg") }
            ?: return@launch

        val uri =
            FileProvider.getUriForFile(appCtx, BuildConfig.APPLICATION_ID + ".provider", imageFile)


        val i = Intent(Intent.ACTION_SEND)

        i.type = "image/*"
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.putExtra(
            Intent.EXTRA_STREAM,
            uri
        )

        val chooser = Intent.createChooser(i, "Share File")

        val resInfoList: List<ResolveInfo> = context.packageManager
            .queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)

        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            context.grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }

        try {
            context.startActivity(chooser)
        } catch (ex: ActivityNotFoundException) {
            ex.printStackTrace()
        }
    }
}

@OptIn(UnreliableToastApi::class)
fun saveWallpaper(bitmap: Bitmap) {
    //Generating a file name
    val filename = "MotivationalQuotes_${System.currentTimeMillis()}.jpg"

    //Output stream
    var fos: OutputStream? = null

    //For devices running android >= Q
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        //getting the contentResolver
        context.contentResolver?.also { resolver ->

            //Content resolver will process the contentvalues
            val contentValues = ContentValues().apply {

                //putting file information in content values
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            //Inserting the contentValues to contentResolver and getting the Uri
            val imageUri: Uri? =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            //Opening an outputstream with the Uri that we got
            fos = imageUri?.let { resolver.openOutputStream(it) }
        }
    } else {
        //These for devices running on android < Q
        //So I don't think an explanation is needed here
        val imagesDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File(imagesDir, filename)
        fos = FileOutputStream(image)
    }

    fos?.use {
        //Finally writing the bitmap to the output stream that we opened
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        context.toast("Saved to Photos")
    }
}