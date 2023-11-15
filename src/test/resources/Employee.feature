Feature: Employee functions 

	Scenario: Employee Login
		Given The employee is on the employee login page
		When The employee inputs their login credentials
		When The employee presses the login button
		Then The employee should be in the employee page
		
	Scenario: Employee Creating Reimbursement
		Given The employee is on the employee page
		When The employee types the reason for reimbursement
		When The employee enters the amount for a reimbursement
		When The employee clicks the submit button
		Then The reimbursement is added to the database
	