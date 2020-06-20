<?php

class dbconnect
{
    function connect()
    {
        $connection=mysqli_connect("localhost","root","","emedikart");
				return $connection;
    }
}

?>