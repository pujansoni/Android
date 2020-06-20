<?php 
session_start();
include "lib/dao.php";
include "lib/model.php";
$d = new dao();
$m = new model();
extract($_POST);
extract($_GET);
$response = array();

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
      // push single product into final response array
      array_push($response["category"], $category);
    }
    $response["success"] = 1;
    // echoing JSON response
    echo json_encode($response);
  } 
  else {
    $response["success"] = 0;
    $response["message"] = "No Category found";
    // echo no users JSON
    echo json_encode($response);
  }
}

// View Manufacturer Json
if(isset($_GET['view_manufacturer'])) {
  $q=$d->select("manufacturer","","");
  if (mysqli_num_rows($q) > 0) {
    $response["manufacturer"] = array();
    while ($row = mysqli_fetch_array($q)) {
      $manufacturer = array();
      $manufacturer["manufacturer_id"] = $row["manufacturer_id"];
      $manufacturer["manufacturer_name"] = $row["manufacturer_name"];
      $manufacturer["manufacturer_description"] = $row["manufacturer_description"];
      $manufacturer["cat_id"] = $row["cat_id"];
      // push single product into final response array
      array_push($response["manufacturer"], $manufacturer);
    }
    $response["success"] = 1;
    // echoing JSON response
    echo json_encode($response);
  } 
  else {
    $response["success"] = 0;
    $response["message"] = "No Manufacturer found";
    // echo no users JSON
    echo json_encode($response);
  }
}

//View Subcategory Json
if(isset($_GET['view_product'])) {
  $q=$d->select("subcategory","","");
  if (mysqli_num_rows($q) > 0) {
    $response["subcategory"] = array();
    while ($row = mysqli_fetch_array($q)) {
      $subcategory = array();
      $subcategory["product_id"] = $row["product_id"];
      $subcategory["product_name"] = $row["product_name"];
      $subcategory["product_sideeffects"] = $row["product_sideeffects"];
      $subcategory["product_use"] = $row["product_use"];
      $subcategory["product_content"] = $row["product_content"];
      $subcategory["product_pregsafety"] = $row["product_pregsafety"];
      $subcategory["product_price"] = $row["product_price"];
      $subcategory["product_photo"] = $row["product_photo"];
      $subcategory["manufacturer_id"] = $row["manufacturer_id"];
      // push single product into final response array
      array_push($response["subcategory"], $subcategory);
    }
    $response["success"] = 1;
    $response["path"] = "http://192.168.1.40/ws/img/subcategory/";
    // echoing JSON response
    echo json_encode($response);
  }
  else {
    $response["success"] = 0;
    $response["message"] = "No Subcategory found";
    // echo no users JSON
    echo json_encode($response);
  }
}

