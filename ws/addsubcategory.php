<?php include"common/header.php"; ?>
    <!-- /#top -->
    <?php include"common/sidebar.php"; ?>

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
                                                   <select name="cat_id" class="form-control" required>
                                          <option value="">Select One:</option>
                                         <?php
                                           $i=1; 
                                           $q=$d->select("categories","","");
                                          while ($data=mysqli_fetch_array($q)) {
                       
                                         ?>
                                        <option value="<?php echo $data['cat_id']; ?>"><?php echo $data['category_name']; ?></option>
                                          <?php } ?>
                                         </select>
                                        </div>
                                        </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Subcategory Name *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="text" id="required2" name="subcat_name"
                                                     class="form-control"
                                                     placeholder="Subcategory Name" required>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-lg-4 text-lg-right">
                                                    <label for="email2" class="form-control-label">Category Description *</label>
                                                </div>
                                                <div class="col-lg-4">

                                                  <textarea class="form-control" id="textArea" name="subcat_description" placeholder="Subcategory Description" required></textarea>

                                                </div>
                                            </div>
                                            <div class="form-actions form-group row">
                                                <div class="col-lg-4 push-lg-4">

                                                <button type="submit"
                                                 name="add_subcategory" class="btn btn-primary" value="Validate" >Add Subategory</button>
                                                    
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
                      
                  
             </div>
                </div>
                <!-- /.outer -->
            </div>
            <!-- /#content -->
        </div>

    </div>
    <!--wrapper-->
    <?php include "common/footer.php"; ?>