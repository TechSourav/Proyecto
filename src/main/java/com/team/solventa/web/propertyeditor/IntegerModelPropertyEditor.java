package com.team.solventa.web.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

import com.team.solventa.manager.Manager;
import com.team.solventa.model.IdEntity;

import javassist.tools.rmi.ObjectNotFoundException;

/**
 * Property Editor that only supports managers of type Manager<? extends IdEntity>.
 * @author Arcadia
 *
 */
public class IntegerModelPropertyEditor extends PropertyEditorSupport {

	private final Manager<? extends IdEntity> manager;

	
	@Override
	public void setAsText(final String text) {
		Integer id = null;
		try {
			if (StringUtils.isBlank(text) || text.equals("null")) {
				this.setValue(null);
				return;
			}

			id = this.getValueOf(text);
			IdEntity entity = this.manager.get(id);

			if(entity == null) {
				String msg = MessageFormat.format("Property editor: {0} - The entity to be transformed is null. Entity Id: {1}", this.getClass().getName(), id);
				throw new ObjectNotFoundException(msg);
			}


			this.setValue(entity);
		}catch(final Exception t) {
			//Error por log
		}
	}

	/**
	 * Method that returns the numeric value of the String text
	 * @param text
	 * @return numeric value of param
	 */
	protected Integer getValueOf(String text) {
		return Integer.valueOf(text);
	}


	

	/**
	 * Returns the manager used by this property editor.
	 *
	 * @return the manager used by this property editor.
	 */
	protected Manager<?> getManager() {
		return this.manager;
	}
	
	/**
	 * For subclasses.
	 * 
	 * @param manager  a manager.
	 */
	public IntegerModelPropertyEditor(Manager<?> manager) {
		super();
		this.manager = manager;
	}

}
