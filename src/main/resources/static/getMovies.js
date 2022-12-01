var url = "http://localhost:8080/api/cinema/movies";

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
    const container =document.querySelector('#container');
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
        header.innerText = "Theater List";
        header.style.backgroundColor = 'green';
        const mov_id = document.querySelector('#mov_text').value;
        sessionStorage.setItem("movie_id", mov_id);
        url = "http://localhost:8080/api/cinema/theaters?movie_id=" + mov_id;
        let data = getData(url);

        const result = async() => {
            const a = await data;
            console.log(a);
            const table = document.createElement('table');
            let tab = 
                `<tr> 
                    <th>Theater ID</th>
                    <th>Theater Name</th>
                </tr>`;

            for (let r in a){
                tab += `
                    <tr>
                        <td>${a[r].id}</td>
                        <td>${a[r].name}</td>
                    </tr>`;
            }
            table.innerHTML = tab;
            container.appendChild(header);
            container.appendChild(table);
            createInputBar("theater_text", "number", "theater_but","Enter theater ID");
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
        url = "http://localhost:8080/api/cinema/schedule?movie_id=" + sessionStorage.getItem("movie_id") + "&theater_id=" + theater_id;
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
        time = time.replace('T', '%20');
        url = "http://localhost:8080/api/cinema/seats?movie_id=" + sessionStorage.getItem("movie_id") + "&theater_id=" + sessionStorage.getItem("theater_id") 
            + "&start_time=" + time;
        console.log(url)
        let data = getData(url);

        const result = async() => {
            const a = await data;
            console.log(a);
            const table = document.createElement('table');
            let tab = 
                `<tr> 
                    <th>Ticket ID</th>
                    <th>Seat Number</th>
                    <th>Related Ticket ID</th>
                </tr>`;

            for (let r in a){
                tab += `
                    <tr>
                        <td>${a[r].id}</td>
                        <td>${a[r].number}</td>
                        <td>${a[r].relatedTicketId}</td>
                    </tr>`;
            }
            table.innerHTML = tab;
            container.appendChild(header);
            container.appendChild(table);
            createInputBar("ticket_text", "ticket_but","Enter ticket ID");
        };    
        result();
    }
}

getAPI(url);