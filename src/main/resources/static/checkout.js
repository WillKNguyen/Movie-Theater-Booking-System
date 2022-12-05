const container =document.querySelector('#container');

const header = document.createElement('h1');
header.textContent = "Check Out";
var movie = sessionStorage.getItem("movie_name");
var theater = sessionStorage.getItem("theater_name");
var time = sessionStorage.getItem("time");
var seat = sessionStorage.getItem("seatnumber")

const home = document.createElement("button");
const homeLink = document.createElement("a");
homeLink.href = "http://localhost:8080";
homeLink.textContent = "Return Home";
home.appendChild(homeLink)

const table = document.createElement('table');
let tab = 
    `<tr> 
        <th>Details</th>
        <th>Value</th>
    </tr>`;

tab += `<tr>
    <td>${"Movie"}</td>
    <td>${movie}</td>
</tr>`;

tab += `<tr>
    <td>${"Show Room"}</td>
    <td>${theater}</td>
</tr>`;

tab += `<tr>
    <td>${"Time"}</td>
    <td>${time}</td>
</tr>`;

tab += `<tr>
    <td>${"Seat"}</td>
    <td>${seat}</td>
</tr>`;

table.innerHTML = tab;
table.style.border = '1px solid';

container.appendChild(header);
container.appendChild(table);

const prompt = document.createElement('h3');
prompt.textContent = 'Please enter a credit card number to purchase ticket'

const eInput = document.createElement('input');
eInput.type = 'text';
eInput.id = 'email';
eInput.placeholder = "Enter a your email address";

const ccInput = document.createElement('input');
ccInput.type = 'text';
ccInput.id = 'cc';
ccInput.placeholder = "Enter a credit card number";

const button = document.createElement('button');
button.id = 'cc_but';
button.innerHTML = "Enter";


container.appendChild(prompt);
container.appendChild(eInput);
container.appendChild(ccInput);
container.appendChild(button);
container.appendChild(home);


button.onclick = function() {
    let email = document.querySelector('#email').value;
    email = email.replace('@', '%40')

    const ccNum = document.querySelector('#cc').value;

    let url = "http://localhost:8080/api/public/cinema/purchase_ticket?ticket_id=" + sessionStorage.getItem("seatID") 
    + "&credit_card=" + ccNum + "&email=" + email;
    const confirmationMessage = document.createElement("h3");

    function handleErrors(response) {
        if (!response.ok) {
            throw Error(response.statusText);
        }
        return response;
    }
    
    fetch(url)
        .then(handleErrors)
        .then(function(response) {
            console.log("ok");
            confirmationMessage.textContent = "Ticket bought!";
            confirmationMessage.style.backgroundColor = "green";
        }).catch(function(error) {
            console.log(error);
            confirmationMessage.textContent = "Something wrong! Please try again";
            confirmationMessage.style.backgroundColor = "red";
        });

    container.appendChild(confirmationMessage);
    
}