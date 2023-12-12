function drop() {
document.getElementById("myDropdown").classList.toggle("show");        
window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
        var myDropdown = document.getElementById("myDropdown");
        if (myDropdown.classList.contains('show')) {
            myDropdown.classList.remove('show');
        }
      }
    }
};

window.onscroll = function() {myFunction()};
function myFunction() {
    var winScroll = document.body.scrollTop || document.documentElement.scrollTop;
    var height = document.documentElement.scrollHeight - document.documentElement.clientHeight;
    var scrolled = (winScroll / height) * 100;
    document.getElementById("myBar").style.height = scrolled + "%";
};

var modal = document.getElementById('id01');
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

var modal1 = document.getElementById('id2');
    window.onclick = function(event) {
    modal.style.display = "none";
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

function updateUI() {
    const userNamePlaceholder = document.getElementById('userNamePlaceholder');
    const loginButton = document.querySelector('.login-button');

    if (isUserLoggedIn()) {
        const userName = localStorage.getItem('loggedInUser');
        userNamePlaceholder.textContent = `Welcome, ${userName}!`;
        loginButton.style.display = 'none';
    } else {
        userNamePlaceholder.textContent = '';
        loginButton.style.display = 'block';
    }
};

function handleLogin() {
    updateUI();
    document.getElementById('id01').style.display = 'none';
};

document.getElementById('user-login').addEventListener('click', handleLogin);
document.getElementById('staff-login').addEventListener('click', handleLogin);

function toggleText(elementId) {
    var additionalText = document.getElementById(elementId);
    additionalText.style.display = (additionalText.style.display === 'block') ? 'none' : 'block';
};

function redirectToInterestCalculator() {
    window.location.href = 'interestcalc.html';
};

function showContent(contentId) {
    var selectedContent = document.getElementById(contentId + '-content');

    var contents = document.querySelectorAll('.content');
    contents.forEach(function (content) {
        if (content !== selectedContent) {
            content.style.display = 'none';
        }
    });

    var navbarItems = document.querySelectorAll('.navbar-item');
    navbarItems.forEach(function (item) {
        item.style.borderBottom = 'none';
    });

    if (selectedContent) {
        selectedContent.style.display = (selectedContent.style.display === 'none' || selectedContent.style.display === '') ? 'block' : 'none';
        var navbarItem = document.getElementById(contentId);
        navbarItem.style.borderBottom = (selectedContent.style.display === 'block') ? '2px solid red' : '2px solid #ccc';
    }
}


document.addEventListener('DOMContentLoaded', function () {
    var yearLinks = document.querySelectorAll('#yearsList a');

    yearLinks.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            var selectedYear = link.textContent.trim();
            window.location.href = 'stamps_details.html?year=' + selectedYear;
        });
    });
});

function resetForm() {
            document.getElementById("myForm").reset();
        }

function togglePasswordFields() {
    var toggleButton = document.getElementById("toggleButton");
    var additionalFields = document.getElementById("additionalpswFields");

    if (additionalFields.style.display === "none" || additionalFields.style.display === "") {
        additionalFields.style.display = "block";
        toggleButton.style.display = "none";
    } else {
        additionalFields.style.display = "none";
        toggleButton.style.display = "inline-block";
    }
};