<div class="modal fade" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Add Book</h4>
			</div><br/>
			<form class="form-horizontal" method="post" action="" id="formBook">
				<div class="form-group">
					<label class="col-sm-4 control-label">Nama Buku</label>
					<div class="col-sm-4">
						<input type="text"
							class="validate[required, minSize[3], maxSize[30]] form-control input-sm" id="name"
							name="name" placeholder="name">
					</div>

				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Author</label>
					<div class="col-sm-4">
						<input type="text"
							class="validate[required, minSize[3], maxSize[30]] form-control input-sm" id="author"
							name="author" placeholder="author">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Release Date</label>
					<div class="col-sm-4">
						<input type="text"
							class="validate[required, custom[date]] form-control input-sm"
							id="releaseDate" name="releaseDate" title="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Price</label>
					<div class="col-sm-4">
						<input type="text"
							class=" validate[required, custom[integer]] form-control input-sm" id="price"
							name="price" title="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Stock</label>
					<div class="col-sm-4">
						<input type="text" id="stock" name="stock" title=""
							class="validate[required, custom[integer]] form-control input-sm">
					</div>
				</div>
				<div class="modal-footer">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<input type="submit" class="btn btn-default" id="saveBook"	value="submit" >						
					</div>
				</div>
			</form>
		</div>
	</div>
</div>