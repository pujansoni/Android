<?php include"common/header.php"; ?>
    <!-- /#top -->
    <?php include"common/sidebar.php"; ?>
<?php 
if(isset($_POST['edit_category'])) {
  extract($_POST); 
  $q=$d->select("categories","cat_id='$cat_id'","");
  $data=mysqli_fetch_array($q);
 ?> 
 <div id="content" class="bg-container">
            <header class="head">
                <div class="main-bar row">
                    <div class="col-xs-6">
                        <h4 class="m-t-5">
                            <i class="fa fa-home"></i>
                            Dashboard
                        </h4>
                    </div>
                </div>
            </header>
            <div class="outer">

              <div class="inner bg-container">
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="card">
                                    <div class="card-header bg-white">
                                        <i class="fa fa-file-text-o"></i>
                                        Block Validation
                                    </div>
                                    <div class="card-block m-t-35">
                                        <form method="post" 
                                        action="controller.php" class="form-horizontal  login_validator" id="form_block_validator">



                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">category Name *</label>
                                                </div>
                                                <div class="col-lg-4">

                                                 <input type="hidden" name="cat_id" value="<?php echo $data['cat_id']; ?>">
                                                    <input type="text" id="required2" name="category_name" 
                                                    value="<?php echo $data['category_name']; ?>" 
                                                     class="form-control"
                                                     placeholder="Category Name" required>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-lg-4 text-lg-right">
                                                    <label for="email2" class="form-control-label">Category Description *</label>
                                                </div>
                                                <div class="col-lg-4">

                                                  <textarea class="form-control" id="textArea" name="category_description" placeholder="Category Description" required>
                                                      <?php echo $data['category_description']; ?>
                                                  </textarea>

                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Category Photo *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                   <img width="100px" height="100px" src="img/subcategory/<?php echo $data['product_photo']; ?>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Change Category Photo *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="file" id="required2" name="cat_photo" class="form-control"
                                                     placeholder="Category Photo" required>
                                                </div>
                                            </div>

                                            <div class="form-actions form-group row">
                                                <div class="col-lg-4 push-lg-4">

                                                <button type="submit"
                                                 name="update_category" class="btn btn-primary" value="Validate" >Update Category</button>
                                                    
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- /.col-lg-12 -->
                        </div>
                        <!-- /.row -->
                      
                  
                    </div>
                </div>
                <!-- /.outer -->
            </div>
            <!-- /#content -->
        </div>

<?php } else { ?>


        <div id="content" class="bg-container">
            <header class="head">
                <div class="main-bar row">
                    <div class="col-xs-6">
                        <h4 class="m-t-5">
                            <i class="fa fa-home"></i>
                            Dashboard
                        </h4>
                    </div>
                </div>
            </header>
            <div class="outer">

              <div class="inner bg-container">
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="card">
                                    <div class="card-header bg-white">
                                        <i class="fa fa-file-text-o"></i>
                                        Block Validation
                                    </div>
                                    <div class="card-block m-t-35">
                                        <form method="post" 
                                        action="controller.php" class="form-horizontal  login_validator" id="form_block_validator">
                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">category Name *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="text" id="required2" name="category_name"
                                                     class="form-control"
                                                     placeholder="Category Name" required>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-lg-4 text-lg-right">
                                                    <label for="email2" class="form-control-label">Category Description *</label>
                                                </div>
                                                <div class="col-lg-4">

                                                  <textarea class="form-control" id="textArea" name="category_description" placeholder="Category Description" required></textarea>

                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Category Photo *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="file" id="required2" name="cat_photo" class="form-control"
                                                     placeholder="Product Photo" required>
                                                </div>
                                            </div>

                                            <div class="form-actions form-group row">
                                                <div class="col-lg-4 push-lg-4">

                                                <button type="submit"
                                                 name="add_category" class="btn btn-primary" value="Validate" >Add Category</button>

                                                
                                                    
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- /.col-lg-12 -->
                        </div>
                        <!-- /.row -->
                      
                  
                    </div>
                </div>
                <!-- /.outer -->
            </div>
            <!-- /#content -->
        </div>

        <?php } ?>
    </div>
    <!--wrapper-->
    <?php include "common/footer.php"; ?>
