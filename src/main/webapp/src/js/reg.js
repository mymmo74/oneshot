let frm = document.querySelector('#regForm');
frm.addEventListener('submit', e => onSubmit(e));

function onSubmit(e) {
    let reg_nome = document.getElementById('nome').value;
    let reg_cognome = document.getElementById('cognome').value;
    let reg_email = document.getElementById('email').value;
    let reg_user = document.getElementById('user').value;
    let reg_password = document.getElementById('password').value;
    let reg_password_control = document.getElementById('password2').value;

    e.preventDefault();

    if ((reg_nome != "") &
            (reg_cognome != "") &
            (reg_email != "") &
            (reg_user != "") &
            (reg_password != "")) {

        const data = new URLSearchParams;
        document.getElementById("esitoReg").removeAttribute("class")

        data.append("rEmail", email.value);
        data.append("rPsw", psw.value);
        evt.preventDefault();

        fetch("http://localhost:8080/esame_cloud/rest/utenti", {method: "POST", body: data})
                .then(res => {
                    if (res.status == 200) {

                        let esito = document.getElementById("esitoReg")
                        esito.classList.add("bg-success");
                        esito.innerHTML = "Registrazione Avvenuta"

                    }
                    if (res.status == 403) {

                        let esito = document.getElementById("esitoReg")
                        esito.classList.add("alert-danger");
                        esito.innerHTML = "Email già presente - Registrazione non avvenuta"
                    }
                })


    }

}