package com.example.zenek.weatherzen.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.core.BaseFragment;

import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.inverce.mod.core.IM;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zenek on 03.06.2017.
 */

public class ShareFragment extends BaseFragment implements View.OnClickListener {

    private Button makeImg;
    private Button shareOn;
    private EditText desc;
    static int TAKE_PICTURE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;

    private ImageView mImageView;
    private ImageView avatar;
    public static ShareFragment newInstance() {
        ShareFragment fragment = new ShareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_fragment, container, false);
        findViews(view);
        setImage();
        setListeners();
        return view;
    }

    private void findViews(View view) {
        makeImg=(Button)view.findViewById(R.id.make_photo);
        shareOn=(Button)view.findViewById(R.id.share_on);
        desc=(EditText)view.findViewById(R.id.et_desc);
        mImageView=(ImageView)view.findViewById(R.id.share_img);
        avatar=(ImageView)view.findViewById(R.id.avatar);

    }

    private void setListeners() {
        makeImg.setOnClickListener(this);
        shareOn.setOnClickListener(this);
    }
    private void setImage(){
        avatar.setImageResource(R.drawable.empty_avatar);
        shareOn.setBackgroundResource(R.drawable.share_button);
    }
    private void sharePhotoToFacebook(){
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(mImageBitmap)
                .setCaption(desc.getText().toString())
                .build();

        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        setAlertDialog(content);


    }
    public void setAlertDialog(ShareContent content){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Jezeli jestes pewien ze chcesz udostepnic ten post na swoja sciane wcisnij tak /nie");
        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ShareApi.share(content, null);
                        dialog.cancel();
                    }
                });

        alertDialogBuilder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = alertDialogBuilder.create();
        alert11.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.make_photo:
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
                }
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PICTURE);
                break;

            case R.id.share_on:
                sharePhotoToFacebook();
                break;
        }


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            mImageBitmap=(Bitmap)data.getExtras().get("data");
          //  ByteArrayOutputStream stream = new ByteArrayOutputStream();
          //  mImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

            Glide.with(IM.activity())
                    .load(bitmapToByte(mImageBitmap))
                    .asBitmap()
                    .into(mImageView);
             //   mImageView.setImageBitmap(mImageBitmap);
            }

    }
    private byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
