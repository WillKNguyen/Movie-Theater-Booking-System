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


let url = "http://localhost:8080/api/private/cinema/purchase_ticket?ticket_id=" + sessionStorage.getItem("seatID") 
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