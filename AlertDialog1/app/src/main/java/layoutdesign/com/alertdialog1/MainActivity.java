package layoutdesign.com.alertdialog1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button alert1, alert2, alert3, alert4, alert5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alert1 = (Button) findViewById(R.id.btn1);
        alert2 = (Button) findViewById(R.id.btn2);
        alert3 = (Button) findViewById(R.id.btn3);
        alert4 = (Button) findViewById(R.id.btn4);
        alert5 = (Button) findViewById(R.id.btn5);
        alert1.setOnClickListener(this);
        alert2.setOnClickListener(this);
        alert3.setOnClickListener(this);
        alert4.setOnClickListener(this);
        alert5.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        AlertDialog.Builder alertbuilder = new AlertDialog.Builder(MainActivity.this);//Here we can take the common object of the alert dialog and use it in different switch case
        switch (view.getId()){
            case R.id.btn1:

                alertbuilder.setTitle("Single Alert");
                alertbuilder.setMessage("do you want to exist??");
                alertbuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "yes is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                alertbuilder.show();

                break;
            case R.id.btn2:

                alertbuilder.setTitle("Double Alert");
                alertbuilder.setMessage("do you want to exist??");
                alertbuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"no is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                alertbuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "yes is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                alertbuilder.show();

                break;
            case R.id.btn3:

                alertbuilder.setTitle("Triple Alert");
                alertbuilder.setMessage("do you want to exist??");
                alertbuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"no is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                alertbuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "yes is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                alertbuilder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "cancel is clicked", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();//Here dialog interface object is used to dismiss because we generally dismiss alert dialog on the click event of any button
                        //on the other hand the alert builder object is used to show the dialog because we can see the dialog on the click event of any button. We should write the show method at the last of writing the code of alertdialog
                    }
                });
                alertbuilder.show();

                break;
            case R.id.btn4:
                //custom toast
                LayoutInflater layoutInflater = getLayoutInflater();
                View layoutview = layoutInflater.inflate(R.layout.layout_3,null);
                ImageView imageView = (ImageView)layoutview.findViewById(R.id.img);
                imageView.setImageResource(R.drawable.heart_icon);
                TextView textView = (TextView)layoutview.findViewById(R.id.tv_toast);
                textView.setText("Helloo.. welcome to android");

                Toast toast = new Toast(MainActivity.this);
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layoutview);
                toast.show();

                break;
            case R.id.btn5:
                //custom alert dialog
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.alertlayout_1);// Here we have used setContentView and not setView because here we are directly accessing the View while in setView we are first creating the View and then using setView method
                Button customalert1 = (Button) dialog.findViewById(R.id.btn6) ;
                Button customalert2 = (Button) dialog.findViewById(R.id.btn7);
                Button customalert3 = (Button) dialog.findViewById(R.id.btn8);
               // alertDialog = alertbuilder1.create();  //here we are using final because we are accessing it in the onclick method and it wont work so we use final method
                customalert1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "yes is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                customalert2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "no is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                customalert3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "cancel is clicked", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();

                break;
        }
    }
}
