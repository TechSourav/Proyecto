package com.team.solventa.web.propertyeditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

//import com.arcadia.versions.manager.ApplicationManager;
//import com.arcadia.versions.manager.DocumentManager;
//import com.arcadia.versions.model.Application;
//import com.arcadia.versions.model.Document;

//CREO QUE ESTA CLASE NO ME HARÁ FALTA

@Component("bindingInitializer")
public class VersionesBindingInitializer implements WebBindingInitializer {

	//@Autowired
	//private ApplicationManager applicationManager;
	//@Autowired
	//private DocumentManager documentManager;

	@Override
	public void initBinder(final WebDataBinder binder, final WebRequest request) {
//		binder.registerCustomEditor(Application.class, new IntegerModelPropertyEditor(applicationManager));
//		binder.registerCustomEditor(Document.class, new IntegerModelPropertyEditor(documentManager));
	}

}