<?php 
session_start();
include "lib/dao.php";
include "lib/model.php";

$d = new dao();
$m = new model();
extract($_POST);
extract($_GET);

$response = array();
if (isset($_POST['add_category'])) {
	// print_r($_POST);


	$file = $_FILES['cat_photo']['tmp_name'];
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
	    if(($_FILES['cat_photo']['size'] >= $maxsize) || ($_FILES["cat_photo"]["size"] == 0)) {
	        $errors[] = 'File too large. File must be less than 2 megabytes.';
	    }

	    if(!in_array($_FILES['cat_photo']['type'], $acceptable) && (!empty($_FILES["cat_photo"]["type"]))) {
	        $errors[] = 'Invalid file type. Only  JPG, GIF and PNG types are accepted.';
	    }
	    
	    if(count($errors) === 0) {

	move_uploaded_file($_FILES['cat_photo']['tmp_name'],
	 'img/category/'.$_FILES['cat_photo']['name']);
    $cat_photo= $_FILES['cat_photo']['name'];

	$m->set_data('category_name',$category_name);
	$m->set_data('category_description',$category_description);
	$m->set_data('cat_photo',$cat_photo);

	$a = array(
		'category_name'=>$m->get_data('category_name'),
		'category_description'=>$m->get_data('category_description'),
		'cat_photo'=>$m->get_data('cat_photo')
	
	);

	$q=$d->insert("categories",$a);
	if($q>0){ 
		 $response["success"] = 1;
		 $response["message"] = "Category Added.";
         echo json_encode($response);
		//header("location:manageCategory.php");
	} else{
		 $response["success"] = 0;
		 $response["message"] = "Error .";
         echo json_encode($response);
		//echo "Error";
	}
	} else{
		$response["success"] = 2;
		 $response["message"] = "invalidfile";
         echo json_encode($response);

//header("location:manageCategory.php?msg=invalidfile");

	}
}
/*
	$m->set_data('category_name',$category_name);
	$m->set_data('category_description',$category_description);

	$a = array(
		'category_name'=>$m->get_data('category_name'),
		'category_description'=>$m->get_data('category_description'),
	);

	$q=$d->insert("categories",$a);
	if($q>0){
		 $response["success"] = 1;
		 $response["message"] = "Category Added.";
         echo json_encode($response);
	} else{
		 $response["success"] = 0;
		 $response["message"] = "Error .";
         echo json_encode($response);
	}*/
}

// View Category Json
if(isset($_GET['view_category'])) {
	$q=$d->select("categories","","");
    if (mysqli_num_rows($q) > 0) {
        $response["category"] = array();
        while ($row = mysqli_fetch_array($q)) {
            $category = array();
            $category["cat_id"] = $row["cat_id"];
            $category["category_name"] = $row["category_name"];
            $category["category_description"] = $row["category_description"];
            $category["cat_photo"] = $row["cat_photo"];

            // push single product into final response array
            array_push($response["category"], $category);
        }
         $response["success"] = 1;
         $response["path"] = "http://192.168.0.114/admin_theme2/img/category/";
         // echoing JSON response
        echo json_encode($response);
    } else {
        $response["success"] = 0;
        $response["message"] = "No Category found";
    // echo no users JSON
        echo json_encode($response);
    }
}

// Edit Category
if (isset($_POST['edit_category'])) {
	// print_r($_POST);

	$m->set_data('category_name',$category_name);
	$m->set_data('category_description',$category_description);

	$a = array(
		'category_name'=>$m->get_data('category_name'),
		'category_description'=>$m->get_data('category_description'),
	);

	$q=$d->update("categories",$a,"cat_id='$cat_id'");
	if($q>0){
		 $response["success"] = 1;
		 $response["message"] = "Category Updated.";
         echo json_encode($response);
	} else{
		 $response["success"] = 0;
		 $response["message"] = "Error .";
         echo json_encode($response);
	}
}

if (isset($_POST['delete_category'])) {
	
	$q=$d->delete("categories","cat_id='$cat_id'");
	if($q>0){
		header("location:manageCategory.php");
	} else{
		echo "Error";
	}

}
 

/*inset subcategory */

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
/*delete subcategory*/
if (isset($_POST['delete_subcategory'])) {
	
	$q=$d->delete("subcategory","subcat_id='$subcat_id'");
	if($q>0){
		header("location:managesubcategory.php");
	} else{
		echo "Error";
	}

}



/************************  Android CWS  ******************************/


/*registration webservice*/

