package com.jtech.service.football.controllers;

import javax.inject.Inject;

import com.jtech.service.football.models.TeamStanding;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.jtech.service.football.services.FootballService;

@RestController
@RequestMapping(value = "football-service/", produces = MediaType.APPLICATION_JSON_VALUE)
public class FootballController {
	private FootballService footballService;

	@Inject
	public FootballController(FootballService footballService) {
		this.footballService = footballService;
	}

	@GetMapping("team-standing")
	public TeamStanding getTeamStanding(@RequestParam String countryName, @RequestParam String leagueName, @RequestParam String teamName) {
		return footballService.getTeamStanding(countryName, leagueName, teamName);
	}
}