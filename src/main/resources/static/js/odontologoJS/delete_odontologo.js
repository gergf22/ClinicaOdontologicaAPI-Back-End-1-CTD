function deleteBy(id)
{

    console.log("script funciona")
          const url = '/odontologos/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json())


          let row_id = `${id}`
          console.log(row_id)
          document.getElementById(row_id).remove();

}