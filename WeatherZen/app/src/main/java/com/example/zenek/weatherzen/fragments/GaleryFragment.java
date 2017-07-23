package com.example.zenek.weatherzen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zenek.weatherzen.R;
import com.example.zenek.weatherzen.adapters.EventAdapter;
import com.example.zenek.weatherzen.adapters.GaleryAdapte;
import com.example.zenek.weatherzen.core.BaseFragment;
import com.example.zenek.weatherzen.models.Event;
import com.example.zenek.weatherzen.models.Galery;
import com.example.zenek.weatherzen.models.Galery_Table;
import com.example.zenek.weatherzen.models.town.Town;
import com.example.zenek.weatherzen.models.town.Town_Table;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Log;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenek on 04.06.2017.
 */

public class GaleryFragment extends BaseFragment {
    ViewPager viewPager;
    GaleryAdapte adapter;
    private static final String ID = "id";
    List<Galery> galeryList;
    Galery galery,galery1,galery2,galery3,galery4,galery5,galery6,galery7,galery8,galery9,galery10,galery11,galery12,galery13;

    Town town;
    int townId;

    public static GaleryFragment newInstance(int id) {
        GaleryFragment fragment = new GaleryFragment();
        Bundle args = new Bundle();
        args.putInt(ID, id);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        townId = getArgumentInt(ID, -1);
        town = SQLite.select().from(Town.class).where(Town_Table.id.eq(townId)).querySingle();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.galery_fragmentr, container, false);
        findViews(view);
        testData();
        prepareSlider();

