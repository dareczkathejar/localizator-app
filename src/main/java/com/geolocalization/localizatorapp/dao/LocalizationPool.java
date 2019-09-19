package com.geolocalization.localizatorapp.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocalizationPool {

    private List<Localization> localizations;

    public LocalizationPool() {
        this.localizations = new ArrayList<>();
    }

    public List<Localization> getLocalizations() {
        return localizations;
    }

    public void setLocalizations(List<Localization> localizations) {
        this.localizations = localizations;
    }
}
