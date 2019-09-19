package com.geolocalization.localizatorapp.geoservice;

import com.geolocalization.localizatorapp.dao.Localization;
import com.geolocalization.localizatorapp.geoservice.exception.NearbyLocalizationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeoService {

    List<Localization> postLocalizations(List<Localization> localizations);

    String getNearestLocalization(Localization currentLoc) throws NearbyLocalizationNotFoundException;
}
