package org.utn.interfaces;

import org.utn.recursos.AcademicResource;
@FunctionalInterface
public interface FilterResource {

    boolean evaluate(AcademicResource academicResource);
}
