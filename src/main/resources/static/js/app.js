
$(document).ready(function() {
    console.log('start app');

    window.onload = function(){
		
    	reportgraficodia();	
		
	}
    
    
    $('.delete').click(function(element) {
    	 var $el = $(this);
         var model = $el.data('model');
         var id = $el.data('id');

         eliminar(model, id)
    })
    
    
    $('.modificar').click(function(element) {
    	 var $el = $(this);
         var model = $el.data('model');
         var id = $el.data('id');

         modificar(model, id)
    })
    
 
    
    let btnBuscar = document.getElementById('Reportef');
   
  	btnBuscar.addEventListener('click',(event) =>{
  	  event.preventDefault();
  	  var fechaInicio = null;
  	  var fechaFin = null;
  	  fechaInicio = $("#fechaInicio").val();
  	  fechaFin  = $("#fechaFin").val();
  	  console.log(fechaInicio,fechaFin);
  	 // report12(fechaInicio,fechaFin);
  	  // report23(fechaInicio,fechaFin,0);
  	  report23(fechaInicio,fechaFin);
  	});
  
  

 
  		
  	

    
    	
    
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




function modificar(modelo, id) {
	Swal.fire({
		  title: 'Se va a cambiar de estado el registro',
		  text: "¿Seguro?, no podrás revertir esta operación",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Si, cambiar',
		  cancelButtonText: 'No, salir',
		}).then((result) => {
		  if (result.value) {        
                $.ajax({
                    url: '/' + modelo + '/update/' + id,
                    method: 'GET',
                    contentType: "application/json",
                    headers: { "X-CSRF-TOKEN": $("input[name='_csrf']").val() },
                    success: function (response) {
                        Swal.fire({
                            title: 'Registro ha cambiado correctamente',
                            text: "Se ha cambiado de estado correctamente.",
                            icon: 'success',
                            confirmButtonText: 'Aceptar'
                          }).then((result) => {
                            window.location.reload();
                          })
                    },
                    error: function (err) {
                        Swal.fire({
                            title: '¡Ha ocurrido un error!',
                            text: "No se ha podido cambiar de estado el registro",
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

	var colores12 = [];
	var numero12 = [];
	var etiqueta12 = [];
	var ingreso = 0;
	var totalGasto = 0;
	var ganancia = 0;
	var  gastos = report12(fechaInicio,fechaFin);
	
	
	console.log(gastos);
	for(var i in gastos){
		
		if((gastos.length-1) == i ){
			ingreso = gastos[i].gasto;
			gastos.splice(i, 1);
		}else{
			colores12.push(getRandomColor());
			numero12.push(gastos[i].gasto);
			totalGasto = totalGasto + gastos[i].gasto;
			etiqueta12.push(gastos[i].categoria);
			
		}
	}
	
	ganancia = ingreso - totalGasto; 
	console.log("Ingreso = " + ingreso);
	console.log("Gastos = " + totalGasto);
	console.log(gastos);
	drawGastos(gastos);
	
	document.getElementById("lingresos").innerHTML ="$ " + ingreso;
	document.getElementById("lgastos").innerHTML = "$ " + totalGasto ;
	document.getElementById("lganancias").innerHTML = "$ " + ganancia ;
	
	
	

	
	var config = {
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
		};
	
	
	document.getElementById("canvas").remove();
	var canvas = document.createElement("canvas");
	canvas.id = "canvas"; 
	document.getElementById("contenedorr").appendChild(canvas);
	
	var ctx = document.getElementById('canvas').getContext('2d');
	window.myBar = new Chart(ctx,config);
	
	

	

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
	print();
	return;
	var fechaInicio = $("#fechaInicio").val();
  	var fechaFin  = $("#fechaFin").val();

	window.open(`/gastospdf/crear?since=${fechaInicio}&to=${fechaFin}`, '_blank');
}


$(function() {
    $(".datepicker").datepicker({
    	dateFormat: 'yy-mm-dd',
    });
    $('.datepicker').datepicker("setDate", new Date() );
});







//Reporte del dia 
//obtencion de los datos del dia 
function reportediaactual(){	
	
	
	
	var matriculados = null;
	$.ajax({
		url : '/gastos/gastosdeldia',
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


//grafico de los gastos 

function reportgraficodia(){


	var color = Chart.helpers.color;

	var colores12 = [];
	var numero12 = [];
	var etiqueta12 = [];
	var  gastos = reportediaactual();
	
	for(var i in gastos){
		colores12.push(getRandomColor());
		numero12.push(gastos[i].gasto);
		etiqueta12.push(gastos[i].categoria);
	}
	console.log(colores12);
	console.log(numero12);
	console.log(etiqueta12);
	

	
	var config = {
			type: 'bar',
			data:  {
				datasets: [{
					data: [numero12[0]],
					backgroundColor: ["#E61717"],
					label: etiqueta12[0]
					
				},
				{
					data: [numero12[1]],
					backgroundColor: ["#17E685"],
					label: etiqueta12[1]
					
				},
				{
					data: [numero12[2]],
					backgroundColor: ["#FCF000"],
					label: etiqueta12[2]
					
				}],
				labels: ["Flujo de dinero"]
			},
			options: {
				responsive: true,
				legend: {
					position: 'top',
				},
				title: {
					display: true,
					text: 'Flujo de dinero de hoy' 
				},
				scales: {
			        yAxes: [{
			            ticks: {
			                beginAtZero: true
			            }
			        }]
			    },
			}
		};
	
	
	
	
	var ctx = document.getElementById('canvas1').getContext('2d');
	window.myBar = new Chart(ctx,config);
	
	

	

}


























