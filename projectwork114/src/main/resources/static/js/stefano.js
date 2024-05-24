function modificaArtista(oggetto){

    var div = document.getElementById("modifica");
    div.hidden = false;

    var id = document.getElementById("modifica-id");
    var nome_artista = document.getElementById("modifica-nome_artista");
    var genere_musicale = document.getElementById("modifica-genere_musicale");
    var biografia = document.getElementById("modifica-biografia");

    id.value = oggetto.getAttribute("modifica-id");
    nome_artista.value = oggetto.getAttribute("modifica-nome_artista");
    genere_musicale.value = oggetto.getAttribute("modifica-genere_musicale");
    biografia.value = oggetto.getAttribute("modifica-biografia");
    

}

function chiudiFormModifica(){
    var form = document.getElementById("modifica");
    form.hidden = true;

}

function modificaCanzone(oggetto){
    
        var div = document.getElementById("modificaCanzone");
        div.hidden = false;
    
        var idCanzone = document.getElementById("modifica-idCanzone");
        var titolo_canzone = document.getElementById("modifica-titolo_canzone");
        var durata = document.getElementById("modifica-durata");
        var id_album = document.getElementById("modifica-id_album");
        var percorso_canzone = document.getElementById("modifica-percorso_canzone");
        var testi = document.getElementById("modifica-testi");
        var numero_ascolti = document.getElementById("modifica-numero_ascolti");
    
        idCanzone.value = oggetto.getAttribute("modifica-idCanzone");
        titolo_canzone.value = oggetto.getAttribute("modifica-titolo_canzone");
        durata.value = oggetto.getAttribute("modifica-durata");
        id_album.value = oggetto.getAttribute("modifica-id_album");
        percorso_canzone.value = oggetto.getAttribute("modifica-percorso_canzone");
        testi.value = oggetto.getAttribute("modifica-testi");
        numero_ascolti.value = oggetto.getAttribute("modifica-numero_ascolti");
        console.log(idCanzone);
        console.log(titolo_canzone);
        console.log(durata);
        console.log(id_album);
}

function chiudiFormModificaCanzone(){
    var form = document.getElementById("modificaCanzone");
    form.hidden = true;

}