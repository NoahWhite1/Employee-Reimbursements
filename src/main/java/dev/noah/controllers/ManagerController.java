package dev.noah.controllers;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dev.noah.entities.Employee;
import dev.noah.entities.Manager;
import dev.noah.entities.Reimbursement;
import dev.noah.services.ManagerService;
import dev.noah.services.ManagerServiceImpl;
import dev.noah.services.ReimbursementService;
import dev.noah.services.ReimbursementServiceImpl;
import io.javalin.http.Handler;

public class ManagerController {

	public static Gson gson = new Gson();
	public static ManagerService mserv = new ManagerServiceImpl();
	public static ReimbursementService rserv = new ReimbursementServiceImpl();
	
	
	public static Handler getManager = (ctx)->{
		String id = ctx.pathParam(":mId");
		Manager manager = mserv.getManagerById(Integer.parseInt(id));
		
		if(manager == null) {
			ctx.status(404);
			ctx.result("No manager was found.");
		}
		else {
			ctx.status(200);
			ctx.result(gson.toJson(manager));
		}
	};
	
	public static Handler getAllManagers = (ctx) ->{
		
		List<Manager> managers = mserv.getAllManagers();
		
		if(managers == null) {
			ctx.status(404);
			ctx.result("No managers were found.");
		}
		else {
			ctx.status(200);
			ctx.result(gson.toJson(managers));
		}
		
	};
	
	public static Handler updateManager = (ctx) -> {
		Manager manager = gson.fromJson(ctx.body(), Manager.class);
		mserv.updateManager(manager);
		
		if(manager == null) {
			ctx.status(404);
			ctx.result("Something went wrong updating the manager");
		}
		else {
			ctx.status(200);
			ctx.result(gson.toJson(manager));
		}
	};
	
	public static Handler updateReimbursement = (ctx) -> {
		JsonObject reimbursementJSON = gson.fromJson(ctx.body(), JsonObject.class);
		
		int rId = reimbursementJSON.get("rId").getAsInt();
		Boolean isApproved = reimbursementJSON.get("isApproved").getAsBoolean();
		Integer mId = reimbursementJSON.get("mId").getAsInt();
		String note = reimbursementJSON.get("note").toString();
		note = note.replace("\"","");
		
		Reimbursement reimbursement = rserv.getReimbursementById(rId);
		reimbursement.setApproved(isApproved);
		reimbursement.setApprovedBy(mId);
		reimbursement.setNote(note);
		
		
		System.out.println(reimbursement);
		
		if(rserv.getReimbursementById(rId) == null) {
			ctx.status(404);
			ctx.result("Something went wrong with updating a reimbursement");
		}
		else {
			mserv.updateReimbursement(reimbursement);
			ctx.status(200);
			ctx.result(gson.toJson(reimbursement));
		}
		
	};
	
}
