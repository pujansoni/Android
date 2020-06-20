<?php 
session_start();
include "lib/dao.php";
include "lib/model.php";

$d = new dao();
$m = new model();
extract($_POST);
extract($_GET); 

// Login Check

if(isset($_POST['loginemail'])) {

	$q=$d->select("admin","email='$loginemail' and password='$password'");
	$data=mysqli_fetch_array($q);
	print_r($data);

	if($data>0) {

		$_SESSION['admin_id']=$data['admin_id'];
		$_SESSION['name']=$data['name'];
		$_SESSION['email']=$data['email']; 

            $_SESSION['tmsg']="Login Successfully";
			$_SESSION['tsts']="success";
			$_SESSION['tTitle']=$data['name'];

			header("location:index.php");

	} else {

		header("location:login.php?msgError=Wrong Details");

	}
} 

/*insert category*/
if (isset($_POST['add_category'])) {

    $m->set_data('category_name',$category_name);
	$m->set_data('category_description',$category_description);

	$a = array(
		'category_name'=>$m->get_data('category_name'),
		'category_description'=>$m->get_data('category_description'),
	
	);

	$q=$d->insert("categories",$a);

	if($q>0){

			$_SESSION['tmsg']="Category Inserted Successfully";
			$_SESSION['tsts']="success";
			$_SESSION['tTitle']="Success";

		header("location:manageCategory.php");

	} else{

		echo "Error";
	}
 
}

/*delete category*/

if (isset($_POST['delete_category'])) {
	
	$q=$d->delete("categories","cat_id='$cat_id'");

	if($q>0){

        $_SESSION['tmsg']="Category Deleted Successfully";
		$_SESSION['tsts']="error";
		$_SESSION['tTitle']="Success";  


		header("location:manageCategory.php");

	} else{

		echo "Error";

	}

}

/*edit and update*/

// Update Category
if (isset($_POST['update_category'])) {
	// print_r($_POST);

	$m->set_data('category_name',$category_name);
	$m->set_data('category_description',$category_description);

	$a = array(
		'category_name'=>$m->get_data('category_name'),
		'category_description'=>$m->get_data('category_description'),
	);

	$q=$d->update("categories",$a,"cat_id='$cat_id'");

	if($q>0){
		
            $_SESSION['tmsg']="Category Updated Successfully";
			$_SESSION['tsts']="success";
			$_SESSION['tTitle']="Success";

		header("location:manageCategory.php");

	} else{

		echo "Error";

	}

}

/*for manufacturer*/
/*add manufacturer*/
if (isset($_POST['add_manufacturer'])) {

    $m->set_data('cat_id',$cat_id);
	$m->set_data('manufacturer_name',$manufacturer_name);

	$a = array(
		'cat_id'=>$m->get_data('cat_id'),
		'manufacturer_name'=>$m->get_data('manufacturer_name'),
		'manufacturer_description'=>$m->get_data('manufacturer_description'),
	);

	$q=$d->insert("manufacturer",$a);

	if($q>0){

			$_SESSION['tmsg']="Category Inserted Successfully";
			$_SESSION['tsts']="success";
			$_SESSION['tTitle']="Success";

		header("location:managemanufacturer.php");

	} else{
		echo "Error";
	}
}
/*delete manufacturer*/
if (isset($_POST['delete_manufacturer'])) {
	
	$q=$d->delete("manufacturer","manufacturer_id='$manufacturer_id'");

	if($q>0){

        $_SESSION['tmsg']="Manufacturer Deleted Successfully";
		$_SESSION['tsts']="error";
		$_SESSION['tTitle']="Success";  


		header("location:managemanufacturer.php");

	} else{

		echo "Error";

	}

}
// Update Manufacturer
if (isset($_POST['update_manufacturer'])) {
	// print_r($_POST);

	$m->set_data('cat_id',$cat_id);
	$m->set_data('manufacturer_name',$manufacturer_name);

	$a = array(
		'cat_id'=>$m->get_data('cat_id'),
		'manufacturer_name'=>$m->get_data('manufacturer_name'),
		'manufacturer_description'=>$m->get_data('manufacturer_description'),
	);

	$q=$d->update("manufacturer",$a,"manufacturer_id='$manufacturer_id'");

	if($q>0){
		
            $_SESSION['tmsg']="Manufacturer Updated Successfully";
			$_SESSION['tsts']="success";
			$_SESSION['tTitle']="Success";

		header("location:managemanufacturer.php");

	} else{

		echo "Error";

	}

}


