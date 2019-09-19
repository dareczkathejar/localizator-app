package com.geolocalization.localizatorapp.geoservice.impl;

import com.geolocalization.localizatorapp.dao.Localization;
import com.geolocalization.localizatorapp.dao.LocalizationPool;
import com.geolocalization.localizatorapp.geoservice.GeoService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GeoServiceTest {

    private LocalizationPool localizationPool  = new LocalizationPool();

    private GeoService geoService  = new GeoServiceImpl();

    @Before
    public void setUp() throws Exception {
        localizationPool = new LocalizationPool();
        geoService = new GeoServiceImpl();
        ((GeoServiceImpl) geoService).setLocalizationPool(localizationPool);
    }

    @Test
    public void postLocalizations() {
        //given
        List<Localization> localizations = Arrays.asList(
                new Localization("Store", "55.555555", "66.666666"),
                new Localization("Parking", "44.444444", "66.777777"),
                new Localization("Cinema", "54.545445", "68.77777"));
        //when
        geoService.postLocalizations(localizations);
        //then
        assertThat(localizationPool.getLocalizations().isEmpty(),is(false));
        assertThat(localizationPool.getLocalizations().size(), is(3));
    }

    @Test
    public void getNearestLocalization() {
        //given
        List<Localization> localizations = Arrays.asList(
                new Localization("Store", "55.555555", "66.666666"),
                new Localization("Parking", "44.444444", "66.777777"),
                new Localization("Cinema", "54.545445", "68.77777"));
        Localization currentLoc = new Localization("Home", "55.44444", "66.77777");
        localizationPool.setLocalizations(localizations);
        //when
        String nearestLocalization = geoService.getNearestLocalization(currentLoc);
        //then
        assertThat(nearestLocalization, is("Store"));
    }
}