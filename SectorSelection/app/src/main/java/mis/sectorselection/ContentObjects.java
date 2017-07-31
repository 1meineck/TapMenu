package mis.sectorselection;


import android.graphics.Point;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;


/**
 * The ContentObjects class provides the overall Structure of the TapMenu by implementing an Object of the class Content that implements every level of the TapMenu.
 */

class ContentObjects {

    private Content countries;
    private Point item1Location;
    private Point item2Location;
    private Point item3Location;
    private Point item4Location;
    private Point item5Location;
    private Point item6Location;


    ContentObjects() {

        Point middleLocation = new Point(-50, -50);
        item1Location = new Point(-225, -200);
        item2Location = new Point(-50, -275);
        item3Location = new Point(+125, -200);
        item4Location = new Point(-225, 100);
        item5Location = new Point(-50, 175);
        item6Location = new Point(+125, 100);

        ArrayList<Content> countriesList = new ArrayList<>();
        countriesList.add(createFrance());
        countriesList.add(createGermany());
        countriesList.add(createGreece());
        countriesList.add(createSpain());
        countriesList.add(createPoland());
        countriesList.add(createItaly());

        countries = new Content("Countries", new LatLng(0, 0), 0, R.drawable.middle_drawable, R.drawable.middle_drawable, middleLocation, countriesList); //ToDo: new drawable!!

    }

    Content getCountries() {
        return countries;
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

        // creates Content objects for each French city
        Content paris = new Content("Paris", parisCoordinates, 10, R.drawable.france_paris, R.drawable.france_paris_selected, item1Location, null);
        Content lyon = new Content("Lyon", lyonCoordinates, 10, R.drawable.france_lyon, R.drawable.france_lyon_selected, item2Location, null);
        Content marseilles = new Content("Marseilles", marseillesCoordinates, 10, R.drawable.france_marseilles, R.drawable.france_marseilles_selected, item3Location, null);
        Content nice = new Content("Nice", niceCoordinates, 10, R.drawable.france_nice, R.drawable.france_nice_selected, item4Location, null);
        Content cannes = new Content("Cannes", cannesCoordinates, 10, R.drawable.france_cannes, R.drawable.france_cannes_selected, item5Location, null);
        Content avignon = new Content("Avignon", avignonCoordinates, 10, R.drawable.france_avignon, R.drawable.france_avignon_selected, item6Location, null);

        // puts all Content objects of French cities in an ArrayList
        ArrayList<Content> franceCities = new ArrayList<>();
        franceCities.add(paris);
        franceCities.add(lyon);
        franceCities.add(marseilles);
        franceCities.add(avignon);
        franceCities.add(cannes);
        franceCities.add(nice);

        // returns Content object of France with ArrayList of French cities as nextList.
        return new Content("France", franceCoordinates, 5, R.drawable.france, R.drawable.france_selected, item1Location, franceCities);
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

        // creates Content objects for each German city
        Content berlin = new Content("Berlin", berlinCoordinates, 10, R.drawable.germany_berlin, R.drawable.germany_berlin_selected, item1Location, null);
        Content munich = new Content("Munich", munichCoordinates, 10, R.drawable.germany_muenchen, R.drawable.germany_muenchen_selected, item2Location, null);
        Content hamburg = new Content("Hamburg", hamburgCoordinates, 10, R.drawable.germany_hamburg, R.drawable.germany_hamburg_selected, item3Location, null);
        Content cologne = new Content("Cologne", cologneCoordinates, 10, R.drawable.germany_cologne, R.drawable.germany_cologne_selected, item4Location, null);
        Content frankfurt = new Content("Frankfurt", frankfurtCoordinates, 11, R.drawable.germany_frankfurt, R.drawable.germany_frankfurt_selected, item5Location, null);
        Content weimar = new Content("Weimar", weimarCoordinates, 12, R.drawable.germany_weimar, R.drawable.germany_weimar_selected, item6Location, null);

        // puts all Content objects of German cities in an ArrayList
        ArrayList<Content> germanyCities = new ArrayList<>();
        germanyCities.add(berlin);
        germanyCities.add(munich);
        germanyCities.add(hamburg);
        germanyCities.add(weimar);
        germanyCities.add(frankfurt);
        germanyCities.add(cologne);

        // returns Content object of Germany with ArrayList of German cities as nextList.
        return new Content("Germany", germanyCoordinates, 5, R.drawable.germany, R.drawable.germany_selected, item2Location, germanyCities);

    }

