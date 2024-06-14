window.addEventListener('load', function () {
    (function(){

      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         for(odontologo of data){

            var select = document.getElementById("get_odontologo_select");
            let value = odontologo.id;

            select.innerHTML = '<option value= ' + value + '  >' + odontologo.nombre + ' ' +odontologo.apellido + '</option>'

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