package mis.tapmenu;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;


/**
 * Created by annika on 19.07.17.
 */

public class ContentObjects {

   public ContentObjects(){
        ArrayList<Content> countries = new ArrayList<>();
        countries.add(createFrance());
        countries.add(createGermany());
        countries.add(createGreece());
        countries.add(createItaly());
        countries.add(createPoland());
        countries.add(createSpain());
    }

    private Content createFrance() {

        // Coordinates of France and French Cities
        LatLng franceCoordinates = new LatLng(46, 2);
        LatLng parisCoordinates = new LatLng(48.864716, 2.349014);
        LatLng lyonCoordinates = new LatLng(45.74846, 4.84671);
        LatLng marseillesCoordinates = new LatLng(43.29695, 5.38107);
        LatLng niceCoordinates = new LatLng(43.675819, 7.289429);
        LatLng cannesCoordinates = new LatLng(43.552849, 7.017369);
        LatLng avignonCoordinates = new LatLng(43.94834, 4.80892);

        // Content Classes of French Cities
        Content paris = new Content("Paris", parisCoordinates, 10, R.drawable.france_paris, R.drawable.france_paris_selected, null);
        Content lyon = new Content("Lyon", lyonCoordinates, 10, R.drawable.france_lyon, R.drawable.france_lyon_selected, null);
        Content marseilles = new Content("Marseilles", marseillesCoordinates, 10, R.drawable.france_marseilles, R.drawable.france_marseilles_selected, null);
        Content nice = new Content("Nice", niceCoordinates, 10, R.drawable.france_nice, R.drawable.france_nice_selected, null);
        Content cannes = new Content("Cannes", cannesCoordinates, 10, R.drawable.france_cannes, R.drawable.france_cannes_selected, null);
        Content avignon = new Content("Avignon", avignonCoordinates, 10, R.drawable.france_avignon, R.drawable.france_avignon_selected, null);

        // Content classes of French cities in an ArrayList.
        ArrayList<Content> franceCities = new ArrayList<>();
        franceCities.add(paris);
        franceCities.add(lyon);
        franceCities.add(marseilles);
        franceCities.add(nice);
        franceCities.add(cannes);
        franceCities.add(avignon);

        // Content class of France
        Content france = new Content("France", franceCoordinates, 5, R.drawable.france, R.drawable.france_selected, franceCities);

        return france;
    }

    private Content createGermany() {

        // Coordinates of Germany and German cities
        LatLng germanyCoordinates = new LatLng(51, 10);
        LatLng berlinCoordinates = new LatLng(52.520008, 13.404954);
        LatLng munichCoordinates = new LatLng(48.137154, 11.576124);
        LatLng hamburgCoordinates = new LatLng(53.580139, 10.030292);
        LatLng cologneCoordinates = new LatLng(50.935173, 6.953101);
        LatLng frankfurtCoordinates = new LatLng(52.34714, 14.55062);
        LatLng weimarCoordinates = new LatLng(50.978516, 11.332221);

        Content berlin = new Content("Berlin", berlinCoordinates, 10, R.drawable.germany_berlin, R.drawable.germany_berlin_selected, null);
        Content munich = new Content("Munich", munichCoordinates, 10, R.drawable.germany_muenchen, R.drawable.germany_muenchen_selected, null);
        Content hamburg = new Content("Hamburg", hamburgCoordinates, 10, R.drawable.germany_hamburg, R.drawable.germany_hamburg_selected, null);
        Content cologne = new Content("Cologne", cologneCoordinates, 10, R.drawable.germany_cologne, R.drawable.germany_cologne_selected, null);
        Content frankfurt = new Content("Frankfurt", frankfurtCoordinates, 11, R.drawable.germany_frankfurt, R.drawable.germany_frankfurt_selected, null);
        Content weimar = new Content("Weimar", weimarCoordinates, 12, R.drawable.germany_weimar, R.drawable.germany_weimar_selected, null);

        ArrayList<Content> germanyCities = new ArrayList<>();
        germanyCities.add(berlin);
        germanyCities.add(munich);
        germanyCities.add(hamburg);
        germanyCities.add(cologne);
        germanyCities.add(frankfurt);
        germanyCities.add(weimar);

        Content germany = new Content("Germany", germanyCoordinates, 5, R.drawable.germany, R.drawable.germany_selected, germanyCities);

        return germany;

    }

