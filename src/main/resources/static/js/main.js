document.getElementById("toggle-contrast").addEventListener("click", function() {
    document.body.classList.toggle("contrast");
});

const radioButtons = document.querySelectorAll('input[name="color-scheme"]');
const body = document.body;

radioButtons.forEach(radio => {
    radio.addEventListener('change', function() {
        if (this.value === 'light') {
            body.classList.remove('dark-mode');
            body.classList.add('light-mode');
        } else if (this.value === 'dark') {
            body.classList.remove('light-mode');
            body.classList.add('dark-mode');
        }
    });
});
