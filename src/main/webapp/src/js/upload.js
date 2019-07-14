export default class Upload {

    constructor(elForm) {
        if(! this.checkAuthentication()){
            this.redirectToAuth();
        }
        this.elForm = elForm;
        elForm.addEventListener('submit', e => this.onSubmit(e));
    }

    onSubmit(e) {
        e.preventDefault();
        let frm = new FormData();
        frm.append("titolo",document.querySelector("#titolo").value);
        frm.append("file",document.querySelector("#file").files[0],document.querySelector("#file").files[0].name);
        fetch("http://localhost:8080/mycloud/resources/documents",
            {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                body: frm,
                method: "post"
            })
            .then(response => {
                if(response.status === 200){
                    this.redirectToIndex();
                }else{
                    console.log(response);
                }
            })
           .catch(res => console.error(res));

    }

    redirectToIndex() {
        document.location = 'index.html';
    }

    redirectToAuth(){
        document.location = 'login.html';
    }

    checkAuthentication() {
        return localStorage.getItem('token') !== null;
    }
}

new Upload(document.getElementById('upload'));