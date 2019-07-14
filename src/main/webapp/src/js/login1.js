let frm = document.querySelector('#login');
frm.addEventListener('submit', e => onSubmit(e));

function onSubmit(e) {
    e.preventDefault();
    const credential = {
        "usr": document.querySelector("#usr").value,
        "pwd": document.querySelector("#pwd").value,
    }
    
    fetch("http://localhost:8080/mycloud/resources/auth",
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credential),
            method: "post"
        })
        .then(response => response.json())
        .then(json => {
            console.log(json);
            localStorage.setItem('token', json.token);
            document.location = 'index.html';
        })
        .catch(res => console.error(res));

}

