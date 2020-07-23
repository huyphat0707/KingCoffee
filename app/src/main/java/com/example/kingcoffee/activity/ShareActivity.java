package com.example.kingcoffee.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.kingcoffee.R;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ShareActivity extends AppCompatActivity {
EditText tieude,mota,duongdan;
Button taiAnh,taiVideo,chupAnh,quayVideo,shlink,shimage,shvideo;
VideoView videoView;
ImageView img;
ShareDialog shareDialog;
ShareLinkContent shareLinkContent;
int REQUESt_CODE_CAMERA = 123 ;
    Bitmap bitmap;
    Uri selecvideo;
    public  static int Select_Image = 1;
    public  static int Pick_Video = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
       Anhxa();

       shareDialog = new ShareDialog(ShareActivity.this);
       shlink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (shareDialog.canShow(ShareLinkContent.class)) {
                   shareLinkContent = new ShareLinkContent.Builder()
                           .setContentTitle(tieude.getText().toString())
                           .setContentDescription(mota.getText().toString())
                           .setImageUrl(Uri.parse(duongdan.getText().toString()))
                           .build();
               }
               shareDialog.show(shareLinkContent);
           }
       });

//       chupAnh.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//               startActivityForResult(i,REQUESt_CODE_CAMERA );
//           }
//       });

       taiAnh.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setType("image/");
               startActivityForResult(intent,Select_Image);
           }
       });
       shimage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharePhoto photo = new SharePhoto.Builder()
                       .setBitmap(bitmap)
                       .build();
               SharePhotoContent content = new SharePhotoContent.Builder()
                       .addPhoto(photo)
                       .build();
               shareDialog.show(content);

           }
       });
       taiVideo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(Intent.ACTION_PICK);
               i.setType("video/");
               startActivityForResult(i,Pick_Video);
           }
       });
       shvideo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ShareVideo shareVideo;
               shareVideo = new ShareVideo.Builder()
                       .setLocalUrl(selecvideo)
                       .build();
               ShareVideoContent content = new ShareVideoContent.Builder()
                       .setVideo(shareVideo)
                       .build();
               shareDialog.show(content);
               videoView.stopPlayback();
           }
       });

    }
    private void Anhxa(){
        tieude = findViewById(R.id.edtieude);
        mota = findViewById(R.id.edMota);
        duongdan = findViewById(R.id.edDuongDan);
        taiAnh = findViewById(R.id.loadHinh);
        taiVideo = findViewById(R.id.loadvideo);
        chupAnh = findViewById(R.id.camera);
        quayVideo = findViewById(R.id.quayvideo);
        shlink = findViewById(R.id.btnLink);
        shimage = findViewById(R.id.btnImage);
        shvideo = findViewById(R.id.btnVideo);
        videoView = findViewById(R.id.videoView);
        img = findViewById(R.id.imghinh);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUESt_CODE_CAMERA && resultCode == RESULT_OK && data !=null){
             bitmap =(Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
        }
        if (requestCode == Select_Image && resultCode == RESULT_OK){
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Pick_Video && resultCode == RESULT_OK){
            selecvideo = data.getData();
            videoView.setVideoURI(selecvideo);
            videoView.start();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
