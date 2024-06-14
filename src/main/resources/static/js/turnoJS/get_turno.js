window.addEventListener('load', function () {



        (function(){


          const pacienteid = document.querySelector('#get_paciente_select').value
          const url = '/turnos/paciente/' + pacienteid;
          console.log('/turnos/paciente/' + pacienteid)
          const settings = {
            method: 'GET'
          }

          fetch(url,settings)
          .then(response => response.json())
          .then(data => {

             for(turno of data){

                var table = document.getElementById("turnoTable");
                var turnoRow =table.insertRow();
                let tr_id = turno.id;
                turnoRow.id = tr_id;

                let deleteButton = '<button' +
                                          ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                          ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                          '&times' +
                                          '</button>';


                let updateButton = '<button' +
                                          ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                          ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                          turno.id +
                                          '</button>';

                turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                        '<td class=\"td_paciente\">' + turno.paciente.nombre.toUpperCase() + '</td>'/*" "
                         + turno.paciente.apellido.toUpperCase() + '</td>' */+
                        '<td class=\"td_odontologo\">' + turno.odontologo.nombre.toUpperCase() +  '</td>' /*" "
                                             + turno.odontologo.apellido.toUpperCase() + '</td>' + +
                        '<td class=\"td_fechaYHora\">' + turno.fechaYHora + '</td>'*/ +
                        '<td>' + deleteButton + '</td>';

            };

        })
        })



        (function(){
          let pathname = window.location.pathname;
          if (pathname == "/pacienteLista.html") {
              document.querySelector(".nav .nav-item a:last").addClass("active");
          }
        })



    })