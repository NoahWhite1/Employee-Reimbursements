Feature: Manager Functions

	Scenario: Manager is updating a reimbursement
		Given The manager is on the manager page
		When The manager enters a reimbursement ID
		When The manager checks reimbursement status as approved or not
		When The manager enters a note or not
		When The manager presses the submit button
		Then The reimbursement is updated in the database
		