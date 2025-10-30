// Immediately apply theme before DOM is loaded to prevent flash
(function() {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme === 'dark') {
        document.documentElement.classList.add('dark-mode');
        document.body.classList.add('dark-mode');
    }
})();

// Dynamic Clock Script (common to dashboard and settings)
function updateClock() {
    const clockElement = document.getElementById('local-clock');
    if (!clockElement) return;

    const now = new Date();
    const userTimezone = localStorage.getItem('userTimezone') || 'America/Toronto';
    const formatter = new Intl.DateTimeFormat('en-US', {
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric',
        hour12: true,
        timeZone: userTimezone
    });

    const formattedTime = formatter.format(now);
    
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
