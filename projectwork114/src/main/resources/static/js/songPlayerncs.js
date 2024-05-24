"use strict";

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

// Extract song data from the DOM
const songs = songItems.map((item, i) => {
    return {
        songName: item.querySelector('.songName').innerText,
        filePath: `/ncs/${i + 1}.mp3`, // Assuming the files are named 1.wav, 2.wav, etc.
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

// Function to update the play button state
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

// Handle play/pause click
masterPlay.addEventListener('click', () => {
    if (audioElement.paused || audioElement.currentTime <= 0) {
        playSong();
    } else {
        audioElement.pause();
        updatePlayButton(false);
    }
});

// Listen to the 'ended' event for playing the next song
audioElement.addEventListener('ended', playNextSong);

// Update progress bar as song plays
audioElement.addEventListener('timeupdate', () => {
    const progress = parseInt((audioElement.currentTime / audioElement.duration) * 100);
    myProgressBar.value = progress;
});

// Change song position when progress bar is used
myProgressBar.addEventListener('input', () => {
    const seekTime = (myProgressBar.value / 100) * audioElement.duration;
    audioElement.currentTime = seekTime;
});

// Function to initialize song items
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

// Function to make all plays inactive
const makeAllPlays = () => {
    Array.from(document.getElementsByClassName('songItemPlay')).forEach((element) => {
        element.classList.remove('fa-pause-circle');
        element.classList.add('fa-play-circle');
    });
};

// Function to show lyrics for the current song
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