if(isset($_GET['ws_signup'])) {


if(isset($_POST) && !empty($_POST) )
{
  

try {

if (isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['email_id']) && isset($_POST['password']) && isset($_POST['mobile'])) {

        extract($_POST);
      $q=$d->select("user","email_id='$email_id'");
      $data=mysqli_fetch_array($q);
      if($data>0) {
         $response["success"] = 0;
        $response["message"] = "Email is Alerady register";
        echo json_encode($response);
      } else {

      $last_auto_id=$d->last_auto_id("user");
      $res=mysqli_fetch_array($last_auto_id);
      $user_id=$res['Auto_increment'];
        
            $m->set_data('first_name',$first_name);
            $m->set_data('last_name',$last_name);
            $m->set_data('email_id',$email_id);
            $m->set_data('password',$password);
            $m->set_data('mobile',$mobile);
            $a1= array ( 
              'first_name'=> $m->get_data('first_name'),
              'last_name'=> $m->get_data('last_name'),
              'email_id'=>$m->get_data('email_id'),
              'password'=>$m->get_data('password'),
              'mobile'=>$m->get_data('mobile')
              );
            $insert=$d->insert('user',$a1);

            if($insert >0) {
                //$_SESSION['msg1']="$email  is already register.";
                $response["user_id"]=$user_id;
                $response["first_name"] = $first_name;
                $response["last_name"] = $last_name;
                $response["email_id"] = $email_id;
                $response["success"] = 1;
                $response["message"] = "Registration successfully.";
                echo json_encode($response);
            } else {
                $response["success"] = 0;
                $response["message"] = "Oops! An error occurred.";
               echo json_encode($response);
            }
          }
    } else {
        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";
        echo json_encode($response);
    }

    
} catch (Exception $e) {

   $response["exception"] = $e->getMessage();
   echo json_encode($response);
}
  


    
}
   
}

/*login ws*/

if(isset($_GET['ws_signin'])) {

if(isset($_POST) && !empty($_POST) )//it can be $_GET doesn't matter
{
  $email = $_POST['email_id'];
  $password = $_POST['password'];

  $q=$d->select("user","email_id='$email_id' AND password='$password'");
  $data=mysqli_fetch_array($q);
  if($data>0) {
    // feach data form table
    $user_id = $data['user_id'];
    $first_name = $data['first_name'];
    $last_name = $data['last_name'];
    $email_id = $data['email_id'];

    // send email
    
    $response["success"] = 1;
    $response["user_id"] = $user_id;
    $response["first_name"] = $first_name;
    $response["last_name"] = $last_name;
    $response["email_id"] = $email_id;
    $response["message"] = "Login successfully.";
        echo json_encode($response);
  } else {
    $response["success"] = 0;
    $response["message"] = "Oops! An error occurred.";
        echo json_encode($response);
    }
}
}

/*forgote password ws*/
if(isset($_GET['ws_forgotepassword'])) {

  if(isset($_POST) && !empty($_POST) )//it can be $_GET doesn't matter
{
  $email = $_POST['email_id'];
  $q=$d->select("user","email_id='$email_id'");
  $data=mysqli_fetch_array($q);
  if($data>0) {
    // feach data form table
    $first_name = $data['first_name'];
    $email_id = $data['email_id'];
    $password = $data['password'];
    // send email
    $subject = "forgot Password  xyz Technologies";
    $txt = "Hello $first_name , Your xyz Account Password is : $password !";
    $headers = "From: admin@xyztechnologies.com";
    mail($email,$subject,$txt,$headers);
    
    $response["success"] = "success";
    $response["first_name"] = $first_name;
 
        echo json_encode($response);
  } else {
    $response["success"] = "failure";
    echo json_encode($response);
  }
}

}


/*faq*/

if(isset($_GET['ws_faq'])) {
  
  $q=$d->select("faq","");
    if (mysqli_num_rows($q) > 0) {
        
            $response["faq"] = array();

        while ($row = mysqli_fetch_array($q)) {
            $faq = array();
            $faq["faqId"] = $row["faqId"];
            $faq["question"] = $row["question"];
            $faq["answer"] = $row["answer"];
            // push single product into final response array
            array_push($response["faq"], $faq);
        }
         $response["success"] = 1;
         // echoing JSON response
        echo json_encode($response);
    } else {
        $response["success"] = 0;
        $response["message"] = "No faq found";
    // echo no users JSON
        echo json_encode($response);
    }

}



// Display user Json
if(isset($_GET['view_user'])) {
  $q=$d->select("user","","");
    if (mysqli_num_rows($q) > 0) {
        $response["user"] = array();
        while ($row = mysqli_fetch_array($q)) {
            $user = array();
            $user["user_id"] = $row["user_id"];
            $user["first_name"] = $row["first_name"];
            $user["last_name"] = $row["last_name"];
            $user["email_id"] = $row["email_id"];

            // push single product into final response array
            array_push($response["user"], $user);
        }
         $response["success"] = 1;
       //  $response["path"] = "http://192.168.0.114/admin_theme2/img/category/";
         // echoing JSON response
        echo json_encode($response);
    } else {
        $response["success"] = 0;
        $response["message"] = "No User found";
    // echo no users JSON
        echo json_encode($response);
    }
}
/*over Display user*/


 ?>