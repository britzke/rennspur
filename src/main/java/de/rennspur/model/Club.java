package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CLUBS database table.
 * 
 */
@Entity
@Table(name="CLUBS")
@NamedQuery(name="Club.findAll", query="SELECT c FROM Club c")
public class Club implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="DSV_NUMMER")
	private String dsvNummer;

	private String kürzel;

	private String name;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="club")
	private List<Event> events;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="club")
	private List<Team> teams;

	//bi-directional many-to-one association to TeamMember
	@OneToMany(mappedBy="club")
	private List<TeamMember> teamMembers;

	public Club() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDsvNummer() {
		return this.dsvNummer;
	}

	public void setDsvNummer(String dsvNummer) {
		this.dsvNummer = dsvNummer;
	}

	public String getKürzel() {
		return this.kürzel;
	}

	public void setKürzel(String kürzel) {
		this.kürzel = kürzel;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setClub(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setClub(null);

		return event;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Team addTeam(Team team) {
		getTeams().add(team);
		team.setClub(this);

		return team;
	}

	public Team removeTeam(Team team) {
		getTeams().remove(team);
		team.setClub(null);

		return team;
	}

	public List<TeamMember> getTeamMembers() {
		return this.teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public TeamMember addTeamMember(TeamMember teamMember) {
		getTeamMembers().add(teamMember);
		teamMember.setClub(this);

		return teamMember;
	}

	public TeamMember removeTeamMember(TeamMember teamMember) {
		getTeamMembers().remove(teamMember);
		teamMember.setClub(null);

		return teamMember;
	}

}