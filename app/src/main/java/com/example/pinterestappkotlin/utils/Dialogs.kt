package com.example.pinterestappkotlin.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Toast
import com.example.pinterestappkotlin.R
import com.example.pinterestappkotlin.activity.MainActivity
import com.example.pinterestappkotlin.database.entity.PhotoRoom
import com.example.pinterestappkotlin.database.repository.PhotoRepository
import com.example.pinterestappkotlin.model.PhotoItem
import com.google.android.material.bottomsheet.BottomSheetDialog

class Dialogs {
    companion object {


        @SuppressLint("CheckResult")
        fun showBottomSheetDialog(context: Context, photo: PhotoItem) {
            val bottomSheetDialog = BottomSheetDialog(context)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)

            val copy = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_copy)
            val share = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_share)
            val upload = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_upload_profile)
            val download = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_download)
            val delete = bottomSheetDialog.findViewById<LinearLayout>(R.id.ll_delete)

            upload!!.setOnClickListener {
                PhotoRepository((context as MainActivity).application).savePhoto(PhotoRoom(photoItem = photo))
                val toast = Toast.makeText(context, "\tImage Saved to Profile\t", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
                bottomSheetDialog.dismiss()
            }

            download!!.setOnClickListener {
                Utils.saveImageToGallery(context, photo.urls!!.regular!!)
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.show()
        }


    }

}