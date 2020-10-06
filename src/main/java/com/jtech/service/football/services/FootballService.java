package com.jtech.service.football.services;

import com.jtech.service.football.clients.FootballApiClient;
import com.jtech.service.football.models.Country;
import com.jtech.service.football.models.League;
import com.jtech.service.football.models.TeamStanding;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@Service
public class FootballService {
    private FootballApiClient footballApiClient;

    @Inject
    public FootballService(FootballApiClient footballApiClient) {
        this.footballApiClient = footballApiClient;
    }

    public TeamStanding getTeamStanding(String countryName, String leagueName, String teamName) {
        String countryId = this.getCountryId(countryName);
        if(Objects.nonNull(countryId)) {
            String leagueId = this.getLeagueId(countryId, leagueName);
            if(Objects.nonNull(leagueId)) {
                return this.getTeamStandingFromList(leagueId, teamName, countryId);
            }
        }
        return null;
    }

    private TeamStanding getTeamStandingFromList(String leagueId, String teamName, String countryId) {
        List<TeamStanding> teamStandingList = footballApiClient.getStandings(leagueId);
        if (!CollectionUtils.isEmpty(teamStandingList)) {
            for (TeamStanding teamStanding : teamStandingList) {
                if (Objects.nonNull(teamStanding) && teamStanding.getTeamName().equals(teamName)) {
                    teamStanding.setCountryId(countryId);
                    return teamStanding;
                }
            }
        }
        return null;
    }

    private String getCountryId(String countryName) {
        List<Country> countryList = footballApiClient.getCountries();
        if(!CollectionUtils.isEmpty(countryList)) {
            for(Country country: countryList) {
                if(Objects.nonNull(country) && country.getCountryName().equals(countryName)) {
                    return country.getCountryId();
                }
            }
        }
        return null;
    }

    private String getLeagueId(String countryId, String leagueName) {
        List<League> leagueList = footballApiClient.getLeagues(countryId);
        if(!CollectionUtils.isEmpty(leagueList)) {
            for(League league: leagueList) {
                if(Objects.nonNull(league) && league.getLeagueName().equals(leagueName)) {
                    return league.getLeagueId();
                }
            }
        }
        return null;
    }
}