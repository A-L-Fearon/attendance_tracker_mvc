package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by fearon on 3/19/15.
 */
@Entity
public class Records extends Model {

    @Id
    public Date date;
    public boolean Attended;
    public Integer dues;

    @ManyToOne
    public Member member;

    public static Finder<String, Records> find = new Finder<>(String.class, Records.class);
}
