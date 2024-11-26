package org.utn.gestores.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.utn.exception.ResourceNotFoundException;
import org.utn.recursos.GeneralAcademicResources;
import org.utn.interfaces.Deserializable;

import java.io.File;
import java.io.IOException;

public class DeserializableImpl implements Deserializable {
    @Override
    public GeneralAcademicResources getInitialData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GeneralAcademicResources generalAcademicResources = objectMapper.readValue(new File("Resources.json"), GeneralAcademicResources.class);
            return generalAcademicResources;
        } catch (JsonMappingException e) {
            throw new ResourceNotFoundException("No se ha Encontado el Recurso");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
