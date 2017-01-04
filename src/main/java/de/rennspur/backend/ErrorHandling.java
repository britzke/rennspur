package de.rennspur.backend;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

	
	public class ErrorHandling extends WebApplicationException {
	     /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ErrorHandling(String message) {
	         super(Response.status(Response.Status.OK)
	             .entity(message).type(MediaType.TEXT_PLAIN).build());
	     }
	}

