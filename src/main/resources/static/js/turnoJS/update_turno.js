window.addEventListener('load', function () {



    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;


        const formData = {
            id: document.querySelector('#turno_id').value,
            odontologo : {
                id: document.querySelector('#get_odontologo_select').value
            },
            paciente: {
                id: document.querySelector('#paciente_id').value
            },
            fechaYHora : document.querySelector('#fechaYHora').value

        };


        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })


    function findBy(id) {
          const url = '/turnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#paciente_id').value = turno.paciente.id;
              document.querySelector('#paciente_nombre').value = turno.paciente.nombre + " " + turno.paciente.apellido;
              document.querySelector('#get_odontologo_select').value = turno.odontologo.id;
              document.querySelector('#fechaYHora').value = turno.fechaYHora;


              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }