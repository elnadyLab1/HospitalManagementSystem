package Comparator;

import Entity.PatientEntity;

import java.util.Comparator;

public class NameComperator implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        PatientEntity patient = (PatientEntity)o;
        PatientEntity patient2 = (PatientEntity)t1;
        return patient.getName().compareTo(patient2.getName());
    }
}