    private Content createGreece() {

        LatLng greeceCoordinates = new LatLng(39, 24);
        LatLng athensCoordinates = new LatLng(37.97945, 23.71622);
        LatLng rhodesCoordinates = new LatLng(36.4349631, 28.2174829);
        LatLng delphiCoordinates = new LatLng(38.480052, 22.494062);
        LatLng corfuCoordinates = new LatLng(39.624444, 19.907778);
        LatLng spartaCoordinates = new LatLng(37.07583303, 22.42083165);
        LatLng thessalonikiCoordinates = new LatLng(40.64361, 22.93086);

        Content athens = new Content("Athens", athensCoordinates, 10, R.drawable.greece_athens, R.drawable.greece_athens_selected, null);
        Content rhodes = new Content("Rhodes", rhodesCoordinates, 10, R.drawable.greece_rhodes, R.drawable.greece_rhodes_selected, null);
        Content delphi = new Content("Delphi", delphiCoordinates, 10, R.drawable.greece_delphi, R.drawable.greece_delphi_selected, null);
        Content corfu = new Content("Corfu", corfuCoordinates, 10, R.drawable.greece_corfu, R.drawable.greece_corfu_selected, null);
        Content sparta = new Content("Sparta", spartaCoordinates, 10, R.drawable.greece_sparta, R.drawable.greece_sparta_selected, null);
        Content thessaloniki = new Content("Thessaloniki", thessalonikiCoordinates, 10, R.drawable.greece_thessaloniki, R.drawable.greece_thessaloniki_selected, null);

        ArrayList<Content> greeceCities = new ArrayList<>();
        greeceCities.add(athens);
        greeceCities.add(rhodes);
        greeceCities.add(delphi);
        greeceCities.add(corfu);
        greeceCities.add(sparta);
        greeceCities.add(thessaloniki);

        Content greece = new Content("Greece", greeceCoordinates, 5, R.drawable.greece, R.drawable.greece_selected, greeceCities);

        return greece;
    }

    private Content createItaly() {

        LatLng italyCoordinates = new LatLng(42.5, 12.5);
        LatLng romeCoordinates = new LatLng(41.89193, 12.51133);
        LatLng veniceCoordinates = new LatLng(45.43713, 12.33265);
        LatLng florenceCoordinates = new LatLng(43.77925, 11.24626);
        LatLng pisaCoordinates = new LatLng(43.70853, 10.4036);
        LatLng naplesCoordinates = new LatLng(40.85631, 14.24641);
        LatLng milanCoordinates = new LatLng(45.4654219, 9.1859243);

        Content rome = new Content("Rome", romeCoordinates, 10, R.drawable.italy_rome, R.drawable.italy_rome_selected, null);
        Content venice = new Content("Venice", veniceCoordinates, 10, R.drawable.italy_venice, R.drawable.italy_venice_selected, null);
        Content florence = new Content("Florence", florenceCoordinates, 10, R.drawable.italy_florence, R.drawable.italy_florence_selected, null);
        Content pisa = new Content("Pisa", pisaCoordinates, 10, R.drawable.italy_pisa, R.drawable.italy_pisa_selected, null);
        Content naples = new Content("Naples", naplesCoordinates, 10, R.drawable.italy_naples, R.drawable.italy_naples_selected, null);
        Content milan = new Content("Milan", milanCoordinates, 10, R.drawable.italy_milan, R.drawable.italy_milan_selected, null);

        ArrayList<Content> italyCities = new ArrayList<>();
        italyCities.add(rome);
        italyCities.add(venice);
        italyCities.add(florence);
        italyCities.add(pisa);
        italyCities.add(naples);
        italyCities.add(milan);

        Content italy = new Content("Italy", italyCoordinates, 5, R.drawable.italy, R.drawable.italy_selected, italyCities);

        return italy;
    }

