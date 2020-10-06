package com.jtech.service.football.utils;

import com.jtech.service.football.models.Country;
import com.jtech.service.football.models.League;
import com.jtech.service.football.models.TeamStanding;

public class TestUtils {

    public static Country mockCountry() {
        return new Country("1", "France");
    }

    public static League mockLeague() {
        return new League("15", "Championship");
    }

    public static TeamStanding mockTeamStanding() {
        TeamStanding teamStanding = new TeamStanding();
        teamStanding.setCountryName("France");
        teamStanding.setLeagueId("15");
        teamStanding.setLeagueName("Championship");
        teamStanding.setTeamId("25");
        teamStanding.setTeamName("London Tigers");
        return teamStanding;
    }

    public static TeamStanding mockTeamStandingWithCountryId() {
        TeamStanding teamStanding = new TeamStanding();
        teamStanding.setCountryId("1");
        teamStanding.setCountryName("France");
        teamStanding.setLeagueId("15");
        teamStanding.setLeagueName("Championship");
        teamStanding.setTeamId("25");
        teamStanding.setTeamName("London Tigers");
        return teamStanding;
    }
}