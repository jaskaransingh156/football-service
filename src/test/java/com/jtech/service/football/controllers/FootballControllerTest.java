package com.jtech.service.football.controllers;

import com.jtech.service.football.models.TeamStanding;
import com.jtech.service.football.services.FootballService;
import com.jtech.service.football.utils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FootballControllerTest {
    @Mock
    private FootballService footballService;
    @InjectMocks
    private FootballController footballController;

    @Test
    public void testGetTeamStandingWithCorrectData() {
        // Given
        String countryName = "France";
        String leagueName = "Championship";
        String teamName = "London Tigers";

        // When
        Mockito.when(footballService.getTeamStanding(countryName, leagueName, teamName)).thenReturn(TestUtils.mockTeamStandingWithCountryId());
        TeamStanding teamStanding = footballController.getTeamStanding(countryName, leagueName, teamName);

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
    public void testGetTeamStandingWithNonExistentData() {
        // Given
        String countryName = "England";
        String leagueName = "Championship";
        String teamName = "London Tigers";

        // When
        Mockito.when(footballService.getTeamStanding(countryName, leagueName, teamName)).thenReturn(null);
        TeamStanding teamStanding = footballController.getTeamStanding(countryName, leagueName, teamName);

        // Then
        Assertions.assertThat(teamStanding).isNull();
    }
}