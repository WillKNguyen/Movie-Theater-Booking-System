var url = "http://localhost:8080/api/public/cinema/movies";
const container =document.querySelector('#container');
var movies = {};
var theaters = {};

async function getAPI(url){
    const response = await fetch(url);
    let data = await response.json();
    console.log(data);
    show(data);
}

async function getData(url){
    const response = await fetch(url);
    let data = await response.json();
    return data;
}

function show(data){

    let tab = 
        `<tr> 
            <th>Movie ID</th>
            <th>Movie Title</th>
        </tr>`;

    for (let r in data){
        movies[data[r].id] = data[r].title;
        tab += `
            <tr>
                <td>${data[r].id}</td>
                <td>${data[r].title}</td>
            </tr>`;
    }

    document.getElementById("movies").innerHTML = tab;

    // get the user input for movie id

    createInputBar("mov_text", "number", "mov_but","Enter movie ID");

    getTheater();
    
}

function createInputBar(text_id, type, but_id, prompt){
    
    const input = document.createElement('input');
    input.type = type;
    input.id = text_id;
    input.placeholder = prompt;
    const button = document.createElement('button');
    button.id = but_id;
    button.innerHTML = "Enter";
    container.appendChild(input);
    container.appendChild(button);
}

function getTheater(){
    const mBtn = document.querySelector('#mov_but');
    mBtn.onclick = () => {
        const header = document.createElement('h1');
        header.innerText = "Show Room List";
        header.style.backgroundColor = 'green';
        const mov_id = document.querySelector('#mov_text').value;
        sessionStorage.setItem("movie_id", mov_id);
        sessionStorage.setItem("movie_name", movies[mov_id]);
        url = "http://localhost:8080/api/public/cinema/show_rooms?movie_id=" + mov_id;
        let data = getData(url);

        const result = async() => {
            const a = await data;
            console.log(a);
            const table = document.createElement('table');
            let tab = 
                `<tr> 
                    <th>Show Room ID</th>
                    <th>Show Room Name</th>
                </tr>`;

            for (let r in a){
                theaters[a[r].id] = a[r].name;
                tab += `
                    <tr>
                        <td>${a[r].id}</td>
                        <td>${a[r].name}</td>
                    </tr>`;
            }
            table.innerHTML = tab;
            container.appendChild(header);
            container.appendChild(table);
            createInputBar("theater_text", "number", "theater_but","Enter show room ID");
            getTime();
        };       
        result();
    }
}

function getTime(){
    const tBtn = document.querySelector('#theater_but');
    tBtn.onclick = () => {
        const header = document.createElement('h1');
        header.innerText = "Showtime List";
        header.style.backgroundColor = 'green';
        const theater_id = document.querySelector('#theater_text').value;
        sessionStorage.setItem("theater_id", theater_id);
        sessionStorage.setItem("theater_name", theaters[theater_id]);
        url = "http://localhost:8080/api/public/cinema/schedule?movie_id=" + sessionStorage.getItem("movie_id") + "&show_room_id=" + theater_id;
        let data = getData(url);

        const result = async() => {
            const a = await data;
            console.log(a);
            const table = document.createElement('table');
            let tab = 
                `<tr> 
                    <th>Show times</th>
                </tr>`;

            for (let r in a){
                tab += `
                    <tr>
                        <td>${a[r].moment}</td>
                    </tr>`;
            }
            table.innerHTML = tab;
            container.appendChild(header);
            container.appendChild(table);
            createInputBar("time_text", "text", "time_but","Enter show time");
            getTicket();
        };       
        
        result();
    }
}

function getTicket(){
    const tBtn = document.querySelector('#time_but');
    tBtn.onclick = () => {
        const header = document.createElement('h1');
        header.innerText = "Available Tickets";
        header.style.backgroundColor = 'green';
        let time = document.querySelector('#time_text').value;
        sessionStorage.setItem('time', time);
        time = time.replace('T', '%20');
        url = "http://localhost:8080/api/public/cinema/seats?movie_id=" + sessionStorage.getItem("movie_id") + "&show_room_id=" + sessionStorage.getItem("theater_id") 
            + "&start_time=" + time;
        console.log(url)
        let data = getData(url);

        const result = async() => {
            const a = await data;
            console.log(a);
            const table = document.createElement('table');
            let availableSeats = {};
            let tab = 
                `<tr> 
                    <th>Ticket ID</th>
                    <th>Seat Number</th>
                    <th>Related Ticket ID</th>
                </tr>`;

            for (let r in a){
                availableSeats[Number(a[r].number)] = Number(a[r].relatedTicketId);
                tab += `
                    <tr>
                        <td>${a[r].id}</td>
                        <td>${a[r].number}</td>
                        <td>${a[r].relatedTicketId}</td>
                    </tr>`;
            }
            console.log(availableSeats)
            table.innerHTML = tab;
            container.appendChild(header);
            container.appendChild(table);
            seatMap(availableSeats);
        };    
        result();
    }
}

function seatMap(availableSeats){
    seatRow(1,5, availableSeats);
    seatRow(6,10, availableSeats);
    // seatListener();
}

function seatRow(start, end, availableSeats){
    const row = document.createElement("div");
    row.style.display = "flex";
    row.style.gap = "5px";
    row.style.margin = "5px";
    row.className = "row";
    for (let i = start; i <= end; i++) {
        const seat = document.createElement("button");
        seat.style.width = "40px";
        seat.style.height = "40px";
        seat.style.borderRadius = "10%";
        if(i in availableSeats){
            seat.className = "S" + i;
            seat.style.backgroundColor = "rgb(28, 192, 28)";
            const anchor = document.createElement("a");
            anchor.href = "http://localhost:8080/checkout";
            anchor.textContent = "S" + i;
            anchor.style.textDecoration = "none";
            seat.id = availableSeats[i];
            seat.onclick = function() {
                sessionStorage.setItem("seatnumber", seat.className) 
                sessionStorage.setItem("seatID", seat.id);
            }
            seat.appendChild(anchor);
        }
        else{
            seat.textContent = "S" + i;
        }
        row.appendChild(seat);
    }

    container.appendChild(row);
}

getAPI(url);