/*insert subcategory*/
//error_reporting(E_ALL | E_STRICT);

if (isset($_POST['add_subcategory']) && isset($_FILES['product_photo'])) {
 // print_r($_POST);
 	$file = $_FILES['product_photo']['tmp_name'];

 	if(file_exists($file)) {

     	$errors     = array();
     	$maxsize    = 2097152;
     	$acceptable = array(
        	 // 'application/pdf',
         	'image/jpeg',
         	'image/jpg',
         	'image/gif',
         	'image/png'
     	);
     	if(($_FILES['product_photo']['size'] >= $maxsize) || ($_FILES["product_photo"]["size"] == 0)) {

        	 $errors[] = 'File too large. File must be less than 2 megabytes.';

     	}
     	if(!in_array($_FILES['product_photo']['type'], $acceptable) && (!empty($_FILES["product_photo"]["type"]))) {

         	$errors[] = 'Invalid file type. Only  JPG, GIF and PNG types are accepted.';

     	}
     
     	if(count($errors) === 0) {

		 	move_uploaded_file($_FILES['product_photo']['tmp_name'], 'img/subcategory/'.$_FILES['product_photo']['name']);
	    	$product_photo= $_FILES['product_photo']['name'];

    		//$m->set_data('cat_id',$cat_id);
    		//$m->set_data('manufacturer_id',$manufacturer_id);
    		$m->set_data('product_name',$product_name);
			$m->set_data('product_sideeffects',$product_sideeffects);
			$m->set_data('product_use',$product_use);
			$m->set_data('product_content',$product_content);
			$m->set_data('product_pregsafety',$product_pregsafety);
			$m->set_data('product_price',$product_price);
    		$m->set_data('product_photo',$product_photo);
    		$m->set_data('manufacturer_id',$manufacturer_id);
 			$a = array(
        		//'cat_id'=>$m->get_data('cat_id'),
        		//'manufacturer_id'=>$m->get_data('manufacturer_id'),
				'product_name'=>$m->get_data('product_name'),
				'product_sideeffects'=>$m->get_data('product_sideeffects'),
				'product_use'=>$m->get_data('product_use'),
				'product_content'=>$m->get_data('product_content'),
				'product_pregsafety'=>$m->get_data('product_pregsafety'),
				'product_price'=>$m->get_data('product_price'),
				'product_photo'=>$m->get_data('product_photo'),
				'manufacturer_id'=>$m->get_data('manufacturer_id'),
 			);
			$q=$d->insert("subcategory",$a);
 			if($q>0){

 				header("location:managesubcategory.php");

 			} else{

  				echo "Error";

 			}
 		} else{

			header("location:manageCategory.php?msg=invalidfile");

 		}
	}
}



/*over new*/


/*
if (isset($_POST['add_subcategory'])) {


    $m->set_data('cat_id',$cat_id);
    $m->set_data('subcat_name',$subcat_name);
	$m->set_data('subcat_description',$subcat_description);
  //  $m->set_data('cat_photo',$cat_photo);

	$a = array(

		'cat_id'=>$m->get_data('cat_id'),
		'subcat_name'=>$m->get_data('subcat_name'),
		'subcat_description'=>$m->get_data('subcat_description'),
		//'subcat_description'=>$m->get_data('subcat_description'),

	);
	
	$q=$d->insert("subcategory",$a);
	if($q>0){
		header("location:managesubcategory.php");
	} else{
		echo "Error";
	}

}
*/
/*over subcategory*/

