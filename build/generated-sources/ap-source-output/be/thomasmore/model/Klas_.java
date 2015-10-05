package be.thomasmore.model;

import be.thomasmore.model.Klastest;
import be.thomasmore.model.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-05T08:58:21")
@StaticMetamodel(Klas.class)
public class Klas_ { 

    public static volatile ListAttribute<Klas, Student> studentList;
    public static volatile SingularAttribute<Klas, Integer> id;
    public static volatile ListAttribute<Klas, Klastest> klastestList;
    public static volatile SingularAttribute<Klas, String> nummer;

}