export default class Login {

    constructor(elForm) {
        this.elForm = elForm;
        elForm.addEventListener('submit', e => this.onSubmit(e));
    }

    onSubmit(e) {
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
                this.redirectToIndex();
            })
            .catch(res => console.error(res));

    }

    redirectToIndex() {
        document.location = 'index.html';
    }

}

new Login(document.getElementById('login'));