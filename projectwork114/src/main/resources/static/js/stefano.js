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