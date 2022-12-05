const container = document.querySelector('#container');

const input = document.createElement('input');
input.type = 'text';
input.id = 'ticketID';
input.placeholder = 'Enter your ticket ID';

const button = document.createElement('button');
button.innerHTML = "Submit";
container.appendChild(input);
container.appendChild(button);

button.onclick = function(){
    let confirmationMessage = document.createElement("h3");
    
    let ticketID = document.querySelector('#ticketID').value;
    let url = "http://localhost:8080/api/public/cinema/cancel_ticket?ticket_id=" + ticketID;

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
            confirmationMessage.textContent = "Ticket refunded!";
            confirmationMessage.style.backgroundColor = "green";
        }).catch(function(error) {
            console.log(error);
            confirmationMessage.textContent = "Could not find this ticket";
            confirmationMessage.style.backgroundColor = "red";
        });

    container.appendChild(confirmationMessage);
}
