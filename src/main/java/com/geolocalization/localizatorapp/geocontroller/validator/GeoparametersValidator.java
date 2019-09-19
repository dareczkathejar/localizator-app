package com.geolocalization.localizatorapp.geocontroller.validator;

import com.geolocalization.localizatorapp.dao.Localization;
import com.geolocalization.localizatorapp.geocontroller.exception.WrongParameterFormatException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GeoparametersValidator {

    private Pattern geoparameterPattern = Pattern.compile("\\d{2}\\.\\d*");

    public void validate(List<Localization> localizations){
        localizations.forEach(localization -> {
            validateParameter(localization.getLatitude());
            validateParameter(localization.getLongitude());
        });
    }

    private void validateParameter(String parameter){
        Matcher paramMatcher = geoparameterPattern.matcher(parameter);
        if (!paramMatcher.find()){
            throw new WrongParameterFormatException();
        }
    }


}