    private Content createGreece() {
        // Coordinates of Greeece and Greece cities
        LatLng greeceCoordinates = new LatLng(39, 24);
        LatLng athensCoordinates = new LatLng(37.97945, 23.71622);
        LatLng rhodesCoordinates = new LatLng(36.4349631, 28.2174829);
        LatLng delphiCoordinates = new LatLng(38.480052, 22.494062);
        LatLng corfuCoordinates = new LatLng(39.624444, 19.907778);
        LatLng spartaCoordinates = new LatLng(37.07583303, 22.42083165);
        LatLng thessalonikiCoordinates = new LatLng(40.64361, 22.93086);

        // creates Content objects for each Greece city
        Content athens = new Content("Athens", athensCoordinates, 10, R.drawable.greece_athens, R.drawable.greece_athens_selected, item1Location, null);
        Content rhodes = new Content("Rhodes", rhodesCoordinates, 10, R.drawable.greece_rhodes, R.drawable.greece_rhodes_selected, item2Location, null);
        Content delphi = new Content("Delphi", delphiCoordinates, 10, R.drawable.greece_delphi, R.drawable.greece_delphi_selected, item3Location, null);
        Content corfu = new Content("Corfu", corfuCoordinates, 10, R.drawable.greece_corfu, R.drawable.greece_corfu_selected, item4Location, null);
        Content sparta = new Content("Sparta", spartaCoordinates, 10, R.drawable.greece_sparta, R.drawable.greece_sparta_selected, item5Location, null);
        Content thessaloniki = new Content("Thessaloniki", thessalonikiCoordinates, 10, R.drawable.greece_thessaloniki, R.drawable.greece_thessaloniki_selected, item6Location, null);

        // puts all Content objects of Greece cities in an ArrayList
        ArrayList<Content> greeceCities = new ArrayList<>();
        greeceCities.add(athens);
        greeceCities.add(rhodes);
        greeceCities.add(delphi);
        greeceCities.add(thessaloniki);
        greeceCities.add(sparta);
        greeceCities.add(corfu);

        // returns Content object of Greece with ArrayList of Greece cities as nextList.
        return new Content("Greece", greeceCoordinates, 5, R.drawable.greece, R.drawable.greece_selected, item3Location, greeceCities);
    }

    private Content createItaly() {
        // coordinates of Italy and Italian cities
        LatLng italyCoordinates = new LatLng(42.5, 12.5);
        LatLng romeCoordinates = new LatLng(41.89193, 12.51133);
        LatLng veniceCoordinates = new LatLng(45.43713, 12.33265);
        LatLng florenceCoordinates = new LatLng(43.77925, 11.24626);
        LatLng pisaCoordinates = new LatLng(43.70853, 10.4036);
        LatLng naplesCoordinates = new LatLng(40.85631, 14.24641);
        LatLng milanCoordinates = new LatLng(45.4654219, 9.1859243);

        // creates Content objects for each Italian city
        Content rome = new Content("Rome", romeCoordinates, 10, R.drawable.italy_rome, R.drawable.italy_rome_selected, item1Location, null);
        Content venice = new Content("Venice", veniceCoordinates, 10, R.drawable.italy_venice, R.drawable.italy_venice_selected, item2Location, null);
        Content florence = new Content("Florence", florenceCoordinates, 10, R.drawable.italy_florence, R.drawable.italy_florence_selected, item3Location, null);
        Content pisa = new Content("Pisa", pisaCoordinates, 10, R.drawable.italy_pisa, R.drawable.italy_pisa_selected, item4Location, null);
        Content naples = new Content("Naples", naplesCoordinates, 10, R.drawable.italy_naples, R.drawable.italy_naples_selected, item5Location, null);
        Content milan = new Content("Milan", milanCoordinates, 10, R.drawable.italy_milan, R.drawable.italy_milan_selected, item6Location, null);

        // puts all Content objects of Italian cities in an ArrayList
        ArrayList<Content> italyCities = new ArrayList<>();
        italyCities.add(rome);
        italyCities.add(venice);
        italyCities.add(florence);
        italyCities.add(milan);
        italyCities.add(naples);
        italyCities.add(pisa);

        // returns Content object of Italy with ArrayList of Italian cities as nextList.
        return new Content("Italy", italyCoordinates, 5, R.drawable.italy, R.drawable.italy_selected, item4Location, italyCities);
    }

