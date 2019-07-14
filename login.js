/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import LoginService from "./loginService.js";
import UtenteService from "./utentiService.js";

class App {
    constructor() {
        this.service = new LoginService();
        this.utService = new UtenteService();
        this.usr = document.getElementById("utente").value;
        this.pwd = document.getElementById("password").value;
        this.utLogged = {};
        this.myJson = {};
        this.token = "";
        this.visRisp = document.getElementById("resp");
        this.bindAll();
        this.getToken();

    }

    getToken() {
        this.service.login(this.usr, this.pwd)
                .then((response) => {
                    if (response) {
                        console.log("response ---> " + response);
                        this.token = response.token;
                        this.leggiIdUt();
                    } else {
                        this.visRisp.innerHTML = "Login non riuscita, Registrati"
                    }

                });
    }

    scriviTok() {
        this.myJson = {
            "token": this.token,
            "idUt": this.utLogged.id
        };

        localStorage.setItem(0, JSON.stringify(this.myJson));
//        localStorage.setItem("token", response.token);

    }

    leggiIdUt() {
        this.utService.readUt(this.usr, this.pwd)
                .then((response) => {
                    this.utLogged = response
                    this.scriviTok()
                    this.visRisp.innerHTML = "Login riuscita"
                    location.reload();
                });

    }

    bindAll() {
        this.getToken = this.getToken.bind(this);
    }
}

document.getElementById("tryLogin").onclick = function () {
    new App();
}