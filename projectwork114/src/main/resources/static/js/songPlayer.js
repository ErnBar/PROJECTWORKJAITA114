"use strict"; //MODALITA RIGOROSA. Permette di scrivere codice più sicuro e corretto
/*La selezione attiva è una singola riga di codice JavaScript:
Questa riga è una direttiva introdotta in ECMAScript 5 (ES5). Si chiama "use strict" o 
la direttiva della modalità rigorosa. Quando la metti all'inizio del tuo file JavaScript o funzione, 
abilita la modalità rigorosa per quel contesto.
La modalità rigorosa apporta diverse modifiche alla semantica normale di JavaScript:
1. Elimina alcuni errori silenziosi di JavaScript trasformandoli in errori che vengono lanciati.
 Ad esempio, in JavaScript normale, se assegni un valore a una variabile non dichiarata, 
 JavaScript dichiarerà implicitamente la variabile per te. Ma in modalità rigorosa, questo genererà un errore.
2. Corregge errori che rendono difficile per i motori JavaScript eseguire ottimizzazioni. 
Il codice eseguito in modalità rigorosa può a volte essere fatto funzionare più velocemente 
rispetto al codice identico che non è in modalità rigorosa.
3. Proibisce alcune sintassi che probabilmente saranno definite nelle future versioni di ECMAScript.
 Questo è per garantire che il codice attuale in "modalità rigorosa" rimarrà compatibile con le future versioni di JavaScript.
In sostanza, "use strict" ti aiuta a scrivere codice più sicuro e robusto prevenendo l'uso di 
funzionalità o comportamenti potenzialmente problematici in JavaScript.*/ 

// Initialize the Variables
let songIndex = 0;
let audioElement = new Audio();
const masterPlay = document.getElementById('masterPlay');
const myProgressBar = document.getElementById('myProgressBar');
const gif = document.getElementById('gif');
const masterSongName = document.getElementById('masterSongName');
const songItems = Array.from(document.getElementsByClassName('songItem'));
const lyricsItems = Array.from(document.getElementsByClassName('lyricsItem'));
const next = document.getElementById('next');
const previous = document.getElementById('previous');

//La selezione attiva del codice JavaScript mappa un array di 
//elementi di canzoni in un nuovo array di oggetti. 
//Ogni oggetto ha tre proprietà: `songName`, `filePath` e `duration`.


const songs = songItems.map((item, i) => {
    return {
        songName: item.querySelector('.songName').innerText,
        filePath: `/audio/${i + 1}.wav`, // Assuming the files are named 1.wav, 2.wav, etc.
        duration: item.querySelector('.timestamp').innerText
    };
});

// Function to play the next song
const playNextSong = () => {
    songIndex = (songIndex < songs.length - 1) ? songIndex + 1 : 0;
    playSong();
};

// Function to play the previous song
const playPreviousSong = () => {
    songIndex = (songIndex > 0) ? songIndex - 1 : songs.length - 1;
    playSong();
};

// Function to play a specific song
const playSong = () => {
    if (songs[songIndex]) {
        audioElement.src = songs[songIndex].filePath;
        masterSongName.innerText = songs[songIndex].songName;
        audioElement.currentTime = 0;
        audioElement.play();
        updatePlayButton(true);
        showLyrics(songIndex);
    } else {
        console.error('songIndex fuori dal range:', songIndex);
    }
};

// Funzione per l'update del puklsante play e della gif animata
const updatePlayButton = (isPlaying) => {
    if (isPlaying) {
        masterPlay.classList.remove('fa-play-circle');
        masterPlay.classList.add('fa-pause-circle');
        gif.style.opacity = 1;
    } else {
        masterPlay.classList.remove('fa-pause-circle');
        masterPlay.classList.add('fa-play-circle');
        gif.style.opacity = 0;
    }
};

// Funzione che gestisce il click sul pulsante play
masterPlay.addEventListener('click', () => {
    if (audioElement.paused || audioElement.currentTime <= 0) {
        playSong();
    } else {
        audioElement.pause();
        updatePlayButton(false);
    }
});

//Quando laudio è in pausa il codice controlla se l'elemento audio è in pausa e il tempo corrente
//è minore o uguale a 0. Se è vero, la funzione playSong() viene chiamata per riprodurre la canzone.
//Altrimenti, l'elemento audio viene messo in pausa e il pulsante di riproduzione viene aggiornato.

// Listen to the 'ended' event for playing the next song
audioElement.addEventListener('ended', playNextSong);

// Update progress bar as song plays
audioElement.addEventListener('timeupdate', () => {
    const progress = parseInt((audioElement.currentTime / audioElement.duration) * 100);
    myProgressBar.value = progress;
});

// Funzione per aggiornare la barra di avanzamento quando l'utente la trascina
myProgressBar.addEventListener('input', () => {
    const seekTime = (myProgressBar.value / 100) * audioElement.duration;
    audioElement.currentTime = seekTime;
});

// Funzione per inizializzare gli elementi della canzone
//Attraversa ogni elemento di canzone e aggiunge un ascoltatore di eventi per il click sul pulsante di riproduzione.
//Quando il pulsante di riproduzione viene cliccato, la funzione playSong() viene chiamata per riprodurre la canzone corrispondente.
//In sintesi permette di cliccare sul pulsante play per riprodurre la canzone corrispondente.
//tutte le altre canzoni vengono messe in pausa.
//l'icona cambiata da play a pausa.
//grazie ad un ciclo for each si aggiunge un ascoltatore di eventi per il click sul pulsante di riproduzione.
const initializeSongItems = () => {
    songItems.forEach((element, i) => {
        if (songs[i]) {
            const songItemPlay = element.getElementsByClassName("songItemPlay")[0];
            songItemPlay.addEventListener('click', (e) => {
                makeAllPlays();
                songIndex = i;
                e.target.classList.remove('fa-play-circle');
                e.target.classList.add('fa-pause-circle');
                playSong();
            });
        }
    });
};

//utilizza il document per ottenere tutti gli elementi con la classe songItemPlay.
//array.from() viene utilizzato per trasformare la NodeList in un array.
//con il forEach() eseguo una funzione per ogni elemento dell'array.
//dentro la funzione, rimuovo la classe fa-pause-circle e aggiungo la classe fa-play-circle.
const makeAllPlays = () => {
    Array.from(document.getElementsByClassName('songItemPlay')).forEach((element) => {
        element.classList.remove('fa-pause-circle');
        element.classList.add('fa-play-circle');
    });
};

// Funzione per mostrare i testi della canzone corrispondente
const showLyrics = (index) => {
    lyricsItems.forEach((lyricsItem, i) => {
        if (i === index) {
            lyricsItem.classList.add('show');
        } else {
            lyricsItem.classList.remove('show');
        }
    });
};

// Add event listeners for next and previous buttons
next.addEventListener('click', playNextSong);
previous.addEventListener('click', playPreviousSong);

// Initialize song items
initializeSongItems();
