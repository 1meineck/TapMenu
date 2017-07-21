package mis.tapmenu;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOverlay;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GestureDetector.OnGestureListener {

    private GoogleMap mMap;

    private TextView textView;
    private FrameLayout layout;

    private boolean active = false;
    private boolean drawSelectionDiagram = true;

    private ContentObjects co;
    private Content contentList;
    private Content currentSelection;

    private ViewOverlay viewOverlay;

    private int displayWidth;
    private int displayHeight;

    private float xLongPress;
    private float yLongPress;
    private static final int closeDistance = 600;

    private Handler timeHandler;
    private Runnable r;

    private GestureDetectorCompat ourGestureDetector;
    private Resources resources;
    private HandleDrawables handleDrawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        co = new ContentObjects();
        active = false;
        drawSelectionDiagram = true;
        textView = (TextView) findViewById(R.id.stringText);
        layout = (FrameLayout) findViewById(R.id.layout);
        viewOverlay = layout.getOverlay();

        resources = getResources();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        displayWidth = size.x;
        displayHeight = size.y;

        handleDrawables = new HandleDrawables(resources, viewOverlay, displayWidth, displayHeight);

        ourGestureDetector = new GestureDetectorCompat(this, this);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ourGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (active) {
            textView.setText("works");
            active = false;
            handleDrawables.removeDrawables();
            return true;
        }
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        if (!active) {
            active = true;
            xLongPress = e.getX();
            yLongPress = e.getY();


            ShapeDrawable overlayDrawable = new ShapeDrawable();
            overlayDrawable.getPaint().setARGB(150, 150, 150, 150);
            overlayDrawable.setBounds(0, 0, layout.getWidth(), layout.getHeight());
            viewOverlay.add(overlayDrawable);


            contentList = co.getCountries();

            if (drawSelectionDiagram) {
                handleDrawables.drawSelectionArea(xLongPress, yLongPress);
                handleDrawables.drawDiagramArea(contentList, 0);
            }
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


}
