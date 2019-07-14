//let frmLog = document.querySelector('#logForm');
//frmLog.addEventListener('submit', t => onSubmit(t));

function login() {
    let log_user = document.getElementById('log_user').value;
    let log_password = document.getElementById('log_password').value;

//    t.preventDefault();

    if ((log_user !== "") & (log_user !== "")) {


        const credenziali = {
            "usr": log_user,
            "pwd": log_password,
        };

        fetch("http://localhost:8080/cloud/resources/auth",
                {
                    method: 'post',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'usr': log_user,
                        'pwd': log_password
                    },
//                    body: JSON.stringify(credenziali),
                    
                })
                .then(response => response.json())
                .then(json => {
                    console.log(json);
                    localStorage.setItem('token', json.token);
                    document.location = 'index.html';
                })
                .catch(res => console.error(res));


    } else {
        // messaggio che non sono stati inseriti tutti i dati
        window.alert('Inserire le credenziali');
    }

}