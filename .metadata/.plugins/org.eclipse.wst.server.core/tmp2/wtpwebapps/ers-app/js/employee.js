document.getElementById('pending-reimbursement').onclick = displayPending;

function displayPending() {
	var xhr = new XMLHttpRequest();
	
	xhr.open("GET", "../ViewPendingReimbursements", true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var response = JSON.parse(xhr.responseText);
			getPendingReimbursements(response);
		}
	}
	
}

function getPendingReimbursements(response) {
	var pendingTable = document.getElementById('pending-table');
	
	for(let i = 0; i<response.length; i++) {
		let appendPending = 
		"<tr>" +
			"<td  id='rId'>" + response[i].reimbursementId + "</td>" +
			"<td id='rDescription'" + response[i].reimbursementDescription  + "</td>" +
			"<td id='isPending'" + response[i].isPendingReimbursement + "</td>" +
			"<td id='rAmount'" + response[i].reimbursementAmount + "</td>" +
			"<td id='eId'" + response[i].reimbursementEmployeeId + "</td>" +
		"</tr>"	
		
		pendingTable.append(appendPending);
	}
}