
function login() {
	console.log('asdflja');
	fetch('http://localhost:9731/ers-app/EmployeeLogInServlet', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			username: document.getElementById("employeeUsername").value,
			password: document.getElementById("employeePassword").value
			})
		})
		.then(response => {console.log(response.text())}) 
		.catch(err => console.log(err))
	}
	