        return view;
    }
    public void testData(){
        galeryList=new ArrayList<>();

            galery=new Galery();
            galery1=new Galery();
            galery2=new Galery();
        galery3 =new Galery();galery4=new Galery();galery5=new Galery();galery6=new Galery();galery7=new Galery();galery8=new Galery();galery9=new Galery();galery10=new Galery();galery11=new Galery();galery12=new Galery();galery13=new Galery();

        SQLite.delete().from(Galery.class).queryList();
        galery.setUrl("http://www.faktykaliskie.pl/gfx/faktykaliskie/_thumbs/pl/defaultaktualnosci/102/847/1/b_1021,qop-7WeanlrZqr7XV5Ka.jpg");
        galery.setName("Sobotni wieczór: kaliszanie w bibliotekach i kościołach ZDJĘCIA");
        galery.setDescription("Moment wyciszenia i możliwość przebywania z naturą, która na co dzień zarezerwowana jest tylko dla zakonników kościoła Ojców Jezuitów. Wczoraj otworzono dla kaliszan wrota do ogrodów przy kościele pobernardyńskim przy ul. Stawiszyńskiej. Kaliszanie mogli spacerować krętymi alejami, usiąść w ogrodzie i cieszyć się ciszą i widokiem bujnej roślinności.");
        galery.setTownName("Warszawa");
        galery.save();

        galery1.setName("Miasto uczciło obrońców Domu Katolickiego");
        galery1.setDescription("Na placu Powstańców Wielkopolskich odbyły się uroczystości z okazji 57. rocznicy Wydarzeń Zielonogórskich. 30 maja 1960 r. ok. pięciu tysięcy mieszkańców starło się z milicją i ZOMO w obronie eksmitowanego Domu Katolickiego.");
        galery1.setUrl("http://bi.gazeta.pl/im/0a/dd/14/z21876746V,57--rocznica-Wydarzen-Zielonogorskich.jpg");
        galery1.save();

        //Lodz
        galery2.setTownName("Łódź");
        galery2.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/fb/d2/5808b3474d5f8_o,size,933x0,q,70,h,9854a4.jpg");
        galery2.setName("Koncert Ariany Grande");
        galery2.setDescription("\n" +
                "Ariana Grande wystąpi 1 czerwca w łódzkiej Atlas Arenie. Przedsprzedaż biletów rozpocznie się 26 października o godzinie 10 na www.LiveNation.pl. Bilety w cenie od 199 zł wejdą do ogólnej sprzedaży 28 października o godzinie 10 na www.LiveNation.pl. ");
        galery2.save();

        galery3.setTownName("Łódź");
        galery3.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/4d/7f/593e38ae69fa8_o,size,933x0,q,70,h,cc271c.jpg");
        galery3.setName("Eliminacje do Przystanku Woodstock 2017: Nocny Kochanek najlepszy!");
        galery3.setDescription("W warszawskim klubie Progresja zakończyły się w sobotę koncerty Eliminacji do 23. Przystanku Woodstock. Do muzycznych rywalizacji stanęło 26 zespołów z całej Polski, wyłonionych spośród ponad 850 kapel, które nadesłały swoje płyty do Fundacji WOŚP - organizatora Eliminacji. [PATRONAT NaM]");
        galery3.save();

        galery4.setTownName("Łódź");
        galery4.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/66/ff/59476fad96c41_o,size,933x0,q,70,h,105714.jpg");
        galery4.setName("Sławno: Ostatni Maraton na raty [WYNIKI]");
        galery4.setDescription("Po raz osiemnasty i ostatni w sezonie 2016/2017 na ścieżce rowerowej i na stadionie miał miejsce Maraton na Raty. Przed biegaczami już tylko ceremonia wręczenia pucharów, medali i nagród, która odbędzie się 24 czerwca 2017r., po Biegach Śniadaniowych na sławieńskim stadionie. Biegi śniadaniowe startują o godz. 8.30.");
        galery4.save();



        //Krakow
        galery5.setTownName("Krakow");
        galery5.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/cc/53/591227450227a_o,size,933x0,q,70,h,a13e2e.jpg");
        galery5.setName("Warsaw Comic Con rusza już 19 MAJA! Nie, to nie jest żart!");
        galery5.setDescription("Nie możecie doczekać się pierwszego w Polsce Warsaw Comic Conu? Rozgrzewka przed tym niesamowitym festiwalem rusza już w ten piątek, 19 maja. W sześciu Multikinach w całej Polsce: Warszawie, Katowicach, Krakowie, Poznaniu i Wrocławiu organizatorzy zaplanowali nocny maraton serialowy! Każdy posiadacz biletu na Warsaw Comic Con może bezpłatnie obejrzeć cały szósty sezon „Gry o Tron” bądź – w zależności od miasta – seriale „Legion” lub „Outcast”.");
        galery5.save();

        galery6.setTownName("Krakow");
        galery6.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/15/49/58ca74fd36303_o,size,933x0,q,70,h,241709.jpg");
        galery6.setName("Metallica w Polsce. Legendarny zespół zagra w 2018 roku w Krakowie");
        galery6.setDescription("28 kwietnia 2018 roku legendarna, metalowa grupa Metallica wystąpi na jedynym koncercie w Polsce. Występ odbędzie się w krakowskiej Tauron Arenie.");
        galery6.save();

        galery7.setTownName("Krakow");
        galery7.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/24/bc/58b421674a4a8_o,size,933x0,q,70,h,22d99d.jpg");
        galery7.setName("W.A.S.P. w Polsce. Kultowa grupa zagra w Warszawie w ramach wyjątkowej trasy");
        galery7.setDescription("Jeden z nielicznych w muzyce heavy metalowej concept albumów, \"The Crimson Idol\" grupy W.A.S.P., obchodzi w tym roku 25. urodziny. Z tej okazji Blackie Lawless wraz z zespołem ruszają w trasę, podczas której zagrają cały materiał z kultowej płyty, a także największe hity grupy. W.A.S.P. w Polsce zagrają 25 listopada w warszawskiej Progresji. Bilety od 90 złotych.");
        galery7.save();

        //Warszawa
        galery8.setTownName("Warszawa");
        galery8.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/0a/94/5937a3c283298_o,size,933x0,q,70,h,be769b.jpg");
        galery8.setName("Wianki nad Wisłą 2017. Wiemy, kto zagra w tym roku na Podzamczu");
        galery8.setDescription("Wianki nad Wisłą 2017 odbędą się 24 czerwca. Coroczna impreza na Podzamczu tradycyjnie będzie bezpłatna. Organizatorzy podali też, kto wystąpi w tym roku. Wśród zaproszonych artystów znalazła się m.in. ikona muzyki elektronicznej.");
        galery8.save();

        galery9.setTownName("Warszawa");
        galery9.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/f0/22/5940370e3780f_o,size,933x0,q,70,h,e4383e.jpg");
        galery9.setName("Kino Iluzjon zaprasza na darmowe pokazy filmowe!");
        galery9.setDescription("Kino Iluzjon (ul. Narbutta 50a) zaprasza na darmowe projekcje, które będą trwały, aż do 22 czerwca! Jest to idealna propozycja na letnie popołudnia i wieczory. Możecie wybrać się do kina z przyjaciółmi, rodziną i obejrzeć filmy zupełnie za darmo! \n" +
                "\n" +
                "W repertuarze znajdziecie m.in. \n" +
                "- „Cyrk straceńców”, czyli film z 1969 r. w reżyserii Johna Frankenheimera, \n" +
                "- „Słonia”, film Gusa Van Santa, który jest zapisem jednego dnia z życia amerykańskiej szkoły średniej w Columbii, \n" +
                "- „Psychozę” w reżyserii Alfreda Hitchcocka, jeden z najbardziej niepokojących filmów w historii kina, \n" +
                "- czy „Obywatela Milka” – film biograficzny Harveya Milka, pierwszego polityka, który odważył się otwarcie przyznać do swojej homoseksualnej orientacji ");
        galery9.save();

        galery10.setTownName("Warszawa");
        galery10.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/0d/70/59352dfb008a3_o,size,933x0,q,70,h,9522df.jpg");
        galery10.setName("Darmowe koncerty w Warszawie w czerwcu. Brodka, Fisz, Taco Hemingway, Acid Drinkers");
        galery10.setDescription("Darmowe koncerty w Warszawie. W czerwcu fani polskiej muzyki muszą być w siódmym niebie - na darmowych koncertach będzie można usłyszeć największe gwiazdy rodzimej sceny muzycznej ostatnich lat. Od braci Waglewskich, przez Brodkę i Taco Hemingwaya aż po weteranów sceny rockowej, czyli Acid Drinkers. Kliknij zdjęcie, aby przejść do przeglądu darmowych koncertów!");
        galery10.save();

        //Wroclaw
        galery11.setTownName("Wrocław");
        galery11.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/66/13/5947171f98d2a_o,size,933x0,q,70,h,64e6cf.jpg");
        galery11.setName("Wroclove Fest 2017. Zobaczcie zdjęcia z koncertów [ZDJĘCIA, GALERIA UCZESTNIKÓW]");
        galery11.setDescription("Za nami pierwsze dni koncertowe (16 i 17 czerwca) w ramach Wroclove Fest. Na scenie Centrum Historii Zajezdnia przy ulicy Grabiszyńskiej pojawili się m.in.: Jelonek, Skubas, Hey i Miuosh. Dzisiaj, 19 czerwca, w Centrum Koncertowym A2 przy ulicy Góralskiej zagra punkrockowy zespól Flogging Molly. Zobaczcie zdjęcia.");
        galery11.save();

        galery12.setTownName("Wrocław");
        galery12.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/fb/81/594712a7eaeef_o,size,933x0,q,70,h,c5ec88.jpg");
        galery12.setName("5. PKO Nocny Wrocław Półmaraton [ZDJĘCIA UCZESTNIKÓW]. Szukajcie się na zdjęciach!");
        galery12.setDescription("Za nami 5. edycja PKO Nocnego Wrocław Półmaratonu. W biegu wzięło udział około 9700 osób. Jako pierwszy do mety dobiegłEtiopczyk, Mengistu Zelalem. W grupie kobiet zwyciężyła Olga Ochal. Znajdźcie się na zdjęciach.");
        galery12.save();

        galery13.setTownName("Wrocław");
        galery13.setName("Stary Jarosław: Dni Gminy Darłowo - świetna zabawa! [ZDJĘCIA] - 2017 rok");
        galery13.setUrl("https://d-nm.ppstatic.pl/kadr/k/r/7f/aa/5947968c7335d_o,size,933x0,q,70,h,a0d8e8.jpg");
        galery13.setDescription("To były wybornie Dni Gminy Darłowo. Imprezę zorganizowano w Starym Jarosławiu. Na scenie wystąpiła m.in. Daria Dydyna, zespoły disco polo - Exaited, Piękni i Młodzi. Oczywiście wszystko było w piknikowej atmosferze!");
        galery13.save();

        galeryList=SQLite.select().from(Galery.class).where(Galery_Table.townName.eq(town.getName())).queryList();
        Log.v("dd",galeryList);


    }
    private void prepareSlider() {
        if (adapter == null) {
            if(galeryList!=null)
            adapter = new GaleryAdapte(getContext(),galeryList);
        }
        viewPager.setAdapter(adapter);

    }

    private void findViews(View view) {
        viewPager=(ViewPager) view.findViewById(R.id.pager);

    }
}
