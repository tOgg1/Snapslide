package org.trmd.snapslide;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
        // Set the window to support translucency
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        surfaceViewCamera = (SurfaceView) findViewById(R.id.cameraSurfaceView);
        buttonCamera = (Button) findViewById(R.id.cameraButton);

        surfaceHolderCamera = surfaceViewCamera.getHolder();

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

    private class HolderCallback implements SurfaceHolder.Callback{

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                frontCamera = Camera.open();
                frontCamera.setDisplayOrientation(90);
                frontCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}
