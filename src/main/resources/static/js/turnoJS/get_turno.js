window.addEventListener('load', function () {

        const form = document.getElementById("add_new_turno");

        console.log (form);
        form.addEventListener('submit', function (event) {

            event.preventDefault();

            refreshRows();

            let pacienteId = document.querySelector('#get_paciente_select').value;
            console.log(pacienteId);
            const url = '/turnos/paciente/' + pacienteId;

            const settings = {
                method: 'GET'
            };

            fetch(url, settings)
                .then(response => response.json())
                .then(data => {

                    for(turno of data){

                        console.log(turno);
                        var table = document.getElementById("turnoTableBody");
                        var turnoRow =table.insertRow();
                        let tr_id = turno.id;
                        turnoRow.id = tr_id;

                        let deleteButton = '<button' +
                                                  ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                                  ' type="button" onclick="deleteBy(' + turno.id + ')" class="btn btn-danger btn_delete">' +
                                                  '&times' +
                                                  '</button>';


                        let updateButton = '<button' +
                                                  ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                                  ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                                  turno.id +
                                                  '</button>';

                        turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                                '<td class="td_paciente">' + turno.paciente.nombre.toUpperCase() + ' ' +
                                turno.paciente.apellido.toUpperCase() + '</td>'+
                                '<td class="td_odontologo">' + turno.odontologo.nombre.toUpperCase() + ' ' +
                                  turno.odontologo.apellido.toUpperCase() + '</td>' +
                                '<td class="td_fechaYHora">' + turno.fechaYHora + '</td>' +
                                '<td>' + deleteButton + '</td>';

                    };

                })
        })

        function refreshRows (){
            let table = document.getElementById("turnoTableBody");
            table.innerHTML = ""
        };



    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname === "/turnoLista.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});


