/*
 * $('#saveBook').click(function(e) { e.preventDefault();
 * jQuery('#formBook').validationEngine({ autoHidePrompt : true });
 * });
 *//*
jQuery(document).ready(function() {
		jQuery('#formBook').validationEngine({
			autoHidePrompt : true 
		});
});*/

function buttonAction(data, type, full) {
			return '<button class="btn btn-link btn-sm" onclick=""><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>'
					+ '&nbsp;||&nbsp; <button onclick="deleteBook('+data+')" class="btn btn-link btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>';
};

function deleteBook(id){
	var message = confirm("Are you sure to delete a data book with id "+id+" ?");
	if(message == true){
		$.ajax({
			async : false,
			type : 'POST',
			url : 'delete/'+id,
			
			success : function (data){
				$('#bookTable').DataTable().ajax.reload();
				// get message
				$('#message').html('<div class="alert alert-success alert-dismissable" id="alert">'
									+ '<button type="button" class="close" data-dismiss="alert" '
									+ 'area-hidden="true">&times;</button>'+ data.message+ '</div>');
				closeAlert();
			},
			error : function (data){
				$('#bookTable').DataTable().ajax.reload();
				$('#message').html('<div class="alert alert-success alert-dismissable" id="alert">'
									+ '<button type="button" class="close" data-dismiss="alert" '
									+ 'area-hidden="true">&times;</button>'+ data.message+ '</div>');
				closeAlert();
			}
		});
	}else{
		return false;
	}
};

$('#saveBook').click(function(e) {
	e.preventDefault();
	jQuery('#formBook').validationEngine('attach',{onValidationComplete : function(form,status) {
		if (status == true) {
			alert();
			var book = {
						name : $('#name').val(),
						author : $('#author').val(),
						releaseDate : $('#releaseDate').val(),
						price : $('#price').val(),
						stock : $('#stock').val()
						}
			$.ajax({
					type : 'post',
					async : false,
					url : "insert",
					dataType : 'json',
					data : JSON.stringify(book),
					contentType : 'application/json',
				success : function(data) {
						// close modal
						$('#myModal').modal("hide");
						// reload data
						$('#bookTable').DataTable().ajax.reload();
						// set empty
						setEmptyInput();
						// get message
						$('#message').html('<div class="alert alert-success alert-dismissable" id="alert">'
											+ '<button type="button" class="close" data-dismiss="alert" '
											+ 'area-hidden="true">&times;</button>'+ data.message+ '</div>');
						closeAlert();
				},
				error : function(data) {
						setEmptyInput();
						// close modal
						$('#myModal').modal("hide");
						$('#message').html('<div class="alert alert-danger alert-dismissable" id="alert">'
											+ '<button type="button" class="close" data-dismiss="alert" '
											+ 'area-hidden="true">&times;</button>'+ data.message+ '</div>');
						closeAlert();
				}
			});
		} else {
				return false;
		}
	}
});

jQuery('#formBook').validationEngine('validate');
});

function closeAlert() {
	$('#alert').fadeTo(5000, 500).slideUp(500, function() {
		$('#alert').alert('close');
	});
}

function setEmptyInput() {
	$('#name').val("");
	$('#author').val("");
	$('#releaseDate').val("");
	$('#price').val("");
	$('#stock').val("");
}

// hide modal bootstrap function
$('#myModal').on('hidden.bs.modal', function(e) {
	setEmptyInput();
	$('#bookTable').DataTable().ajax.reload();
});

/*// delete book
$('#delete_book').click(function() {
	var data;
	alert("delete");
	
	 * $.ajax({ })
	 
});

//update book
$('#update-book').click(function() {
	var data;
	alert("update");
	
	 * $.ajax({ })
	 
});*/


