package mis.tapmenu;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.content.res.ResourcesCompat;
import android.view.ViewOverlay;

import java.util.ArrayList;

/**
 * The HandleDrawables provides methods with which the TapMenu can be drawn. It takes the structure of objects of the class Content into account.
 */

class HandleDrawables {

    private int displayWidth;
    private int displayHeight;
    private Resources resources;
    private ViewOverlay viewOverlay;

    private static final int okAreaSize = 75; // defines the size of the area where an item can be selected
    private static final int areaSize = 400; // defines the size of the area where one needs to tap to iterate through the items
    private static final int iconSize = 100; // defines the size of the item icons
    private static final int middleSize = 75; // defines the size of the icon in the middle


    private ShapeDrawable areaDrawable; // The drawable that illustrates the tapping area
    private ShapeDrawable okAreaDrawable; // the drawable that illustrates the selection area

    private Drawable middleDrawable; // defines the icon in the middle
    private static final Point middleLocation = new Point(-50, -50);

    private ArrayList<Drawable> drawables; // List of all drawables currently on the overlay (not including middledrawable)
    private Drawable selectedDrawable;

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
        areaDrawable = new ShapeDrawable(new OvalShape());
        areaDrawable.getPaint().setARGB(100, 200, 200, 200);
        areaDrawable.setBounds((int) xLongPress - areaSize, (int) yLongPress - areaSize, (int) xLongPress + areaSize, (int) yLongPress + areaSize);
        viewOverlay.add(areaDrawable);

        okAreaDrawable = new ShapeDrawable(new OvalShape());
        okAreaDrawable.getPaint().setARGB(100, 150, 150, 150);
        okAreaDrawable.setBounds((int) xLongPress - okAreaSize, (int) yLongPress - okAreaSize, (int) xLongPress + okAreaSize, (int) yLongPress + okAreaSize);
        viewOverlay.add(okAreaDrawable);
    }

    /*
    removes the selection area
     */
    private void removeSelectionArea(){
        viewOverlay.remove(areaDrawable);
        viewOverlay.remove(okAreaDrawable);
    }

    /*
    draws the Diagram from a Content object with one item at a specific index selected in the List of the next Items
     */
    void drawDiagramArea(Content content, int index) {
        drawBasicDiagram(content);
        ArrayList<Content> list = content.getNextList();
        setDrawableActive(list.get(index));
    }

    /*
    draws the basic diagram (with no selected items) from a given element of the class content.
     */
    private void drawBasicDiagram(Content content) {
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
   removes the normal image of a Content object and replaces it with the image_selected
    */
    void setDrawableActive(Content content) {
        Drawable drawable = getDrawable(content);
        viewOverlay.remove(drawable);
        drawables.remove(drawable);
        if (selectedDrawable != null){
            viewOverlay.remove(selectedDrawable);
        }
        selectedDrawable = getDrawableSelected(content);
        setDrawable(selectedDrawable, content.getLocation(), iconSize);
        drawables.add(selectedDrawable);
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
