package mis.tapmenu;

import android.graphics.Point;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

/**
 * The Content class provides the structure for the Country and City Objects needed for the TapMenu
 * It consists of a String, the name of the Object (e.g. Paris)
 * A LatLng position, the location of the Object on the map
 * A zoom Factor, to tell the map, how far to zoom in
 * An image which stands for the country/city and is shown as its symbol in the menu
 * An image_selected which symbolizes the object in the menu, if the object is selected,
 * A point that indicates the location of the images on the display
 * A ArrayList with the next level of the menu, if it exists.
 */

class Content {
    private String name;
    private LatLng latLng;
    private float zoom;
    private int image;
    private int image_selected;
    private Point location;
    private ArrayList<Content> nextList;

    Content(String name, LatLng latLng, float zoom, int image, int image_selected, Point location, ArrayList<Content> nextList){
        this.name = name;
        this.latLng = latLng;
        this.zoom = zoom;
        this.image = image;
        this.image_selected = image_selected;
        this.location = location;
        this.nextList = nextList;
    }


    /*
    getter-methods for the Objects of Class Content
     */

    String getName(){
        return name;
    }

    LatLng getLatLng(){
        return latLng;
    }

    float getZoomFactor(){
        return zoom;
    }

    int getImageIndex(){
        return image;
    }

    int getImage_selectedIndex(){
        return image_selected;
    }

    Point getLocation(){
        return location;
    }
    ArrayList<Content> getNextList(){
        return nextList;
    }

}
