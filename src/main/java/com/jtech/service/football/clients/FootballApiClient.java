package com.jtech.service.football.clients;

import com.jtech.service.football.models.Country;
import com.jtech.service.football.models.League;
import com.jtech.service.football.models.TeamStanding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FootballApiClient {
    @Value("${football-api.url}")
    private String footballApiUrl;
    @Value("${football-api.action.get-countries}")
    private String getCountriesAction;
    @Value("${football-api.action.get-leagues}")
    private String getLeaguesAction;
    @Value("${football-api.action.get-standings}")
    private String getStandingsAction;
    private RestTemplate restTemplate;

    @Inject
    public FootballApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Country> getCountries() {
        String url = footballApiUrl + getCountriesAction;
        ResponseEntity<List<Country>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>(){});
        return response.getBody();
    }

    public List<League> getLeagues(String countryId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(footballApiUrl + getLeaguesAction);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("country_id", countryId);
        ResponseEntity<List<League>> response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<League>>(){});
        return response.getBody();
    }

    public List<TeamStanding> getStandings(String leagueId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(footballApiUrl + getStandingsAction);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("league_id", leagueId);
        ResponseEntity<List<TeamStanding>> response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<TeamStanding>>(){});
        return response.getBody();
    }
}