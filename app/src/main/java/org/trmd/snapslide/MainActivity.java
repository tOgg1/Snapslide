package org.trmd.snapslide;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import java.io.IOException;


@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

    /**
     * Our magnificent front-camera
     */
    private Camera backCamera;

    /**
     * Our magnificent back-camera
     */
    private Camera frontCamera;

    /**
     * Current active camera
     */
    private Camera activeCamera;

    /**
     * The view we display our camera information on
     */
    private SurfaceView surfaceViewCamera;

    /**
     * The surface holder of the surfaceView
     */
    private SurfaceHolder surfaceHolderCamera;

    /**
     * Our "take picture" button
     */
    private Button buttonCamera;

    /**
     * The button which enables or disables blitz
     */
    private Button buttonEnableBlitz;

    /**
     * Button taking us to the settings page
     */
    private Button buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    /**
     * Initialize everything
     */
    protected void initialize() {
        Log.d("MainActivity", "Initializing");
        // Set the window to support translucency
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        surfaceViewCamera = (SurfaceView) findViewById(R.id.cameraSurfaceView);
        buttonCamera = (Button) findViewById(R.id.cameraButton);

        surfaceHolderCamera = surfaceViewCamera.getHolder();

        try{
            surfaceHolderCamera.addCallback(new HolderCallback());
        }catch(Throwable e){
            Log.getStackTraceString(e);
        }

        Log.d("MainActivity", "Initialization completed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Finds the id of the camera facing FRONT.
     *
     * @return The id of the camera, or -1 if not found.
     */
    public int findFrontCamera(){
        return 0;
    }

    /**
     * Finds the id of the camera facing BACK.
     *
     * @return The id of the camera, or -1 if not found.
     */
    public int findBackCamera(){
        return 0;
    }

    /**
     * Safely opens a camera.
     * @param id The id of the camera
     * @return True if camera was opened succesfully. False if an error occured.
     */
    public boolean openCamera(int id){
        return true;
    }

    /**
     * Callback for our CameraViewSurface.Holder
     * Sets up and handles camera stuff
     */
    private class HolderCallback implements SurfaceHolder.Callback{

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                activeCamera = Camera.open();
                //activeCamera.setDisplayOrientation(90);
                activeCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            activeCamera.startPreview();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            activeCamera.stopPreview();
            activeCamera.release();
            activeCamera = null;
        }
    }
}
