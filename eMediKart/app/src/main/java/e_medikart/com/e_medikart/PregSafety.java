package e_medikart.com.e_medikart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Pujan on 13-04-2018.
 */

public class PregSafety extends AppCompatActivity{
    private TextView txta, txtb, txtc, txtd, txtx, txtdefinition;
    String textDefinition = "Drugs are categorised based on the risk of reproductive and developmental adverse effects and on risk versus benefit considerations. Drugs in categories D, X, and in some cases C, may pose similar risk, but may be categorised differently on the basis of different risk versus benefit considerations. These categories do not imply an increasing progression of risk from A to X.";
    String textA = "Controlled studies in women fail to demonstrate a risk to the foetus in the 1st  trimester (and there is no evidence of a risk in later trimesters), and the possibility of foetal harm remains remote.";
    String textB = "Either animal-reproduction studies have not demonstrated a foetal risk but there are no controlled studies in pregnant women or animal-reproduction studies have shown an adverse effect (other than a decrease in fertility) that was not confirmed in controlled studies in women in the 1st trimester (and there is no evidence of a risk in later trimesters).";
    String textC = "Either studies in animals have revealed adverse effects on the foetus (teratogenic or embryocidal or other) and there are no controlled studies in women or studies in women and animals are not available. Drugs should be given only if the potential benefit justifies the potential risk to the foetus.";
    String textD = "There is positive evidence of human foetal risk, but the benefits from use in pregnant women may be acceptable despite the risk (e.g., if the drug is needed in a life-threatening situation or for a serious disease for which safer drugs cannot be used or are ineffective).";
    String textX = "Studies in animals or human beings have demonstrated foetal abnormalities or there is evidence of foetal risk based on human experience or both, and the risk of the use of the drug in pregnant women clearly outweighs any possible benefit. The drug is contraindicated in women who are or may become pregnant.";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregsafety);
        txta = (TextView) findViewById(R.id.txt_category_a);
        txtb = (TextView) findViewById(R.id.txt_category_b);
        txtc = (TextView) findViewById(R.id.txt_category_c);
        txtd = (TextView) findViewById(R.id.txt_category_d);
        txtx = (TextView) findViewById(R.id.txt_category_x);
        txtdefinition = (TextView) findViewById(R.id.txt_definition);
        txtdefinition.setText(textDefinition);
        txta.setText(textA);
        txtb.setText(textB);
        txtc.setText(textC);
        txtd.setText(textD);
        txtx.setText(textX);
    }
}
