package dev.noah.controllers;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import dev.noah.entities.Reimbursement;
import dev.noah.services.ReimbursementService;
import dev.noah.services.ReimbursementServiceImpl;
import io.javalin.http.Handler;

public class ReimbursementController {

	public static Gson gson = new Gson();
	public static ReimbursementService rserv = new ReimbursementServiceImpl();
	
	public static Handler createReimbursement = (ctx) -> {
		Reimbursement reimbursement = gson.fromJson(ctx.body(), Reimbursement.class);
		rserv.createReimbursement(reimbursement);
		
		if(reimbursement == null) {
			ctx.status(404);
			ctx.result("Something went wrong with reimbursement service creation");
		}
		else {
			ctx.status(201);
			ctx.result(gson.toJson(reimbursement));
		}
	};
	
	public static Handler getReimbursement = (ctx)->{
		String id = ctx.pathParam(":rId");
		Reimbursement reimbursement = rserv.getReimbursementById(Integer.parseInt(id));
		
		if(reimbursement == null) {
			ctx.status(404);
			ctx.result("Reimbursement was not found.");
		}
		else {
			ctx.status(200);
			ctx.result(gson.toJson(reimbursement));
		}
	};
	
	public static Handler getAllReimbursements = (ctx) -> {
		List<Reimbursement> reimbursements = rserv.getAllReimbursements();
		if(reimbursements == null) {
			ctx.status(404);
			ctx.result("No reimbursements were found.");
		}
		else {
			ctx.status(200);
			ctx.result(gson.toJson(reimbursements));
		}
		
	};
	
	
	public static Handler updateReimbursement = (ctx) -> {
		Reimbursement reimbursement = gson.fromJson(ctx.body(), Reimbursement.class);
		rserv.updateReimbursement(reimbursement);
		
		if(reimbursement == null) {
			ctx.status(404);
			ctx.result("Something went wrong with updating the reimbursement");
		}
		else {
			ctx.status(200);
			ctx.result(gson.toJson(reimbursement));
		}
	};
	
}
