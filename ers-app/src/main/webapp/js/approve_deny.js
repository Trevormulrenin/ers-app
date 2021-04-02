/**
 * 
 */


//var approve = document.getElementById('approve').value;
//var deny = document.getElementById('deny').value;
//
//if(approve) {
//	approve.addEventListener('click', approve);
//}
//
//if(deny) {
//	deny.addEventListener('click',  deny);
//}



function approve(r_id){
	fetch('http://localhost:8080/ers-app/ApproveOrDenyReimbursementServlet', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			approveOrDeny: "approve",
			reimbursementId: r_id
		})
	})
		.then(response => { console.log(response.text()) })
		.catch(err => console.log(err))
}

function deny(r_id) {

	fetch('http://localhost:8080/ers-app/ApproveOrDenyReimbursementServlet', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			approveOrDeny: "deny",
			reimbursementId: r_id
		})
	})
		.then(response => { console.log(response.text()) })
		.catch(err => console.log(err))
}


function changeColor(t_id) {
	document.getElementById(t_id).style.color = 'gray'
}
