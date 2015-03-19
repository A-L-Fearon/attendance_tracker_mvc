package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fearon on 3/19/15.
 */
@Entity
public class Member extends Model {
    @Id
    @Constraints.Required
    public String id;
    @Constraints.Required
    public String fname;
    @Constraints.Required
    public String lname;
    public String email;
    @OneToMany
    public List<Records> attendance = new ArrayList<>();

    public static Finder<String,Member> find = new Finder<>(String.class, Member.class);

}
