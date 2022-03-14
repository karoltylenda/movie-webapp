document.addEventListener('DOMContentLoaded', () => {
    // Functions to open and close a modal
    function openModal($el) {
        $el.classList.add('is-active');
    }

    function closeModal($el) {
        $el.classList.remove('is-active');
    }

    function closeAllModals() {
        (document.querySelectorAll('.modal') || []).forEach(($modal) => {
            closeModal($modal);
        });
    }

    // Add a click event on buttons to open a specific modal
    (document.querySelectorAll('.js-modal-trigger') || []).forEach(($trigger) => {
        const modal = $trigger.dataset.target;
        const $target = document.getElementById(modal);
        console.log($target);

        $trigger.addEventListener('click', () => {
            openModal($target);
        });
    });

    // Add a click event on various child elements to close the parent modal
    (document.querySelectorAll('.modal-background, .modal-close, .modal-card-head .delete, .modal-card-foot .button') || []).forEach(($close) => {
        const $target = $close.closest('.modal');

        $close.addEventListener('click', () => {
            closeModal($target);
        });
    });

    // Add a keyboard event to close all modals
    document.addEventListener('keydown', (event) => {
        const e = event || window.event;

        if (e.keyCode === 27) { // Escape key
            closeAllModals();
        }
    });
});

$(document).ready( function () {
    $('#usersTable').DataTable({
        language: {
            paginate: {
                previous: '<',
                next: '>'
            }
        },
        aria: {
            paginate: {
                previous: 'Previous',
                next: 'Next'
            }
        }
    });

    $('#moviesTable').DataTable({
        language: {
            paginate: {
                previous: '<',
                next: '>'
            }
        },
        aria: {
            paginate: {
                previous: 'Previous',
                next: 'Next'
            }
        }
    });
} );

document.addEventListener('DOMContentLoaded', () => {

    // Get all "navbar-burger" elements
    const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

    // Check if there are any navbar burgers
    if ($navbarBurgers.length > 0) {

        // Add a click event on each of them
        $navbarBurgers.forEach( el => {
            el.addEventListener('click', () => {

                // Get the target from the "data-target" attribute
                const target = el.dataset.target;
                const $target = document.getElementById(target);

                // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
                el.classList.toggle('is-active');
                $target.classList.toggle('is-active');

            });
        });
    }

});

// Check passwords match functions:
function showMessage(value, id){

    let password;
    let confirmPassword;
    let message = document.getElementById(value+id);

    switch (value) {
        case 'info':
            password = document.getElementById('password');
            confirmPassword = document.getElementById('confirmPassword');
            break;
        case 'resetInfo':
            password = document.getElementById('resetPassword'+id);
            confirmPassword = document.getElementById('resetConfirmPassword'+id);
            break;
        default: break;
    }

    if (areFieldsEmpty(password, confirmPassword)){
        message.innerText = 'Password field cannot be empty';
        message.classList.replace('has-text-success', 'has-text-danger');
        setInputPasswordClass(password, confirmPassword, 'is-danger')
    } else if (isPasswordMatches(password, confirmPassword)){
        message.innerText = 'Password match';
        message.classList.replace('has-text-danger', 'has-text-success');
        setInputPasswordClass(password, confirmPassword, 'is-success');
    } else {
        message.innerText = 'Password doesn\'t match'
        message.classList.replace('has-text-success', 'has-text-danger');
        setInputPasswordClass(password, confirmPassword, 'is-danger');
    }
}

function areFieldsEmpty(password, confirmPassword){
    if (password.value.length === 0 || confirmPassword.value.length === 0){
        return true;
    } else return false;
}

function isPasswordMatches(password, confirmPassword){
    if (password.value === confirmPassword.value){
        return true;
    } else return false;
}

function setInputPasswordClass(password, confirmPassword, className){
    switch (className){
        case 'is-danger':
            password.classList.replace('is-success', className);
            confirmPassword.classList.replace('is-success', className);
            break;
        case 'is-success':
            password.classList.replace('is-danger', className);
            confirmPassword.classList.replace('is-danger', className);
            break;
        default:
            break;
    }
}
// <<< END OF CHECK PASSWORDS MATCH

//DATE PARSER
function dateParser(date = ''){
    const dateArray = date.split(' ');
    return dateArray[2]+'-'+getMonthNumber(dateArray[1])+'-'+dateArray[0];
}

function getMonthNumber(month = ''){
    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    const monthsNo = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
    for (let i = 0; i < months.length; i++) {
        if (month === months[i]){
            month = monthsNo[i];
            break;
        }
    }
    return month;
}
//END OF DATE PARSER