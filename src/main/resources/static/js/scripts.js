// Theme loading/applying logic (common to all pages)
const body = document.body;
const savedTheme = localStorage.getItem('theme');
if (savedTheme === 'dark') {
    body.classList.add('dark-mode');
} else {
    body.classList.remove('dark-mode');
}

// Dynamic Clock Script (common to dashboard and settings)
function updateClock() {
    const now = new Date();
    const formatter = new Intl.DateTimeFormat('en-US', {
        hour: 'numeric',
        minute: 'numeric',
        hour12: true,
        timeZone: 'America/Toronto' 
    });

    const formattedTime = formatter.format(now);
    const clockElement = document.getElementById('local-clock');
    
    if (clockElement) {
        clockElement.textContent = formattedTime;
    }
}

// Only run clock update if element exists (for dashboard/settings)
if (document.getElementById('local-clock')) {
    updateClock();
    setInterval(updateClock, 1000);
}

// Theme switching logic (specific to settings page)
const darkModeSwitch = document.getElementById('darkModeSwitch');
if (darkModeSwitch) {
    darkModeSwitch.checked = (savedTheme === 'dark'); // Set initial state

    darkModeSwitch.addEventListener('change', () => {
        if (darkModeSwitch.checked) {
            body.classList.add('dark-mode');
            localStorage.setItem('theme', 'dark');
        } else {
            body.classList.remove('dark-mode');
            localStorage.setItem('theme', 'light');
        }
    });
}

// Handle form submission for theme (specific to settings page)
const themeForm = document.getElementById('theme-form');
if (themeForm) {
    themeForm.addEventListener('submit', (event) => {
        event.preventDefault();
        alert('Theme preference saved!');
    });
}
