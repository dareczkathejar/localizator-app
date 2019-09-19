package com.geolocalization.localizatorapp.geoservice.impl;

import com.geolocalization.localizatorapp.dao.Localization;
import com.geolocalization.localizatorapp.dao.LocalizationPool;
import com.geolocalization.localizatorapp.geoservice.GeoService;
import com.geolocalization.localizatorapp.geoservice.exception.NearbyLocalizationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GeoServiceImpl implements GeoService {

    @Autowired
    LocalizationPool localizationPool;


    @Override
    public List<Localization> postLocalizations(List<Localization> localizations) {
        localizationPool.getLocalizations().addAll(localizations);
        return localizationPool.getLocalizations();
    }

    @Override
    public String getNearestLocalization(Localization currentLoc) throws NearbyLocalizationNotFoundException {
        if (localizationPool.getLocalizations().isEmpty()){
            throw new NearbyLocalizationNotFoundException("No saved locations found in the neighbourhood.");
        }
        Set<Double> distances = new HashSet<>();
        Localization nearestLoc = null;
        for (Localization loc : localizationPool.getLocalizations()){
            double distance = distanceInKmBetweenEarthCoordinates(
                    convertToDouble(loc.getLatitude()),
                    convertToDouble(loc.getLongitude()),
                    convertToDouble(currentLoc.getLatitude()),
                    convertToDouble(currentLoc.getLongitude()));
            if (distances.stream().filter(d -> d < distance).collect(Collectors.toList()).isEmpty()){
                nearestLoc = loc;
            }
            distances.add(distance);
        }
        if (Objects.isNull(nearestLoc)){
            throw new NearbyLocalizationNotFoundException("No saved locations found in the neighbourhood.");
        }
        return nearestLoc.getName();
    }

    private double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    private double distanceInKmBetweenEarthCoordinates(double lat1, double lon1, double lat2, double lon2) {
        double earthRadiusKm = 6371;

        double dLat = degreesToRadians(lat2-lat1);
        double dLon = degreesToRadians(lon2-lon1);

        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadiusKm * c;
    }

    private double convertToDouble(String parameter){
        return Double.valueOf(parameter);
    }

    public LocalizationPool getLocalizationPool() {
        return localizationPool;
    }

    public void setLocalizationPool(LocalizationPool localizationPool) {
        this.localizationPool = localizationPool;
    }
}
