package de.rennspur.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author e4_schlender
 */
public class Wrapper {

	private List<TeamPosition> positions;

	/**
	* 
	*/
	public Wrapper() {
	}

	/**
	 * @param positions
	 */
	public Wrapper(List<TeamPosition> positions) {
		this.positions = positions;
	}

	/**
	 * @return the positions
	 */
	public List<TeamPosition> getPositions() {
		return positions;
	}

	/**
	 * @param positions
	 *            the positions to set
	 */
	public void setPositions(List<TeamPosition> positions) {
		this.positions = positions;
	}
}