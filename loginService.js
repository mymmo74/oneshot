
import AbstractService from "./AbstractService.js";

export default class LoginService extends AbstractService {
    constructor() {
        super();
        this.url = this.baseUrl + "/auth";
    }


    async login(usr, pwd) {
        const data = await fetch(this.url, {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'usr': usr,
                'pwd': pwd
            }
        })
                .then(response => response.json())
                .then(data => {
                    this.res = data
                })
                .catch(error => console.error(error))
        return this.res;
    }
    

}