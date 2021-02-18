
$(document).ready(function() {
    console.log('start app');

  
    $('.delete').click(function(element) {
        var $el = $(this);
        var model = $el.data('model');
        var id = $el.data('id');

        eliminar(model, id)
    })
})



function eliminar(modelo, id) {
	Swal.fire({
		  title: 'Se va a eliminar el registro',
		  text: "¿Seguro?, no podrás revertir esta operación",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Si, eliminar',
		  cancelButtonText: 'No, salir',
		}).then((result) => {
		  if (result.value) {        
                $.ajax({
                    url: '/' + modelo + '/delete/' + id,
                    method: 'GET',
                    contentType: "application/json",
                    headers: { "X-CSRF-TOKEN": $("input[name='_csrf']").val() },
                    success: function (response) {
                        Swal.fire({
                            title: 'Registro eliminado correctamente',
                            text: "Se ha eliminado correctamente el registro.",
                            icon: 'success',
                            confirmButtonText: 'Aceptar'
                          }).then((result) => {
                            window.location.reload();
                          })
                    },
                    error: function (err) {
                        Swal.fire({
                            title: '¡Ha ocurrido un error!',
                            text: "No se ha podido eliminar el registro",
                            icon: 'error',
                            confirmButtonText: 'Ok'
                          })
                    }
                });
		  }
		})
	
}


$(function() {
    $(".datepicker").datepicker({
    	dateFormat: 'yy-mm-dd'
    });
});

