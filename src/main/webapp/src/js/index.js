import { html, render } from "./../lit-html.js";

export default class Index {

    constructor(elContainer) {
        this.onDownload = this.onDownload.bind(this);
        this.el = elContainer;
        if (!this.checkAuthentication()) {
            this.redirectToAuth();
        }
        this.docs = fetch("http://localhost:8080/mycloud/resources/documents",
            {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                method: "get"
            })
            .then(response => response.json())
            .then(json => {
                this.docs = json;
                console.log(JSON.stringify(this.docs));
                render(this.renderDocs(), this.el);
            })
            .catch(res => console.error(res));
    }

    renderDocs() {
        return html`
            <ul>
                ${this.docs.map(doc => html`<li><a href='#' 
                    @click=${e => {e.preventDefault(); this.onDownload(doc)}}>${doc.titolo}</a> </li>`)}
            </ul>
        `
    }

    checkAuthentication() {
        return localStorage.getItem('token') !== null;
    }

    redirectToAuth() {
        document.location = 'login.html';
    }

    onDownload(doc) {
        this.docs = fetch(`http://localhost:8080/mycloud/resources/documents/download?name=${doc.fileName}`,
            {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                method: "get"
            })
            .then(response => response.blob())
            .then(blob => {
                console.dir(blob)
                var url = window.URL.createObjectURL(blob);
                var a = document.createElement('a');
                a.href = url;
                a.download = doc.fileName;
                document.body.appendChild(a); // we need to append the element to the dom -> otherwise it will not work in firefox
                a.click();
                a.remove();
            })
            .catch(res => console.error(res));
    }
}

new Index(document.getElementById('docs'));