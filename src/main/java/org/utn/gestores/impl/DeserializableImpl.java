package org.utn.gestores.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.utn.recursos.GeneralAcademicResources;
import org.utn.gestores.Deserializable;

import java.io.File;
import java.io.IOException;

public class DeserializableImpl implements Deserializable {
    @Override
    public GeneralAcademicResources getInitialData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GeneralAcademicResources generalAcademicResources = objectMapper.readValue(new File("/home/sebastianhernandez/Documentos-utn/Resources.json"), GeneralAcademicResources.class);
            return generalAcademicResources;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
