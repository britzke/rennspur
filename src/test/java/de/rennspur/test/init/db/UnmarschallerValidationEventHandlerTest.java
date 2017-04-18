/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2017  burghard.britzke, bubi@charmides.in-berlin.de
 *  
 *  Rennspur is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  Rennspur is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with Rennspur.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.rennspur.test.init.db;

import static org.junit.Assert.*;

import java.net.URL;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventLocator;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

import de.rennspur.init.db.UnmarschallerValidationEventHandler;

/**
 * Tests the UnmarschallerValidationEventHandler unit.
 *
 * @author burghard.britzke
 */
public class UnmarschallerValidationEventHandlerTest {

	private final class ValidationEventImpl implements ValidationEvent {
		private final class ValidationEventLocatorImpl implements ValidationEventLocator {
			private boolean getURLCalled;
			private boolean getColNumberCalled;

			@Override
			public URL getURL() {
				getURLCalled = true;
				return null;
			}

			@Override
			public int getOffset() {
				return 0;
			}

			@Override
			public Object getObject() {
				return null;
			}

			@Override
			public Node getNode() {
				return null;
			}

			@Override
			public int getLineNumber() {
				boolean getLineNumberCalled = true;
				return 0;
			}

			@Override
			public int getColumnNumber() {
				getColNumberCalled = true;
				return 0;
			}

			/**
			 * @return the getURLCalled
			 */
			public boolean isGetURLCalled() {
				return getURLCalled;
			}

			/**
			 * @return the getColNumberCalled
			 */
			public boolean isGetColNumberCalled() {
				return getColNumberCalled;
			}
		}

		private boolean getLocatorCalled;
		private boolean getMessageCalled;

		/**
		 * Returns ValidationEvent.ERROR - every time for test purposes.
		 * 
		 * @see javax.xml.bind.ValidationEvent#getSeverity()
		 */
		@Override
		public int getSeverity() {

			return ValidationEvent.ERROR;
		}

		@Override
		public String getMessage() {
			getMessageCalled = true;
			return null;
		}

		@Override
		public ValidationEventLocator getLocator() {
			getLocatorCalled = true;
			if (locator == null) {
				locator = new ValidationEventLocatorImpl();
			}
			return locator;
		}

		@Override
		public Throwable getLinkedException() {
			return null;
		}

		/**
		 * @return the getLocatorCalled
		 */
		public boolean isGetLocatorCalled() {
			return getLocatorCalled;
		}

		/**
		 * @return the getMessageCalled
		 */
		public boolean isGetMessageCalled() {
			return getMessageCalled;
		}
	}

	private UnmarschallerValidationEventHandler proband;
	private ValidationEventLocator locator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		proband = new UnmarschallerValidationEventHandler();

	}

	/**
	 * Test method for
	 * {@link de.rennspur.init.db.UnmarschallerValidationEventHandler#handleEvent(javax.xml.bind.ValidationEvent)}.
	 */
	@Test
	public void testHandleEvent() {
		ValidationEventImpl ve;
		ve = new ValidationEventImpl();
		// 2. send test message
		proband.handleEvent(ve);
		// 3. proof result
		assertTrue("getLocator() must be called on ValidationEvent", ve.isGetLocatorCalled());
		assertTrue("getMessage() must be called on ValidationEvent", ve.isGetMessageCalled());
		assertTrue("getURL() must be called on the validationEvent's validationEventLocator",
				((de.rennspur.test.init.db.UnmarschallerValidationEventHandlerTest.ValidationEventImpl.ValidationEventLocatorImpl) ve
						.getLocator()).isGetURLCalled());
	}

}
