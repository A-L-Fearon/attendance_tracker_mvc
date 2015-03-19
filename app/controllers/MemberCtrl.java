package controllers;

import models.Member;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.List;

/**
 * Created by fearon on 3/19/15.
 */
public class MemberCtrl extends Controller {

    private static Form<Member> memberForm = Form.form(Member.class);

    public static Result index() {
        return ok(form.render(memberForm));

    }

    public static Result members(){
        List<Member> memberList = Member.find.findList();
        return ok(index2.render(memberList));
    }

    public static  Result editMembers(String id){
        Member member = Member.find.byId(id);
        Form<Member> filledForm = memberForm.fill(member);
        return  ok(editMember.render(filledForm));
    }

    public static Result save(){
        Form<Member> boundForm = memberForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", String.format("Please fill out all fields in the form."));
            return badRequest(form.render(boundForm));
        }
        Member s = boundForm.get();
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
        return redirect(routes.MemberCtrl.members());
    }

}
