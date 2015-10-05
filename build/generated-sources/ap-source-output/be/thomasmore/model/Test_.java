package be.thomasmore.model;

import be.thomasmore.model.Klastest;
import be.thomasmore.model.Score;
import be.thomasmore.model.Vak;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-05T08:58:21")
@StaticMetamodel(Test.class)
public class Test_ { 

    public static volatile SingularAttribute<Test, String> beschrijving;
    public static volatile ListAttribute<Test, Score> scoreList;
    public static volatile SingularAttribute<Test, Vak> vakId;
    public static volatile SingularAttribute<Test, Integer> totaalScore;
    public static volatile SingularAttribute<Test, Integer> id;
    public static volatile ListAttribute<Test, Klastest> klastestList;

}