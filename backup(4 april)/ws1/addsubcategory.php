<?php include"common/header.php"; ?>
    <!-- /#top -->
    <?php include"common/sidebar.php"; ?>


<?php 
if(isset($_POST['edit_subcategory'])) {
  extract($_POST); 
  $q=$d->select("subcategory"," product_id='$product_id'","");
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
                                        action="controller.php" class="form-horizontal  login_validator" id="form_block_validator"  
                                        enctype="multipart/form-data">

                                         <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Manufacturer Name *</label>
                                                </div>

                                                 <input type="hidden" name="product_id" value="<?php echo $data['manufacturer_id']; ?>">
                                                <div class="col-lg-4">
                                                   <select name="manufacturer_id" class="form-control" required>
                                          <option value="">Select One:</option>
                                         <?php
                                           $i=1; 
                                           $q=$d->select("manufacturer","","");
                                          while ($data1=mysqli_fetch_array($q)) {
                       
                                         ?>
                                        <option  <?php if ($data['manufacturer_id']==$data1['manufacturer_id']) {

                                            echo "selected";
                                           
                                        } ?> value="<?php echo $data1['mmanufacturer_id']; ?>"><?php echo $data1['manufacturer_name']; ?></option>
                                          <?php } ?>
                                         </select>
                                        </div>
                                        </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Name *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="text" id="required2" name="product_name"
                                                     class="form-control"
                                                     placeholder="Product Name" required value="<?php echo $data['product_name']; ?>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4 text-lg-right">
                                                    <label for="email2" class="form-control-label">Product SideEffects *</label>
                                                </div>
                                                <div class="col-lg-4">

                                                  <textarea class="form-control" id="textArea" name="product_sideeffects" placeholder="Product SideEffects" required><?php echo $data['product_sideeffects']; ?></textarea>

                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4 text-lg-right">
                                                    <label for="email2" class="form-control-label">Product Use *</label>
                                                </div>
                                                <div class="col-lg-4">

                                                  <textarea class="form-control" id="textArea" name="product_use" placeholder="Product Use" required><?php echo $data['product_use']; ?></textarea>

                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Content *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="text" id="required2" name="product_content"
                                                     class="form-control"
                                                     placeholder="Product Content" required value="<?php echo $data['product_content']; ?>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Pregnancy Safety *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="text" id="required2" name="product_pregsafety"
                                                     class="form-control"
                                                     placeholder="Product Pregnancy Safety" required value="<?php echo $data['product_pregsafety']; ?>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Price *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="text" id="required2" name="product_price"
                                                     class="form-control"
                                                     placeholder="Product Price" required value="<?php echo $data['product_price']; ?>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Photo *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                   <img width="100px" height="100px" src="img/subcategory/<?php echo $data['product_photo']; ?>">
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Change Product Photo *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="file" id="required2" name="product_photo" class="form-control"
                                                     placeholder="Product Photo" required>
                                                </div>
                                            </div>

                                            <div class="form-actions form-group row">
                                                <div class="col-lg-4 push-lg-4">

                                                <button type="submit"
                                                 name="update_subcategory" class="btn btn-primary" value="Validate" >Update Subategory</button>
                                                    
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

        <<?php }else {?>

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
                                        action="controller.php" class="form-horizontal  login_validator" id="form_block_validator" 
                                        enctype="multipart/form-data">

                                         

                                        <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Manufacturer Name *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                   <select name="manufacturer_id" class="form-control" required>
                                          <option value="">Select One:</option>
                                         <?php
                                           $i=1; 
                                           $q=$d->select("manufacturer","cat_id","$cat_id");
                                          while ($data=mysqli_fetch_array($q)) {
                       
                                         ?>
                                        <option value="<?php echo $data['manufacturer_id']; ?>"><?php echo $data['manufacturer_name']; ?></option>
                                          <?php } ?>
                                         </select>
                                        </div>
                                        </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Name *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="text" id="required2" name="product_name"
                                                     class="form-control"
                                                     placeholder="Product Name" required>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-lg-4 text-lg-right">
                                                    <label for="email2" class="form-control-label">Product SideEffects *</label>
                                                </div>
                                                <div class="col-lg-4">

                                                  <textarea class="form-control" id="textArea" name="product_sideeffects" placeholder="Product SideEffects" required></textarea>

                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4 text-lg-right">
                                                    <label for="email2" class="form-control-label">Product Use *</label>
                                                </div>
                                                <div class="col-lg-4">

                                                  <textarea class="form-control" id="textArea" name="product_use" placeholder="Product Use" required></textarea>

                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Content *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <textarea class="form-control" id="textArea" name="product_content" placeholder="Product content" required></textarea>

                                                    <!--

                                                        aa pujan no bekar code
                                                        JUST IGNORE IT
                                                    <input type="text" id="required2" name="product_content
                                                     class="form-control"
                                                     placeholder="Product Content" required>
                                                     -->
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Pregnancy Safety *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <textarea class="form-control" id="textArea" name="product_pregsafety" placeholder="Product pregsafty" required></textarea>
                                                    <!--
                                                    <input type="text" id="required2" name="product_pregsafety
                                                     class="form-control"
                                                     placeholder="Product Pregnancy Safety" required>
                                                    -->
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Price *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <textarea class="form-control" id="textArea" name="product_price" placeholder="Product Price" required></textarea>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <div class="col-lg-4  text-lg-right">
                                                    <label for="required2" class="form-control-label">Product Photo *</label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="file" id="required2" name="product_photo" class="form-control"
                                                     placeholder="Product Photo" required>
                                                </div>
                                            </div>


                                            <div class="form-actions form-group row">
                                                <div class="col-lg-4 push-lg-4">

                                                <button type="submit"
                                                 name="add_subcategory" class="btn btn-primary" value="Validate" >Add Product</button>
                                                    
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
                </div>
                <!-- /.outer -->
            </div>
            <!-- /#content -->
        </div>

    </div>
    <!--wrapper-->
    <?php include "common/footer.php"; ?>