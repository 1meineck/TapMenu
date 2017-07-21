package mis.tapmenu;

import android.graphics.Point;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by annika on 21.07.17.
 */

public class Content {
    private String name;
    private LatLng latLng;
    private float zoom;
    private int image;
    private int image_selected;
    private Point location;
    private ArrayList<Content> nextList;

    public Content(String name, LatLng latLng, float zoom, int image, int image_selected, Point location, ArrayList<Content> nextList){
        this.name = name;
        this.latLng = latLng;
        this.zoom = zoom;
        this.image = image;
        this.image_selected = image_selected;
        this.location = location;
        this.nextList = nextList;
    }

    public String getName(){
        return name;
    }

    public LatLng getLatLng(){
        return latLng;
    }

    public float getZoomFactor(){
        return zoom;
    }

    public int getImageIndex(){
        return image;
    }

    public int getImage_selectedIndex(){
        return image_selected;
    }

    public  Point getLocation(){
        return location;
    }
    public ArrayList<Content> getNextList(){
        return nextList;
    }

}
