// Theme initialization
(function() {
    const userThemeInput = document.getElementById('darkThemeEnabled');
    if (userThemeInput) {
        // User is logged in, use their preference
        if (userThemeInput.value === 'true') {
            document.documentElement.classList.add('dark-mode');
            document.body.classList.add('dark-mode');
        }
    } else {
        // Guest user - always use light theme
        document.documentElement.classList.remove('dark-mode');
        document.body.classList.remove('dark-mode');
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

// Theme switching logic (specific to settings page)
const darkModeSwitch = document.getElementById('darkModeSwitch');
if (darkModeSwitch) {
    const userThemeInput = document.getElementById('darkThemeEnabled');
    darkModeSwitch.checked = userThemeInput ? userThemeInput.value === 'true' : false;

    darkModeSwitch.addEventListener('change', () => {
        if (darkModeSwitch.checked) {
            document.documentElement.classList.add('dark-mode');
            document.body.classList.add('dark-mode');
        } else {
            document.documentElement.classList.remove('dark-mode');
            document.body.classList.remove('dark-mode');
        }
        if (userThemeInput) {
            userThemeInput.value = darkModeSwitch.checked;
        }
    });
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