/*delete subcategory*/

if (isset($_POST['delete_subcategory'])) {
	
	$q=$d->delete("subcategory","product_id='$product_id'");
	if($q>0){

        $_SESSION['tmsg']="Product Deleted Successfully";
		$_SESSION['tsts']="error";
		$_SESSION['tTitle']="Success";  

		header("location:managesubcategory.php");

	} else{

		echo "Error";

	}

}

/*over*/


/*update subcategory*/


if (isset($_POST['update_subcategory']) && isset($_FILES['product_photo'])) {
 // print_r($_POST);
 	$file = $_FILES['product_photo']['tmp_name'];

 	if(file_exists($file)) {

    	$errors     = array();
     	$maxsize    = 2097152;
     	$acceptable = array(
         // 'application/pdf',
         	'image/jpeg',
         	'image/jpg',
         	'image/gif',
         	'image/png'
     	);

     	if(($_FILES['product_photo']['size'] >= $maxsize) || ($_FILES["product_photo"]["size"] == 0)) {

         	$errors[] = 'File too large. File must be less than 2 megabytes.';

     	}
     	if(!in_array($_FILES['product_photo']['type'], $acceptable) && (!empty($_FILES["product_photo"]["type"]))) {

        	$errors[] = 'Invalid file type. Only  JPG, GIF and PNG types are accepted.';

     	}
     
     	if(count($errors) === 0) {

 			move_uploaded_file($_FILES['product_photo']['tmp_name'], 'img/subcategory/'.$_FILES['product_photo']['name']);
    		$product_photo= $_FILES['product_photo']['name'];

    		$m->set_data('cat_id',$cat_id);
    		$m->set_data('manufacturer_id',$manufacturer_id);
    		$m->set_data('product_name',$product_name);
			$m->set_data('product_sideeffects',$product_sideeffects);
			$m->set_data('product_use',$product_use);
			$m->set_data('product_content',$product_content);
			$m->set_data('product_pregsafety',$product_pregsafety);
			$m->set_data('product_price',$product_price);
    		$m->set_data('product_photo',$product_photo);
    		//$m->set_data('manufacturer_id',$manufacturer_id);
 			$a = array(
        		'cat_id'=>$m->get_data('cat_id'),
        		'manufacturer_id'=>$m->get_data('manufacturer_id'),
				'product_name'=>$m->get_data('product_name'),
				'product_sideeffects'=>$m->get_data('product_sideeffects'),
				'product_use'=>$m->get_data('product_use'),
				'product_content'=>$m->get_data('product_content'),
				'product_pregsafety'=>$m->get_data('product_pregsafety'),
				'product_price'=>$m->get_data('product_price'),
				'product_photo'=>$m->get_data('product_photo'),
				//'manufacturer_id'=>$m->get_data('manufacturer_id'),
 			);
			$q=$d->update("subcategory",$a,"product_id='$product_id'");
 			if($q>0){

 				header("location:managesubcategory.php");

 			} else{

  				echo "Error";

 			}
 		} else{

			header("location:manageCategory.php?msg=invalidfile");

 		}
	}
}

/*if (isset($_POST['update_subcategory'])) {

    $m->set_data('cat_id',$cat_id);
    $m->set_data('subcat_name',$subcat_name);
	$m->set_data('subcat_description',$subcat_description);

	$a = array(

		'cat_id'=>$m->get_data('cat_id'),
		'subcat_name'=>$m->get_data('subcat_name'),
		'subcat_description'=>$m->get_data('subcat_description'),
	);
	$q=$d->update("subcategory",$a,"subcat_id='$subcat_id'");
	if($q>0){
		header("location:managesubcategory.php");
	} else{
		echo "Error";
	}

}*/

?>