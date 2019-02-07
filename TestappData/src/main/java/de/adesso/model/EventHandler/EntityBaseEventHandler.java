/**
 * 
 */
package de.adesso.model.EventHandler;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.adesso.model.EntityBase;

/**
 * @author hayber
 *
 */

public class EntityBaseEventHandler {

	private static final Logger logger = LogManager.getLogger(EntityBaseEventHandler.class);

	@PrePersist
	public void prePersist(Object object) {
		EntityBase entityBase = (EntityBase) object;
		logger.debug("Vor dem Persistieren EntityBase Entity : {}", entityBase);
	}

	@PostPersist
	public void postPersist(Object object) {
		EntityBase entityBase = (EntityBase) object;
		logger.debug("Nach dem Persistieren EntityBase Entity : {}", entityBase);
	}

}
