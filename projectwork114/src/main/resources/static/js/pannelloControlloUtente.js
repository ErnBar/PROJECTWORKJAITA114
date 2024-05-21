function modificaAccount(oggetto){

    var div = document.getElementById("modifica");
    div.hidden = false;

    var id = document.getElementById("modifica-id");
    var username = document.getElementById("modifica-username");
    var nome = document.getElementById("modifica-nome");
    var email = document.getElementById("modifica-email");
    var password = document.getElementById("modifica-password");
    var ruolo = document.getElementById("modifica-ruolo");

    id.value = oggetto.getAttribute("modifica-id");
    username.value = oggetto.getAttribute("modifica-username");
    nome.value = oggetto.getAttribute("modifica-nome");
    email.value = oggetto.getAttribute("modifica-email");
    password.value = oggetto.getAttribute("modifica-password");
    ruolo.value = oggetto.getAttribute("modifica-ruolo");

}

function chiudiFormModifica(){
    var form = document.getElementById("Modifica");
    form.hidden = true;

}