/*post when manufacturer_id is clicked*/
if(isset($_GET['view_manufacturer_product'])) {
if(isset($_POST) && !empty($_POST) )//it can be $_GET doesn't matter
{
  $manufacturer_id = $_POST['manufacturer_id'];
  $q=$d->select("subcategory","manufacturer_id='$manufacturer_id'");
  if (mysqli_num_rows($q) > 0) {
    $response["subcategory"] = array();
    while ($row = mysqli_fetch_array($q)) {
      $subcategory = array();
      $subcategory["product_id"] = $row["product_id"];
      $subcategory["product_name"] = $row["product_name"];
      $subcategory["product_sideeffects"] = $row["product_sideeffects"];
      $subcategory["product_use"] = $row["product_use"];
      $subcategory["product_content"] = $row["product_content"];
      $subcategory["product_pregsafety"] = $row["product_pregsafety"];
      $subcategory["product_price"] = $row["product_price"];
      $subcategory["product_photo"] = $row["product_photo"];
      $subcategory["manufacturer_id"] = $row["manufacturer_id"];
      // push single product into final response array
      array_push($response["subcategory"], $subcategory);
    }
    $response["success"] = 1;
    $response["path"] = "http://192.168.1.33/ws/img/subcategory/";
    // echoing JSON response
    echo json_encode($response);
  }
  else {
    $response["success"] = 0;
    $response["message"] = "No Subcategory found";
    // echo no users JSON
    echo json_encode($response);
  }
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

/*add to kart webservice*/
if(isset($_GET['ws_addtokart'])) {
if(isset($_POST) && !empty($_POST) )
{

try {

if (isset($_POST['user_id']) && isset($_POST['product_id']) ) {

      extract($_POST);
      $q=$d->select("addtokart","user_id='$user_id' AND product_id='$product_id'");
      $data=mysqli_fetch_array($q);
      if($data>0) {
        $response["success"] = 0;
        $response["message"] = "this item is already in cart";
        echo json_encode($response);
      } else {

      $last_auto_id=$d->last_auto_id("addtokart");
      $res=mysqli_fetch_array($last_auto_id);
      $kart_id=$res['Auto_increment'];
  
            $m->set_data('user_id',$user_id);
            $m->set_data('product_id',$product_id);
            //$m->set_data('product_name',$product_name);
            //$m->set_data('product_sideeffects',$product_sideeffects);
            //$m->set_data('product_use',$product_use);
            //$m->set_data('product_price',$product_price);
            //$m->set_data('product_photo',$product_photo);
            $a1= array ( 
              'user_id'=> $m->get_data('user_id'),
              'product_id'=> $m->get_data('product_id'),
              //'product_name'=> $m->get_data('product_name'),
              //'product_sideeffects'=> $m->get_data('product_sideeffects'),
              //'product_use'=> $m->get_data('product_use'),
              //'product_price'=> $m->get_data('product_price'),
              //'product_photo'=> $m->get_data('product_photo'),
              );
            $insert=$d->insert('addtokart',$a1);

            if($insert >0) {
                //$_SESSION['msg1']="$email  is already register.";
                $response["kart_id"]=$kart_id;
                $response["user_id"] = $user_id;
                $response["product_id"] = $product_id;
                //$response["product_name"] = $product_name;
                //$response["product_sideeffects"] = $product_sideeffects;
                //$response["product_use"] = $product_use;
                //$response["product_price"] = $product_price;
                //$response["product_photo"] = $product_photo;
                $response["success"] = 1;
                $response["message"] = "successfully added to kart";
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


/*get kart*/

if(isset($_GET['view_add_kart'])) {
if(isset($_POST) && !empty($_POST) )//it can be $_GET doesn't matter
{
  $user_id = $_POST['user_id'];
  $product_id  = $_POST['product_id'];

  $q=$d->select("addtokart","user_id='$user_id'","product_id='$product_id'");
  if (mysqli_num_rows($q) > 0) {
    $response["addtokart"] = array();
    while ($row = mysqli_fetch_array($q)) {
      $addtokart = array();
      $addtokart["product_id"] = $row["product_id"];
     /* $addtokart["product_name"] = $row["product_name"];
      $addtokart["product_price"] = $row["product_price"];
      $addtokart["product_photo"] = $row["product_photo"];*/
      $addtokart["user_id"] = $row["user_id"];
      // push single product into final response array
      array_push($response["addtokart"], $addtokart);
    }
  //  $response["success"] = 1;
   // $response["path"] = "http://192.168.1.33/ws/img/subcategory/";
    // echoing JSON response
    echo json_encode($response);
  }
  else {
    $response["success"] = 0;
    $response["message"] = "No Subcategory found";
    // echo no users JSON
    echo json_encode($response);
  }
}
}

/*over kart*/




if(isset($_GET['display_kart'])) {
if(isset($_POST) && !empty($_POST) )
{
  

try {

if (isset($_POST['user_id'])) {

        extract($_POST);
      $user_id = $_POST['user_id'];
      $q=$d->select1("addtokart","product_id","user_id='$user_id'");
      $data = mysqli_fetch_array($q);
      
      if($data==0) {
         $response["success"] = 0;
        $response["message"] = "you have no item in kart";
        echo json_encode($response);
      } else {
            

      //$last_auto_id=$d->last_auto_id("addtokart");
      //$res=mysqli_fetch_array($last_auto_id);
      //$kart_id=$res['Auto_increment'];
            //$m->set_data('user_id',$user_id);
            //$m->set_data('product_id',$product_id);
          $response["subcategory"] = array();
          foreach ($data as $value) {
            # code...
          

            $q1 = $d->select("subcategory" , " product_id = '$value'");
            //$q1 = mysqli_query($connection,"SELECT * FROM 'subcategory' WHERE 'product_id' IN (. $data .)");

            if(mysqli_num_rows($q1) >0) {
                
                 while ($row = mysqli_fetch_array($q1)) {
                  $subcategory = array();
                  $subcategory["product_id"] = $row["product_id"];
                  $subcategory["product_name"] = $row["product_name"];
                  $subcategory["product_sideeffects"] = $row["product_sideeffects"];
                  $subcategory["product_use"] = $row["product_use"];
                  $subcategory["product_content"] = $row["product_content"];
                  $subcategory["product_pregsafety"] = $row["product_pregsafety"];
                  $subcategory["product_price"] = $row["product_price"];
                  $subcategory["product_photo"] = $row["product_photo"];
                  $subcategory["manufacturer_id"] = $row["manufacturer_id"];
                  // push single product into final response array
                  
                  }
                  array_push($response["subcategory"], $subcategory);
                  $response["success"] = 1;
                  $response["path"] = "http://192.168.1.40/ws/img/subcategory/";
                  // echoing JSON response
                  
            } else {
                $response["success"] = 0;
                $response["message"] = "Oops! An error occurred.";
              
            }

          }
          echo json_encode($response);
           
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








 ?>

