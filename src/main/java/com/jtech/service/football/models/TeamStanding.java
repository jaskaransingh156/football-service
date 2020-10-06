package com.jtech.service.football.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamStanding {
    private String countryId;
    private String countryName;
    private String leagueId;
    private String leagueName;
    private String teamId;
    private String teamName;
    private String overallLeaguePosition;
}