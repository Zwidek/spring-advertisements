const toggleSwitch = document.getElementById('toggle-theme');

toggleSwitch.addEventListener('change', function() {
    if (this.checked) {
        applyTheme('dark');
        localStorage.setItem('colorScheme', 'dark');
    } else {
        applyTheme('light');
        localStorage.setItem('colorScheme', 'light');
    }
});

function applyTheme(theme) {
    var body = document.body;
    if (theme === 'light') {
        body.classList.remove('dark-mode');
        body.classList.add('light-mode');
    } else if (theme === 'dark') {
        body.classList.remove('light-mode');
        body.classList.add('dark-mode');
    }
}

var savedColorScheme = localStorage.getItem('colorScheme');
if (savedColorScheme) {
    if (savedColorScheme === 'dark') {
        toggleSwitch.checked = true;
        applyTheme('dark');
    } else {
        toggleSwitch.checked = false;
        applyTheme('light');
    }
}
