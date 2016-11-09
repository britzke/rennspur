package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TEAMS database table.
 * 
 */
@Entity
@Table(name="TEAMS")
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="HANDYCAP_FAKTOR")
	private int handycapFaktor;

	private String kennung;

	private String land;

	//bi-directional many-to-one association to Club
	@ManyToOne
	@JoinColumn(name="CLUBS_ID")
	private Club club;
	
	private String email;
	
	private String hash;

	//bi-directional many-to-one association to TeamMember
	@OneToMany(mappedBy="team")
	private List<TeamMember> teamMembers;

	//bi-directional many-to-one association to TeamPosition
	@OneToMany(mappedBy="team")
	private List<TeamPosition> teamPositions;

	public Team() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHandycapFaktor() {
		return this.handycapFaktor;
	}

	public void setHandycapFaktor(int handycapFaktor) {
		this.handycapFaktor = handycapFaktor;
	}

	public String getKennung() {
		return this.kennung;
	}

	public void setKennung(String kennung) {
		this.kennung = kennung;
	}

	public String getLand() {
		return this.land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	public List<TeamMember> getTeamMembers() {
		return this.teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public TeamMember addTeamMember(TeamMember teamMember) {
		getTeamMembers().add(teamMember);
		teamMember.setTeam(this);

		return teamMember;
	}

	public TeamMember removeTeamMember(TeamMember teamMember) {
		getTeamMembers().remove(teamMember);
		teamMember.setTeam(null);

		return teamMember;
	}

	public List<TeamPosition> getTeamPositions() {
		return this.teamPositions;
	}

	public void setTeamPositions(List<TeamPosition> teamPositions) {
		this.teamPositions = teamPositions;
	}

	public TeamPosition addTeamPosition(TeamPosition teamPosition) {
		getTeamPositions().add(teamPosition);
		teamPosition.setTeam(this);

		return teamPosition;
	}

	public TeamPosition removeTeamPosition(TeamPosition teamPosition) {
		getTeamPositions().remove(teamPosition);
		teamPosition.setTeam(null);

		return teamPosition;
	}

}