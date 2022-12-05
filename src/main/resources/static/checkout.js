const container =document.querySelector('#container');

const header = document.createElement('h1');
header.textContent = "Check Out";
var movie = sessionStorage.getItem("movie_name");
var theater = sessionStorage.getItem("theater_name");
var time = sessionStorage.getItem("time");
var seat = sessionStorage.getItem("seatnumber")

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

const input = document.createElement('input');
input.type = 'text';
input.id = 'cc';
input.placeholder = "Enter a credit card number";
const button = document.createElement('button');
button.id = 'cc_but';
button.innerHTML = "Enter";
container.appendChild(prompt);
container.appendChild(input);
container.appendChild(button);