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
            let option = document.createElement("option");
            option.setAttribute("value",value);

            option.innerHTML =  paciente.nombre + ' ' + paciente.apellido;

            select.appendChild(option);
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