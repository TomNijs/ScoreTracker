package be.thomasmore.model;

import be.thomasmore.model.Klas;
import be.thomasmore.model.Score;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-05T08:58:21")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile ListAttribute<Student, Score> scoreList;
    public static volatile SingularAttribute<Student, Integer> studentenNr;
    public static volatile SingularAttribute<Student, Klas> klasId;
    public static volatile SingularAttribute<Student, String> voornaam;
    public static volatile SingularAttribute<Student, Integer> id;
    public static volatile SingularAttribute<Student, String> naam;
    public static volatile SingularAttribute<Student, String> email;

}