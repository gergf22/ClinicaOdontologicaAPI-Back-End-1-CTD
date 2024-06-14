window.addEventListener('load', function () {
    (function(){

      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         for(paciente of data){

            var select = document.getElementById("get_paciente_select");
            let value = paciente.id;

            select.innerHTML = '<option value= ' + value + '  >' + paciente.nombre + ' ' +paciente.apellido + '</option>'

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnoAlta.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })