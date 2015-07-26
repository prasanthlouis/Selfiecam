package com.psiuol21.selfiecam;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
<<<<<<< HEAD
import android.media.MediaScannerConnection;
import android.net.Uri;
=======
>>>>>>> 631654f04374ed3e840d9d81cf87662c4fec6515
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class PhotoHandler implements PictureCallback {

  private final Context context;
<<<<<<< HEAD
  MediaScannerConnection conn;

=======
>>>>>>> 631654f04374ed3e840d9d81cf87662c4fec6515

  public PhotoHandler(Context context) {
    this.context = context;
  }

  @Override
  public void onPictureTaken(byte[] data, Camera camera) {

    File pictureFileDir = getDir();
<<<<<<< HEAD
    
=======

>>>>>>> 631654f04374ed3e840d9d81cf87662c4fec6515
    if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {

  //    Log.d(MakePhotoActivity.DEBUG_TAG, "Can't create directory to save image.");
      Toast.makeText(context, "Can't create directory to save image.",
          Toast.LENGTH_LONG).show();
      return;

    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
    String date = dateFormat.format(new Date());
    String photoFile = "Picture_" + date + ".jpg";

    String filename = pictureFileDir.getPath() + File.separator + photoFile;

    File pictureFile = new File(filename);
<<<<<<< HEAD
   
=======
>>>>>>> 631654f04374ed3e840d9d81cf87662c4fec6515

    try {
      FileOutputStream fos = new FileOutputStream(pictureFile);
      fos.write(data);
      fos.close();
      Toast.makeText(context, "New Image saved:" + photoFile,
          Toast.LENGTH_LONG).show();
<<<<<<< HEAD
     
=======
>>>>>>> 631654f04374ed3e840d9d81cf87662c4fec6515
    } catch (Exception error) {
//      Log.d(MakePhotoActivity.DEBUG_TAG, "File" + filename + "not saved: "
     //     + error.getMessage());
      Toast.makeText(context, "Image could not be saved.",
          Toast.LENGTH_LONG).show();
    }
<<<<<<< HEAD
    new SingleMediaScanner(this.context, pictureFile);
    
   // startScan( pictureFileDir.getPath());
    
 /*   MediaScannerConnection.scanFile(this, new String[] { Environment.getExternalStorageDirectory().toString() }, null, new MediaScannerConnection.OnScanCompletedListener() {
        /*
         *   (non-Javadoc)
         * @see android.media.MediaScannerConnection.OnScanCompletedListener#onScanCompleted(java.lang.String, android.net.Uri)
         */
/*        public void onScanCompleted(String path, Uri uri) 
          {
              Log.i("ExternalStorage", "Scanned " + path + ":");
              Log.i("ExternalStorage", "-> uri=" + uri);
          }
        });
   */
  }
  
 
  
 
  
  

	
=======
  }
>>>>>>> 631654f04374ed3e840d9d81cf87662c4fec6515

  private File getDir() {
    File sdDir = Environment
      .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    return new File(sdDir, "Selfiecam");
  }
<<<<<<< HEAD
  
  
  
=======
>>>>>>> 631654f04374ed3e840d9d81cf87662c4fec6515
} 
