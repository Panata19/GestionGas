




$(document).ready(function() {
    console.log('start app');
   
  
    
    
    $('.delete').click(function(element) {
    	 var $el = $(this);
         var model = $el.data('model');
         var id = $el.data('id');

         eliminar(model, id)
    })
    
    let btnBuscar = document.getElementById('Reportef');
   
  	btnBuscar.addEventListener('click',(event) =>{
  		event.preventDefault();
  		 var fechaInicio = null;
  	    var fechaFin = null;
  	  fechaInicio = $("#fechaInicio").val();
  	  fechaFin  = $("#fechaFin").val();
  	  console.log(fechaInicio,fechaFin);
  	  report12(fechaInicio,fechaFin)
  	  report23(fechaInicio,fechaFin)
  		
  		
  	});
  
  
  //$('.Reportef').click()
    
    	
    
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


function report12(fechainicio,fechafin){	
	
	console.log(fechainicio,fechafin);
	
	var matriculados = null;
	$.ajax({
		url : '/gastos/todasGastos/' + fechainicio + '/' + fechafin,
		method : 'GET',
		async: false,
		contentType: "application/json",
        headers: { "X-CSRF-TOKEN": $("input[name='_csrf']").val() },
		success : function(response){
			console.log(response);
			matriculados = response;
		},
		error : function(err){
			console.log(err);
		}		
	});
	return matriculados;
	
}

function getRandomColor() {
	  var letters = '0123456789ABCDEF';
	  var color = '#';
	  for (var i = 0; i < 6; i++) {
	    color += letters[Math.floor(Math.random() * 16)];
	  }
	  return color;
}


function report23(fechaInicio,fechaFin){


	var color = Chart.helpers.color;

	
	var  gastos = report12(fechaInicio,fechaFin);
	var colores12 = [];
	var numero12 = [];
	var etiqueta12 = [];

	drawGastos(gastos);

	for(var i in gastos){
		colores12.push(getRandomColor());
		numero12.push(gastos[i].gasto);
		etiqueta12.push(gastos[i].categoria);
		
	}
	
	console.log(colores12)
	console.log(numero12)
	console.log(etiqueta12)
	
	
	var ctx = document.getElementById('canvas').getContext('2d');
	window.myBar = new Chart(ctx, {
		type: 'pie',
		data:  {
			datasets: [{
				data: numero12,
				backgroundColor: colores12,
				label: 'Dataset 1'
			}],
			labels: etiqueta12
		},
		options: {
			responsive: true,
			legend: {
				position: 'top',
			},
			title: {
				display: true,
				text: 'Gastos efectuados desde: ' + fechaInicio + ' hasta: ' +  fechaFin
			}
		}
	});
}

function drawGastos(gastos) {
	var content = $('#gastos-content');
	var html = '';
	
	gastos.forEach(gasto => {
		html += `
		<tr>
			<td>${gasto.categoria}</td>
			<td>${gasto.gasto}</td>
		</tr>
		`
	});
	
	content.html(html);
}

function reportPDFGastos(){	
	var fechaInicio = $("#fechaInicio").val();
  	var fechaFin  = $("#fechaFin").val();

	window.open(`/gastospdf/crear?since=${fechaInicio}&to=${fechaFin}`, '_blank')
	// $.ajax({
	// 	url : '/gastospdf/crear',
	// 	method : 'GET',
	// 	async: false,
	// 	contentType: "application/json",
    //     headers: { "X-CSRF-TOKEN": $("input[name='_csrf']").val() },
	// 	success : function(response){
	// 		console.log({response});
	// 		var newBlob = new Blob([response], {type: "application/pdf"});

    //         if (window.navigator && window.navigator.msSaveOrOpenBlob) {
    //             window.navigator.msSaveOrOpenBlob(newBlob);
    //             return;
    //         }
            
    //         var data = window.URL.createObjectURL(newBlob);
    //         var link = document.createElement('a');
    //         link.href = data;
    //         link.download = 'reporte.pdf';
    //         link.click();
    //         setTimeout(function(){
    //             window.URL.revokeObjectURL(data);
    //         }, 100);
	// 	},
	// 	error : function(err){
	// 		console.log({err});
	// 	}		
	// });
	
}


$(function() {
    $(".datepicker").datepicker({
    	dateFormat: 'yy-mm-dd'
    });
});


