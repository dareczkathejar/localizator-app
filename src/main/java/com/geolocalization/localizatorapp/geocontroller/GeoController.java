package com.geolocalization.localizatorapp.geocontroller;

import com.geolocalization.localizatorapp.dao.Localization;
import com.geolocalization.localizatorapp.geocontroller.validator.GeoparametersValidator;
import com.geolocalization.localizatorapp.geoservice.GeoService;
import com.geolocalization.localizatorapp.geoservice.exception.NearbyLocalizationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class GeoController {

    @Autowired
    private GeoparametersValidator validator;

    @Autowired
    private GeoService geoService;

    @RequestMapping(value = "/localizations", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public List<Localization> postLocalizations(@RequestBody List<Localization> localizations) {
        validator.validate(localizations);
        return geoService.postLocalizations(localizations);
    }

    @RequestMapping(value = "/nearestLocalization", method = RequestMethod.GET, consumes = "application/json")
    public String getNearestLocalization(@RequestBody Localization currentLoc) throws NearbyLocalizationNotFoundException {
        return geoService.getNearestLocalization(currentLoc);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(){
        return "HELLO";
    }
}