    private Content createPoland() {

        LatLng polandCoordinates = new LatLng(52, 19);
        LatLng warsawCoordinates = new LatLng(52.22977, 21.01178);
        LatLng krakowCoordinates = new LatLng(50.06143, 19.93658);
        LatLng lublinCoordinates = new LatLng(51.25, 22.5666);
        LatLng torunCoordinates = new LatLng(53.01375, 18.59814);
        LatLng poznanCoordinates = new LatLng(52.40692, 16.92993);
        LatLng gdanskCoordinates = new LatLng(54.35205, 18.64637);

        Content warsaw = new Content("Warsaw", warsawCoordinates, 10, R.drawable.poland_warsaw, R.drawable.poland_warsaw_selected, null);
        Content krakow = new Content("Krakow", krakowCoordinates, 10, R.drawable.poland_krakow, R.drawable.poland_krakow_selected, null);
        Content lublin = new Content("Lublin", lublinCoordinates, 10, R.drawable.poland_lublin, R.drawable.poland_lublin_selected, null);
        Content torun = new Content("Torun", torunCoordinates, 10, R.drawable.poland_torun, R.drawable.poland_torun_selected, null);
        Content poznan = new Content("Poznan", poznanCoordinates, 10, R.drawable.poland_poznan, R.drawable.poland_poznan_selected, null);
        Content gdansk = new Content("Gdansk", gdanskCoordinates, 10, R.drawable.poland_gdansk, R.drawable.poland_gdansk_selected, null);

        ArrayList<Content> cities = new ArrayList<>();
        cities.add(warsaw);
        cities.add(krakow);
        cities.add(lublin);
        cities.add(torun);
        cities.add(poznan);
        cities.add(gdansk);

        Content poland = new Content("Poland", polandCoordinates, 5, R.drawable.poland, R.drawable.poland_selected, cities);

        return poland;
    }

    private Content createSpain() {

        LatLng spainCoordinates = new LatLng(40.5, -4);
        LatLng madridCoordinates = new LatLng(40.4165, -3.70256);
        LatLng barcelonaCoordinates = new LatLng(41.3850639, 2.1734035);
        LatLng sevilleCoordinates = new LatLng(37.38283, -5.97317);
        LatLng granadaCoordinates = new LatLng(37.18817, -3.60667);
        LatLng palmaCoordinates = new LatLng(39.5722, 2.6529);
        LatLng valenciaCoordinates = new LatLng(39.46975, -0.37739);

        Content madrid = new Content("Madrid", madridCoordinates, 10, R.drawable.spain_madrid, R.drawable.spain_madrid_selected, null);
        Content barcelona = new Content("Barcelona", barcelonaCoordinates, 10, R.drawable.spain_barcelona, R.drawable.spain_barcelona_selected, null);
        Content seville = new Content("Seville", sevilleCoordinates, 10, R.drawable.spain_seville, R.drawable.spain_seville_selected, null);
        Content granada = new Content("Granada", granadaCoordinates, 10, R.drawable.spain_granada, R.drawable.spain_granada_selected, null);
        Content palma = new Content("Palma", palmaCoordinates, 10, R.drawable.spain_palma, R.drawable.spain_palma_selected, null);
        Content valencia = new Content("Valencia", valenciaCoordinates, 10, R.drawable.spain_valencia, R.drawable.spain_valencia_selected, null);

        ArrayList<Content> cities = new ArrayList<>();
        cities.add(madrid);
        cities.add(barcelona);
        cities.add(seville);
        cities.add(granada);
        cities.add(palma);
        cities.add(valencia);

        Content spain = new Content("Spain", madridCoordinates, 5, R.drawable.spain, R.drawable.spain_selected, cities);

        return spain;
    }


    private class Content{
        private String name;
        private LatLng latLng;
        private float zoom;
        private int image;
        private int image_selected;
        private ArrayList<Content> nextList;

        private Content(String name, LatLng latLng, float zoom, int image, int image_selected, ArrayList<Content> nextList){
            this.name = name;
            this.latLng = latLng;
            this.zoom = zoom;
            this.image = image;
            this.image_selected = image_selected;
            this.nextList = nextList;
        }

        private String getName(){
            return name;
        }

        private LatLng getLatLng(){
            return latLng;
        }

        private float getZoomFactor(){
            return zoom;
        }

        private int getImageIndex(){
            return image;
        }

        private int getImage_selectedIndex(){
            return image_selected;
        }

        private ArrayList<Content> getNextList(){
            return nextList;
        }
    }
}
