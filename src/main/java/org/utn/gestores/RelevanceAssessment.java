package org.utn.gestores;

import org.utn.recursos.AcademicResource;

public interface RelevanceAssessment {
    @FunctionalInterface
    interface ResourceFilter {
        boolean evaluate(AcademicResource resource);
    }
}
