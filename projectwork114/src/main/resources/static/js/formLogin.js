window.onload = function() {
    document.getElementById('welcomeModal').style.display = 'block';
};
function startAudio() {
    var myAudio = document.getElementById('myAudio');
    myAudio.play().catch(function(error) {
        console.error("L'autoplay dell'audio non è stato possibile: ", error);
    });
    myAudio.volume = 0.03;
    closeModal();
}
function closeModal() {
    document.getElementById('welcomeModal').style.display = 'none';
}