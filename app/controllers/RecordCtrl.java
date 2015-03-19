package controllers;

import models.Member;
import models.Records;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by fearon on 3/19/15.
 */
public class RecordCtrl extends Controller {

    public static Result save(){
        Form<Records> boundForm = recordForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", String.format("Please fill out all fields in the form."));
            return badRequest(attendance.render(boundForm));
        }
        Records s = boundForm.get();
        s.save();
        flash("success", String.format("Successfully signed up %s %s", s.fname, s.lname));
        return redirect(routes.MemberCtrl.members());
    }

    public static Result update(){
        Form<Member> boundForm = memberForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", String.format("Please correct the form below."));
            return badRequest(editMember.render(boundForm));
        }
        Member u = boundForm.get();
        u.update();

        flash("success", String.format("Successfully updated member %s %s", u.fname, u.lname));
        return redirect(routes.MemberCtrl.members());
    }

    public static Result delete(String id) {
        Member member = Member.find.byId(id);
        if(member == null) {
            return notFound(String.format("Member does not exists."));
        }
        Member.find.ref(id).delete();

    }
