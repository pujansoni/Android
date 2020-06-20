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

/*insert subcategory*/

if (isset($_POST['add_subcategory'])) {

    $m->set_data('cat_id',$cat_id);
    $m->set_data('subcat_name',$subcat_name);
	$m->set_data('subcat_description',$subcat_description);

	$a = array(

		'cat_id'=>$m->get_data('cat_id'),
		'subcat_name'=>$m->get_data('subcat_name'),
		'subcat_description'=>$m->get_data('subcat_description'),
	);
	
	$q=$d->insert("subcategory",$a);
	if($q>0){
		header("location:managesubcategory.php");
	} else{
		echo "Error";
	}

}

?>