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
import java.util.List;

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
    @Value("${football-api.key}")
    private String apiKey;
    private RestTemplate restTemplate;
    private static final String ACTION_QUERY_PARAM = "action";
    private static final String API_KEY_QUERY_PARAM = "APIkey";

    @Inject
    public FootballApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Country> getCountries() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(footballApiUrl)
                .queryParam(ACTION_QUERY_PARAM ,getCountriesAction)
                .queryParam(API_KEY_QUERY_PARAM, apiKey);
        ResponseEntity<List<Country>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>(){});
        return response.getBody();
    }

    public List<League> getLeagues(String countryId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(footballApiUrl)
                .queryParam(ACTION_QUERY_PARAM, getLeaguesAction)
                .queryParam("country_id", countryId)
                .queryParam(API_KEY_QUERY_PARAM, apiKey);
        ResponseEntity<List<League>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<League>>(){});
        return response.getBody();
    }

    public List<TeamStanding> getStandings(String leagueId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(footballApiUrl)
                .queryParam(ACTION_QUERY_PARAM, getStandingsAction)
                .queryParam("league_id", leagueId)
                .queryParam(API_KEY_QUERY_PARAM, apiKey);
        ResponseEntity<List<TeamStanding>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<TeamStanding>>(){});
        return response.getBody();
    }
}