    private Content createPoland() {

        //Coordinates of Poland and Polish cities
        LatLng polandCoordinates = new LatLng(52, 19);
        LatLng warsawCoordinates = new LatLng(52.22977, 21.01178);
        LatLng krakowCoordinates = new LatLng(50.06143, 19.93658);
        LatLng lublinCoordinates = new LatLng(51.25, 22.5666);
        LatLng torunCoordinates = new LatLng(53.01375, 18.59814);
        LatLng poznanCoordinates = new LatLng(52.40692, 16.92993);
        LatLng gdanskCoordinates = new LatLng(54.35205, 18.64637);

        // creates Content objects for each Polish city
        Content warsaw = new Content("Warsaw", warsawCoordinates, 10, R.drawable.poland_warsaw, R.drawable.poland_warsaw_selected, item1Location, null);
        Content krakow = new Content("Krakow", krakowCoordinates, 10, R.drawable.poland_krakow, R.drawable.poland_krakow_selected, item2Location, null);
        Content lublin = new Content("Lublin", lublinCoordinates, 10, R.drawable.poland_lublin, R.drawable.poland_lublin_selected, item3Location, null);
        Content torun = new Content("Torun", torunCoordinates, 10, R.drawable.poland_torun, R.drawable.poland_torun_selected, item4Location, null);
        Content poznan = new Content("Poznan", poznanCoordinates, 10, R.drawable.poland_poznan, R.drawable.poland_poznan_selected, item5Location, null);
        Content gdansk = new Content("Gdansk", gdanskCoordinates, 10, R.drawable.poland_gdansk, R.drawable.poland_gdansk_selected, item6Location, null);

        // puts all Content objects of Polish cities in an ArrayList
        ArrayList<Content> cities = new ArrayList<>();
        cities.add(warsaw);
        cities.add(krakow);
        cities.add(lublin);
        cities.add(gdansk);
        cities.add(poznan);
        cities.add(torun);

        // returns Content object of Poland with ArrayList of Polish cities as nextList.
        return new Content("Poland", polandCoordinates, 5, R.drawable.poland, R.drawable.poland_selected, item5Location, cities);
    }

    private Content createSpain() {

        // Coordinates of Spain and Spanish cities
        LatLng spainCoordinates = new LatLng(40.5, -4);
        LatLng madridCoordinates = new LatLng(40.4165, -3.70256);
        LatLng barcelonaCoordinates = new LatLng(41.3850639, 2.1734035);
        LatLng sevilleCoordinates = new LatLng(37.38283, -5.97317);
        LatLng granadaCoordinates = new LatLng(37.18817, -3.60667);
        LatLng palmaCoordinates = new LatLng(39.5722, 2.6529);
        LatLng valenciaCoordinates = new LatLng(39.46975, -0.37739);

        // creates Content objects for each Spanish city
        Content madrid = new Content("Madrid", madridCoordinates, 10, R.drawable.spain_madrid, R.drawable.spain_madrid_selected, item1Location, null);
        Content barcelona = new Content("Barcelona", barcelonaCoordinates, 10, R.drawable.spain_barcelona, R.drawable.spain_barcelona_selected, item2Location, null);
        Content seville = new Content("Seville", sevilleCoordinates, 10, R.drawable.spain_seville, R.drawable.spain_seville_selected, item3Location, null);
        Content granada = new Content("Granada", granadaCoordinates, 10, R.drawable.spain_granada, R.drawable.spain_granada_selected, item4Location, null);
        Content palma = new Content("Palma", palmaCoordinates, 10, R.drawable.spain_palma, R.drawable.spain_palma_selected, item5Location, null);
        Content valencia = new Content("Valencia", valenciaCoordinates, 10, R.drawable.spain_valencia, R.drawable.spain_valencia_selected, item6Location, null);

        // puts all Content objects of Spanish cities in an ArrayList
        ArrayList<Content> cities = new ArrayList<>();
        cities.add(madrid);
        cities.add(barcelona);
        cities.add(seville);
        cities.add(valencia);
        cities.add(palma);
        cities.add(granada);

        // returns Content object of Spain with ArrayList of Spanish cities as nextList.
        return new Content("Spain", spainCoordinates, 5, R.drawable.spain, R.drawable.spain_selected, item6Location, cities);
    }

}
