/**
 * Chiamate dei servizi rest sul server
 */


import AbstractService from "./AbstractService.js";

export default class DocumentiService extends AbstractService {
    constructor() {
        super();
        this.url = this.baseUrl + "/documenti";
        this.token = "";
        this.myJson = {};
    }

    leggiLocSt() {
        this.myJson = JSON.parse(localStorage.getItem(0));
        this.token = this.myJson.token;
    }
    
    async all() {
        this.leggiLocSt();
        const data = await fetch(this.url, {
            method: 'get',
            headers: {
                'Accept': 'application/json',
                'Authorization': "Bearer " + this.token
            }
        })
                .then((response) =>  {
                    if (response.ok) {return response.json()} else { return "non auth"}
            })
                .catch((res) => console.log(res))
        return data;
    }

    async delete(id) {
                this.leggiLocSt();
        return await fetch(this.url + "/" + id, {
            method: 'delete',
            headers: {
                'Accept': 'application/json',
                'Authorization': "Bearer " + this.token
            }
        })
                .catch((res) => console.error(res))
    }


    async update(id, json) {
                this.leggiLocSt();
        await fetch(this.url + "/" + id, {
            method: 'put',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + this.token
            },
            body: JSON.stringify(json)

        })
                .then((response) =>  {
                    if (response.ok) {return response.json()} else { return "non auth"}
            })
                .catch((res) => console.log(res))
        return this.res;
    }

    async sendFile(fd) {
                this.leggiLocSt();
        await fetch(this.url, {
            method: 'post',
            headers: {
                'Authorization': "Bearer " + this.token
            },
            body: fd
        })
                .then((response) => {
                    if (response.ok) {
                        console.log("File salvato")
                    } else {
                        console.log("File non salvato")
                    }
                })
                .catch(error => console.error(error))
        return this.res;
    }
//                'Content-Type': 'multipart/form-data',
}