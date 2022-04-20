function registerPerson() {
    let type = document.getElementById("type").value;
    let ruc = document.getElementById("ruc").value;

    if (type === "") {
        alert("Ingrese un Tipo Valido");
        return;
    }

    if (ruc === "") {
        alert("Ingrese un Ruc Valido");
        return
    }

    const request = new FormData();
    request.append('type', type);
    request.append('ruc', ruc);


    fetch('/person/register', {
        method: 'POST',
        body: request
    })
        .then(response => response.json())
        .then((json) => {
            $("#result").html(setResponseSuccess(json));
        })

}

function findPerson() {
    let ruc = document.getElementById("ruc").value;
    if (ruc === "") {
        alert("Ingrese un Ruc Valido");
        return
    }
    let url = 'http://localhost:8999/person/find/' + ruc;
    console.log(url); 
    fetch(url)
        .then(response =>response.json())
        .then((json) => {
            $("#result").html(setResponseSuccess(json));
        })
}


function setResponseSuccess(json) {
    let card;
    if (json.responseStatus == "R200") {
        card = `
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><span class="badge bg-success">${json.responseMessage}</span></h5>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">RUC: ${json.responseBody.ruc}</li>
                <li class="list-group-item">RAZON SOCIAL: ${json.responseBody.razon_social}</li>
                <li class="list-group-item">ESTADO: ${json.responseBody.estado}</li>
                <li class="list-group-item">DIRECCION: ${json.responseBody.direccion}</li>
                <li class="list-group-item">UBIGEO: ${json.responseBody.ubigeo}</li>
                <li class="list-group-item">DEPARTAMENTO: ${json.responseBody.departamento}</li>
                <li class="list-group-item">PROVINCIA: ${json.responseBody.provincia}</li>
                <li class="list-group-item">DISTRITO: ${json.responseBody.distrito}</li>
            </ul>
        </div>`;
    } else {
        card = `
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><span class="badge bg-warning">${json.responseMessage}</span></h5>
            </div>
        </div>`;
    }

    
    return card;
}