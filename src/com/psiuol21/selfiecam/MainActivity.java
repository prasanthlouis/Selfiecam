package com.psiuol21.selfiecam;

import java.util.List;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	 private final static String DEBUG_TAG = "MakePhotoActivity";
	  private Camera camera;
	  private int cameraId = 0;
	  private CameraPreview mPreview;
	  private MediaRecorder mMediaRecorder;
	  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 getActionBar().hide();
		   if (!getPackageManager()
			        .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			      Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
			          .show();
			    } else {
			    	//Toast.makeText(getApplicationContext(), "FOUND", Toast.LENGTH_SHORT).show();
			      cameraId = findFrontFacingCamera();
			      if (cameraId < 0) {
			        Toast.makeText(this, "No front facing camera found.",
			            Toast.LENGTH_LONG).show();
			      } else {
			        camera = Camera.open(cameraId);
			        mPreview = new CameraPreview(this, camera);
			        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
			        preview.addView(mPreview);
			        Parameters params = camera.getParameters();
			        params.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
			        //some more settings
			    //    params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
			        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
			        params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
			        params.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
			        params.setExposureCompensation(0);
			        params.setRotation(90);
			        List<Size> sizes = params.getSupportedPictureSizes();
			        Camera.Size size = sizes.get(0);
			        for(int i=0;i<sizes.size();i++)
			        {
			            if(sizes.get(i).width > size.width)
			                size = sizes.get(i);
			        }
			        params.setPictureSize(size.width, size.height);
			        camera.setParameters(params);
			      }
			    }

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	
	 private int findFrontFacingCamera() {
		    int cameraId = -1;
		    // Search for the front facing camera
		    int numberOfCameras = Camera.getNumberOfCameras();
		    for (int i = 0; i < numberOfCameras; i++) {
		      CameraInfo info = new CameraInfo();
		      Camera.getCameraInfo(i, info);
		      if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
		        Log.d(DEBUG_TAG, "Camera found");
		        cameraId = i;
		        break;
		      }
		    }
		    return cameraId;
		  }
	 
	public void onClick(View view) {
		camera.autoFocus(new AutoFocusCallback() {
	        public void onAutoFocus(boolean success, Camera camera) {
	            if(success){
	                camera.takePicture(null, null,
	            new PhotoHandler(getApplicationContext()));
	                camera.cancelAutoFocus();
	                camera.startPreview();
	           
	            }
	        }
	    
	    });
	
	    
//    camera.takePicture(null, null,
	//	       new PhotoHandler(getApplicationContext()));
 //   camera.startPreview();
		  }
	 


	 
	  protected void onPause() {
	        super.onPause();
	        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
	        releaseCamera(); 
	        finish();
	        // release the camera immediately on pause event
	    }
	  
	
		  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	 private void releaseMediaRecorder(){
	        if (mMediaRecorder != null) {
	            mMediaRecorder.reset();   // clear recorder configuration
	            mMediaRecorder.release(); // release the recorder object
	            mMediaRecorder = null;
	            camera.lock();           // lock camera for later use
	        }
	    }

	    private void releaseCamera(){
	        if (camera != null){
	            camera.release();        // release the camera for other applications
	            camera = null;
	        }
	    }
	    
	    	// Local method to handle camera init
}
