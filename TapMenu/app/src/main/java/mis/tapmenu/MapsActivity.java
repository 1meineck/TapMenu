package mis.tapmenu;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GestureDetector.OnGestureListener {

    private GoogleMap mMap;

    private FrameLayout tapLayout;
    private FrameLayout mapLayout;

    private boolean active = false;
    private boolean drawSelectionDiagram = true;

    private ContentObjects co;
    private Content activeContent;
    private Content currentSelection;
    private int counter;

    private ViewOverlay viewOverlay;

    private float xLongPress;
    private float yLongPress;

    private static final int okAreaSize = 75;
    private static final int areaSize = 400;
    private static final int closeDistance = 600;

    private Handler timeHandler;
    private Runnable r;

    private GestureDetectorCompat ourGestureDetector;
    private HandleDrawables handleDrawables;
    private ShapeDrawable okLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        co = new ContentObjects(); // An Object of the class ContentObjects is created. ContentObjects defines the elements of the TapMenu
        active = false; // active is initially set to false because the Menu should only open on a longClick and is closed on the opening of the app.
        drawSelectionDiagram = true; // drawSelectionDiagram is initially set to true, because in the beginning, the TapMenu should always be drawn in full
        tapLayout = (FrameLayout) findViewById(R.id.layout); // the layout in which the TapMenu lies
        mapLayout = (FrameLayout) findViewById(R.id.mapLayout); // the layout with the maps
        viewOverlay = tapLayout.getOverlay();

        Resources resources = getResources();

        // The Size of the Display is taken to calculate the Locations of the Drawables of the Menu
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int displayWidth = size.x;
        int displayHeight = size.y;

        // An Object of the class HandleDrawables is created with the resources, the viewOverlay and the size of the display.
        // The object is later used to handle everything related to the drawing of the TapMenu
        handleDrawables = new HandleDrawables(resources, viewOverlay, displayWidth, displayHeight);

        ourGestureDetector = new GestureDetectorCompat(this, this);
        //Adds the onTouchListener to the layout in which the TapMenu is drawn
        tapLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ourGestureDetector.onTouchEvent(event);
                return true;
            }
        });

        // The ShapeDrawable that is Drawn at the opening Location, if the menu is not drawn.
        okLocation = new ShapeDrawable(new OvalShape());
        okLocation.getPaint().setARGB(255, 0, 156, 181);

        // if the TapMenu is not drawn (drawSelectionDiagram = false), a TimeHandler is needed, in case the user has forgotten the structure of the menu.
        // If the user is inactive for too long of a time, the Menu will show up on its own.
        timeHandler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                setDrawSelectionDiagram();
            }
        };
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
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // To open the tapMenu, the Location of the LongClick is needed. to get this, the projection of the map is used and the LatLng is transformed to a Point
                Projection projection = mMap.getProjection();
                Point e = projection.toScreenLocation(latLng);
                openTapMenu(e); // The openTapMenu method is called with the Location of the LongClick on the screen
            }
        });
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
        // Only if the TapMenu is open, changes will occure on Tap
        if (active) {
            handleTap(e);

            return true;
        }
        return false;
    }

    private void handleTap(MotionEvent e) {
        double xTapup = (double) e.getX();
        double yTapup = (double) e.getY();
        double distance = sqrt(pow((xTapup - xLongPress), 2.0) + pow((yTapup - yLongPress), 2.0));

        // if the TimeHandler is active, the Callbacks will be removed on Tap for the Visualization of the TapMenu is not needed.
        if (timeHandler != null) {
            timeHandler.removeCallbacks(r);
        }

        ArrayList<Content> list = activeContent.getNextList(); // get the List of the on the current Content following Contents
        //if the Tap occurs inside the OK-Area,the handleOK function is called.
        if (distance < okAreaSize) {
            handleOK(currentSelection);
        }
        // is the Tap above the original opening Point (LongPress), outside the okArea, but Still inside the defined area for tapping,
        // we will iterate through the upper elements (first elements inside the list.
        else if (yTapup < yLongPress && distance > okAreaSize && distance < areaSize) {
            setCounter(list, 0); // the counter will be set with the setCounter method.
            currentSelection = list.get(counter); // the currentSelection will be the element of the list with the counter as index.
            // if the Menu is drawn, the selected Content will be set as selected in the Diagram.
            if (drawSelectionDiagram) {
                handleDrawables.setDrawableActive(currentSelection);
            }
        }
        // is the Tap below the original opening point (LongPress), outside the okArea, but still inside the tapping area,
        // we will iterate through the second half of the Contents in the list.
        else if (yTapup > yLongPress && distance > okAreaSize && distance < areaSize) {
            setCounter(list, 1);
            currentSelection = list.get(counter);   // the currentSelection will be the element of the list with the counter as index
            // if the menu is drawn, the currentSelection will be set as selected in the diagram.
            if (drawSelectionDiagram) {
                handleDrawables.setDrawableActive(currentSelection);
            }
        } else if (distance > closeDistance) {
            // if the tap is outside the specified distance, the closeTapMenu function is called and the whole Menu closes
            closeTapMenu();
        }
    }

    /*
    sets the counter depending on
    if the tap was over the original Opening Location:   -> i = 0
    if the tap was beneath the Opening Location:        -> i = 1
    @param list
    @param i
     */
    private void setCounter(ArrayList<Content> list, int i){
        //tap was over the Original opening Location (LongPress)
        if (i == 0){
        if ((counter + 1) <= ((list.size() - 1) / 2)) {
            // the counter will be set up if it is still smaller than half the size of the list.
            counter++;
        } else {
            // if the counter is bigger than half the size of the list, it will be set back to 0, to ensure iteration over only the top-half of the list.
            counter = 0;
        }}
        // Tap was beneath the original opening Location (LongPress)
        else if (i == 1){
            // the counter will be set up if it is still smaller than the size and the list but bigger than half the size of the list.
            if ((counter + 1) < (list.size()) && (counter > (list.size() - 1) / 2)) {
                counter++;
            } else {
                // if the counter is smaller than half the size of the list, it will be set on half the size of the list + 1;
                counter = (list.size() - 1) / 2 + 1;
            }
        }

    }

    /*
    Closes the TapMenu by deleting all overlays, setting active to false and removing the callbacks of the timeHandler
     */
    private void closeTapMenu() {
        viewOverlay.clear();
        active = false;
        mapLayout.bringToFront();
        if (timeHandler != null) {
            timeHandler.removeCallbacks(r);
        }
    }

    /* handleOK makes the action after a Content is selected by tapping.
     Depending on if there are any Contents in the nextList, or if the selection is the final selection, the results are different.
     @param selection     the Content that is slected. Will define where the map will zoom in, and if/how the Menu will be drawn.
     */
    private void handleOK(Content selection) {
        activeContent = selection; // will set the avtiveContent to the selection.
        counter = 0; // counter will be set to 0
        zoomIn(selection); // zoomIn will be called to move the camera to the right position on the map.
        if (selection.getNextList() != null) {
            // if there is a list with possible following Contents,
            currentSelection = selection.getNextList().get(counter); // the currentSelection will be set to the first element of the List
            if (drawSelectionDiagram) {
                // if the Menu is drawn, it will be replaced by the drawing of the new activeContent, with the first element selected.
                handleDrawables.removeDrawables();
                handleDrawables.drawDiagramArea(activeContent, counter);
            } else {
                // otherwise, the timeHandler will be initiated, in case the user forgot the structure of the diagram, so the menu will be shown after a certain time without action.
                timeHandler.postDelayed(r, 5000);
            }
        } else {
            // is the selection a final Content, a Toast will be thrown and the Menu will close.
            Toast.makeText(this, selection.getName(), Toast.LENGTH_SHORT).show();
            closeTapMenu();
        }
    }

    // moves the Camera to the position saved in the selection, with the zoomFactor also saved in the selection
    // @param selection the Content that is selected. It will define where the camera will move on the map and how far it will zoom in.
    private void zoomIn(Content selection) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selection.getLatLng(), selection.getZoomFactor()));
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // if the TapMenu is not already open, it will open on a longPress by calling the openTapMenu method.
        if (!active) {
            Point p = new Point((int) e.getX(), (int) e.getY());
            openTapMenu(p); // The openTapMenu method is called with the Location of the LongPress

        } else {
            // if the TapMenu is open, a LongPress will result in the showing or removing of the Drawing of the menu
            setDrawSelectionDiagram();
        }
    }

    /*
    changes the boolean drawSelectionDiagram to true or false, depending on its state. Removes the elements of the viewoverlay, that should no longer be seen, and adds the new elements
     */
    private void setDrawSelectionDiagram() {
        if (drawSelectionDiagram) {
            // if the menu should no longer be shown, set drawSelectionDiagram to false,
            drawSelectionDiagram = false;
            // Add the okLocation to the screen, to indicate, where the okArea is
            okLocation.setBounds((int) xLongPress - 25, (int) yLongPress - 25, (int) xLongPress + 25, (int) yLongPress + 25);
            viewOverlay.add(okLocation);
            // and remove the TapMenu by calling the method from handleDrawables
            handleDrawables.removeTapMenu();
        } else {
            // if the menu should again be shown, set drawSelectionDiagram to true,
            drawSelectionDiagram = true;
            // Add the SelectionArea
            handleDrawables.drawSelectionArea(xLongPress, yLongPress);
            // and the menu/diagram to the view by calling the methods from handleDrawables.
            handleDrawables.drawDiagramArea(activeContent, counter);
            // and remove the okLocation because it is no longer needed.
            viewOverlay.remove(okLocation);
        }
    }

    /*  Opens the TapMenu by getting the first activeContent from the ContentObjects Class and setting the first currentSelection.
        Draws the SelectionArea and the menu, if necessary.
        @param e the center point of the selectionArea. Needed for the drawing of the slectionArea and the handling of taps.
     */
    private void openTapMenu(Point e) {

        // bring the tapLayout to the front. Maps has no built-in tapListener.
        tapLayout.bringToFront();
        active = true;

        // set xLongPress and yLongPress. Needed for the Drawing of the SelectionArea, as well as for the handling of Taps.
        xLongPress = e.x;
        yLongPress = e.y;

        counter = 0; // the counter will be set to 0

        // The display gets a gray overlay to indicate that the menu is active.
        ShapeDrawable overlayDrawable = new ShapeDrawable();
        overlayDrawable.getPaint().setARGB(150, 150, 150, 150);
        overlayDrawable.setBounds(0, 0, tapLayout.getWidth(), tapLayout.getHeight());
        viewOverlay.add(overlayDrawable);

        // get the first Content from th ContentObjects class. All following Contents can be reached through this first conent.
        activeContent = co.getCountries();
        // set the first selected content
        currentSelection = activeContent.getNextList().get(counter);

        if (drawSelectionDiagram) {
            // if the menu should be drawn
            handleDrawables.drawSelectionArea(xLongPress, yLongPress); // draw the selection area in which to tap
            handleDrawables.drawDiagramArea(activeContent, counter); // draw the menu with a selected element defined through the counter
        } else {
            // if the menu should not be drawn,
            timeHandler.postDelayed(r, 5000); // the timeHandler will be initiated, in case the user forgot the structure of the diagram, so the menu will be shown after a certain time without action.
            okLocation.setBounds((int) xLongPress - 25, (int) yLongPress - 25, (int) xLongPress + 25, (int) yLongPress + 25); // draw a small circle to indicate where to log a selection. Otherwise, there will be no additional help.
            viewOverlay.add(okLocation);
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


}
