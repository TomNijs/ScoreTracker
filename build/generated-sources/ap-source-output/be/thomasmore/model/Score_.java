package be.thomasmore.model;

import be.thomasmore.model.Student;
import be.thomasmore.model.Test;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-05T08:58:21")
@StaticMetamodel(Score.class)
public class Score_ { 

    public static volatile SingularAttribute<Score, Student> studentId;
    public static volatile SingularAttribute<Score, Integer> score;
    public static volatile SingularAttribute<Score, Test> testId;
    public static volatile SingularAttribute<Score, Integer> id;

}