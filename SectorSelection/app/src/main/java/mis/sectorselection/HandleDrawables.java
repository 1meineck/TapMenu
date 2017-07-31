package mis.sectorselection;


import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.support.v4.content.res.ResourcesCompat;
import android.view.ViewOverlay;

import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * The HandleDrawables provides methods with which the TapMenu can be drawn. It takes the structure of objects of the class Content into account.
 */

class HandleDrawables {

    private int displayWidth;
    private int displayHeight;
    private Resources resources;
    private ViewOverlay viewOverlay;

    private static final int areaSize = 400; // defines the size of the area where one needs to tap to iterate through the items
    private static final int iconSize = 100; // defines the size of the item icons
    private static final int middleSize = 75; // defines the size of the icon in the middle


    private ShapeDrawable areaDrawable; // The drawable that illustrates the tapping area

    private Drawable middleDrawable; // defines the icon in the middle
    private static final Point middleLocation = new Point(-50, -50);

    private ArrayList<Drawable> drawables; // List of all drawables currently on the overlay (not including middledrawable)

    HandleDrawables(Resources resources, ViewOverlay viewOverlay, int displayWidth, int displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.resources = resources;
        this.viewOverlay = viewOverlay;

        // initialize the drawables list
        drawables = new ArrayList<>();
    }

    /*
    draws the selection Area consisting of the Tapping area (area) and the selection Are (okArea).
    The Location is specified by xLongPress and yLongPress.
     */
    void drawSelectionArea(float xLongPress, float yLongPress) {

        // Draw tapping area
        areaDrawable = new ShapeDrawable(new OvalShape());
        areaDrawable.getPaint().setARGB(100, 200, 200, 200);
        areaDrawable.setBounds((int) xLongPress - areaSize, (int) yLongPress - areaSize, (int) xLongPress + areaSize, (int) yLongPress + areaSize);
        viewOverlay.add(areaDrawable);

        /* Draw sectors in the tapping-area, if needed
        //Calculate Segment-Points
        float x0 = (float) (xLongPress + areaSize * cos(toRadians(0)));
        float y0 = (float) (yLongPress + areaSize * sin(toRadians(0)));

        float x1 = (float) (xLongPress + areaSize * cos(toRadians(60)));
        float y1 = (float) (yLongPress + areaSize * sin(toRadians(60)));

        float x2 = (float) (xLongPress + areaSize * cos(toRadians(120)));
        float y2 = (float) (yLongPress + areaSize * sin(toRadians(120)));

        float x3 = (float) (xLongPress + areaSize * cos(toRadians(180)));
        float y3 = (float)(yLongPress + areaSize * sin(toRadians(180)));

        float x4 = (float) (xLongPress + areaSize * cos(toRadians(240)));
        float y4 = (float) (yLongPress + areaSize * sin(toRadians(240)));

        float x5 = (float) (xLongPress + areaSize * cos(toRadians(300)));
        float y5 = (float) (yLongPress + areaSize * sin(toRadians(300)));

        // Create lines between sectors
        Path path1 = new Path();
        path1.moveTo(x3, y3);
        path1.lineTo(x0, y0);
        path1.moveTo(x2, y2);
        path1.lineTo(x5, y5);
        path1.moveTo(x1, y1);
        path1.lineTo(x4, y4);
        ShapeDrawable pathDrawable1 = new ShapeDrawable(new PathShape(path1, displayWidth, displayHeight));
        pathDrawable1.getPaint().setStyle(Paint.Style.STROKE);
        pathDrawable1.getPaint().setStrokeWidth(1);
        pathDrawable1.getPaint().setARGB(255, 230, 230, 230);
        pathDrawable1.setBounds(0, 0, displayWidth, displayHeight);
        viewOverlay.add(pathDrawable1);
*/
    }

    /*
    removes the selection area
     */
    private void removeSelectionArea(){
        viewOverlay.remove(areaDrawable);
       // viewOverlay.remove(pathDrawable1);
    }

    /*
    draws the basic diagram (with no selected items) from a given element of the class content.
     */
    void drawBasicDiagram(Content content) {
        middleDrawable = getDrawableSelected(content); // the middle of the diagram is illustrated by the image_selected
        setDrawable(middleDrawable, middleLocation, middleSize); // the middle drawable is drawn at the middleLocation in its specified size;
        drawables.add(middleDrawable);
        //To draw the rest of the diagram the ArrayList with the following elements gets extracted. For every Element of the list, The image is drawn at the Location specified in the Content class.
        ArrayList<Content> list = content.getNextList();
        for (Content element : list) {
            Drawable drawable = getDrawable(element);
            setDrawable(drawable, element.getLocation(), iconSize); // The location of the image is specified through Content, the size of the icon is set.
            drawables.add(drawable); // The image is added to the drawables list for easier removal if necessary.
        }
    }

    /*
    setDrawable draws an image on the overlay at the specified Location point, relative to the width and height of the display, in a given size.
     */
    private void setDrawable(Drawable drawable, Point point, int size) {
        int x = displayWidth / 2 + point.x;
        int y = displayHeight / 4 + point.y;
        drawable.setBounds(x - size / 2, y - size / 2, x + size / 2, y + size / 2);
        viewOverlay.add(drawable);
    }


    /*
    Takes the index of an image from a Content object and gets the actual Drawable from the resources
     */
    private Drawable getDrawable(Content content) {
        return ResourcesCompat.getDrawable(resources, content.getImageIndex(), null);
    }

    /*
   Takes the index of an image_selected from a Content object and gets the actual Drawable from the resources
    */
    private Drawable getDrawableSelected(Content content) {
        return ResourcesCompat.getDrawable(resources, content.getImage_selectedIndex(), null);
    }

    /*
    removes all drawables in the ArrayList drawables from the overlay and clears the list.
     */
    void removeDrawables() {
        for (Drawable drawable : drawables) {
            viewOverlay.remove(drawable);
        }
        drawables.clear();
    }

    // removes the complete TapMenu, all Drawables and the selection Area
    void removeTapMenu(){
        removeDrawables();
        removeSelectionArea();
        viewOverlay.remove(middleDrawable);
    }

}
