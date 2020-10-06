package com.jtech.service.football.services;

import com.jtech.service.football.clients.FootballApiClient;
import com.jtech.service.football.models.TeamStanding;
import com.jtech.service.football.utils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class FootballServiceTest {
    @Mock
    private FootballApiClient footballApiClient;
    @InjectMocks
    private FootballService footballService;

    @Test
    public void testGetTeamStandingWithCorrectData() {
        // Given
        String countryName = "France";
        String leagueName = "Championship";
        String teamName = "London Tigers";

        // When
        Mockito.when(footballApiClient.getCountries()).thenReturn(Collections.singletonList(TestUtils.mockCountry()));
        Mockito.when(footballApiClient.getLeagues("1")).thenReturn(Collections.singletonList(TestUtils.mockLeague()));
        Mockito.when(footballApiClient.getStandings("15")).thenReturn(Collections.singletonList(TestUtils.mockTeamStanding()));
        TeamStanding teamStanding = footballService.getTeamStanding(countryName, leagueName, teamName);

        // Then
        Assertions.assertThat(teamStanding).isNotNull();
        Assertions.assertThat(teamStanding.getCountryId()).isEqualTo("1");
        Assertions.assertThat(teamStanding.getCountryName()).isEqualTo("France");
        Assertions.assertThat(teamStanding.getLeagueId()).isEqualTo("15");
        Assertions.assertThat(teamStanding.getLeagueName()).isEqualTo("Championship");
        Assertions.assertThat(teamStanding.getTeamId()).isEqualTo("25");
        Assertions.assertThat(teamStanding.getTeamName()).isEqualTo("London Tigers");
    }

    @Test
    public void testGetTeamStandingWithNonExistentLeague() {
        // Given
        String countryName = "France";
        String leagueName = "Champion";
        String teamName = "London Tigers";

        // When
        Mockito.when(footballApiClient.getCountries()).thenReturn(Collections.singletonList(TestUtils.mockCountry()));
        Mockito.when(footballApiClient.getLeagues("1")).thenReturn(Collections.singletonList(TestUtils.mockLeague()));
        TeamStanding teamStanding = footballService.getTeamStanding(countryName, leagueName, teamName);

        // Then
        Assertions.assertThat(teamStanding).isNull();
    }
}