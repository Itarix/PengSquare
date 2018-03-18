package cous.q.carremagique;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    int chiffre1_Lig1, chiffre2_Lig1, chiffre3_Lig1;
    int chiffre1_Lig2, chiffre2_Lig2, chiffre3_Lig2;
    int chiffre1_Lig3, chiffre2_Lig3, chiffre3_Lig3;

    int resLig1, resLig2, resLig3, resCol1, resCol2, resCol3;

    int niveau;

    boolean gagner;

    long time;
    int cptAide;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        niveau = intent.getIntExtra("niveauChoisis",9); //24
        Toast toast = Toast.makeText(this, "niveau :" + niveau, Toast.LENGTH_LONG);
        toast.show();

        Chronometer chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.start();

        time = 0;
        cptAide = 0;
        creationOperation();

        remplirOperation(niveau);
    }


    public void remplirOperation(int niveau)
    {

        EditText rep1Lig1 = (EditText) findViewById(R.id.chiffreLig1Col1);
        EditText rep2Lig1 = (EditText) findViewById(R.id.chiffreLig1Col2);
        EditText rep3Lig1 = (EditText) findViewById(R.id.chiffreLig1Col3);

        EditText rep1Lig2 = (EditText) findViewById(R.id.chiffreLig2Col1);
        EditText rep2Lig2 = (EditText) findViewById(R.id.chiffreLig2Col2);
        EditText rep3Lig2 = (EditText) findViewById(R.id.chiffreLig2Col3);

        EditText rep1Lig3 = (EditText) findViewById(R.id.chiffreLig3Col1);
        EditText rep2Lig3 = (EditText) findViewById(R.id.chiffreLig3Col2);
        EditText rep3Lig3 = (EditText) findViewById(R.id.chiffreLig3Col3);

        ArrayList<EditText> alEdT = new ArrayList<>();
        alEdT.add(rep1Lig1);
        alEdT.add(rep1Lig2);
        alEdT.add(rep1Lig3);

        alEdT.add(rep2Lig1);
        alEdT.add(rep2Lig2);
        alEdT.add(rep2Lig3);

        alEdT.add(rep3Lig1);
        alEdT.add(rep3Lig2);
        alEdT.add(rep3Lig3);

        ArrayList<Integer> alChRes = new ArrayList<>();
        alChRes.add(chiffre1_Lig1);
        alChRes.add(chiffre1_Lig2);
        alChRes.add(chiffre1_Lig3);

        alChRes.add(chiffre2_Lig1);
        alChRes.add(chiffre2_Lig2);
        alChRes.add(chiffre2_Lig3);

        alChRes.add(chiffre3_Lig1);
        alChRes.add(chiffre3_Lig2);
        alChRes.add(chiffre3_Lig3);


        boolean[] tabBoolean = new boolean[] {false, false, false, false, false, false, false, false, false };

        int i = 9;
        while ( i > niveau )
        {
            boolean bOk = false;
            while ( !bOk )
            {
                int ch = (int) (Math.random() * 9) + 1;
                if (!tabBoolean[ch - 1]) {
                    tabBoolean[ch - 1] = true;
                    bOk = true;
                    alEdT.get(ch - 1).setText( "" + alChRes.get(ch - 1));
                }

            }
            i--;
        }


    }

    public void choixNiveau ( View v )
    {
        Intent intent = new Intent( MainActivity.this, ChoixNiveau.class );
        startActivity(intent);
    }

    public void calculerScore ()
    {
        int score = 100;
        int scoreTotal = 100;

        scoreTotal = scoreTotal * niveau;
        score = score * niveau;

        score = score / (cptAide + 1); // on evite le divisé par 0 et si il utilise aucune aide, on divise par 1... donc ne change pas score

        Chronometer chrono = (Chronometer) findViewById(R.id.chrono);
        int temps = (int) (SystemClock.elapsedRealtime() - chrono.getBase())/1000;

        if ( niveau >= 1 && niveau <= 3 )
        {
            if ( temps > 5 )
            {
                if (temps <= 15) score = (int) (score / ( 1+ (0.025 * temps)));
                else score = (int) (score / (0.1 * temps));
            }
        }
        else if ( niveau >= 4 && niveau <= 6 )
        {
            if ( temps > 10 )
            {
                if (temps <= 20) score = (int) (score / (1 + (0.025 * temps)));
                else score = (int) (score / (0.10 * temps));
            }
        }
        else if ( niveau >= 7 && niveau <= 8)
        {
            if ( temps > 15 )
            {
                if ( temps <= 40) score = (int) (score / (1 + ( 0.025 * temps)));
                else  score = (int) (score / ( 0.1 * temps));
            }
        }
        else
        {
            if ( temps > 20 )
            {
                if (temps <= 60) score = (int) (score / ( 1+ (0.025 * temps)));
                else score = (int) (score / (0.1 * temps));
            }
        }
        TextView scoreText = (TextView) findViewById(R.id.scoreText);
        scoreText.setText( "Votre Score : " + score + " / " + scoreTotal);

    }

    public void creationOperation()
    {
        Chronometer chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.stop();
        time = 0;
        chrono.setBase(SystemClock.elapsedRealtime() );
        chrono.start();

        EditText rep1Lig1 = (EditText) findViewById(R.id.chiffreLig1Col1);
        EditText rep2Lig1 = (EditText) findViewById(R.id.chiffreLig1Col2);
        EditText rep3Lig1 = (EditText) findViewById(R.id.chiffreLig1Col3);

        EditText rep1Lig2 = (EditText) findViewById(R.id.chiffreLig2Col1);
        EditText rep2Lig2 = (EditText) findViewById(R.id.chiffreLig2Col2);
        EditText rep3Lig2 = (EditText) findViewById(R.id.chiffreLig2Col3);

        EditText rep1Lig3 = (EditText) findViewById(R.id.chiffreLig3Col1);
        EditText rep2Lig3 = (EditText) findViewById(R.id.chiffreLig3Col2);
        EditText rep3Lig3 = (EditText) findViewById(R.id.chiffreLig3Col3);

        ArrayList<EditText> alEdT = new ArrayList<>();
        alEdT.add(rep1Lig1);
        alEdT.add(rep1Lig2);
        alEdT.add(rep1Lig3);

        alEdT.add(rep2Lig1);
        alEdT.add(rep2Lig2);
        alEdT.add(rep2Lig3);

        alEdT.add(rep3Lig1);
        alEdT.add(rep3Lig2);
        alEdT.add(rep3Lig3);

        for ( EditText ed : alEdT)
        {
            ed.setText("");
        }

        gagner = false;

        TextView ligne1 = (TextView) findViewById(R.id.resLig1);
        TextView ligne2 = (TextView) findViewById(R.id.resLig2);
        TextView ligne3 = (TextView) findViewById(R.id.resLig3);

        TextView colonne1 = (TextView) findViewById(R.id.resCol1);
        TextView colonne2 = (TextView) findViewById(R.id.resCol2);
        TextView colonne3 = (TextView) findViewById(R.id.resCol3);

        chiffre1_Lig1 = (int) (Math.random()*9)+1;
        chiffre2_Lig1 = (int) (Math.random()*9)+1;
        chiffre3_Lig1 = (int) (Math.random()*9)+1;

        chiffre1_Lig2 = (int) (Math.random()*9)+1;
        chiffre2_Lig2 = (int) (Math.random()*9)+1;
        chiffre3_Lig2 = (int) (Math.random()*9)+1;

        chiffre1_Lig3 = (int) (Math.random()*9)+1;
        chiffre2_Lig3 = (int) (Math.random()*9)+1;
        chiffre3_Lig3 = (int) (Math.random()*9)+1;

        resLig1 = chiffre1_Lig1 + chiffre2_Lig1 + chiffre3_Lig1;
        resLig2 = chiffre1_Lig2 + chiffre2_Lig2 + chiffre3_Lig2;
        resLig3 = chiffre1_Lig3 + chiffre2_Lig3 + chiffre3_Lig3;

        resCol1 = chiffre1_Lig1 + chiffre1_Lig2 + chiffre1_Lig3;
        resCol2 = chiffre2_Lig1 + chiffre2_Lig2 + chiffre2_Lig3;
        resCol3 = chiffre3_Lig1 + chiffre3_Lig2 + chiffre3_Lig3;

        ligne1.setText("" + resLig1);
        ligne2.setText("" + resLig2);
        ligne3.setText("" + resLig3);

        colonne1.setText("" + resCol1);
        colonne2.setText("" + resCol2);
        colonne3.setText("" + resCol3);
    }

    public void aide( View v )
    {
        EditText rep1Lig1 = (EditText) findViewById(R.id.chiffreLig1Col1);
        EditText rep2Lig1 = (EditText) findViewById(R.id.chiffreLig1Col2);
        EditText rep3Lig1 = (EditText) findViewById(R.id.chiffreLig1Col3);

        EditText rep1Lig2 = (EditText) findViewById(R.id.chiffreLig2Col1);
        EditText rep2Lig2 = (EditText) findViewById(R.id.chiffreLig2Col2);
        EditText rep3Lig2 = (EditText) findViewById(R.id.chiffreLig2Col3);

        EditText rep1Lig3 = (EditText) findViewById(R.id.chiffreLig3Col1);
        EditText rep2Lig3 = (EditText) findViewById(R.id.chiffreLig3Col2);
        EditText rep3Lig3 = (EditText) findViewById(R.id.chiffreLig3Col3);

        ArrayList<EditText> alEdT = new ArrayList<>();
        alEdT.add(rep1Lig1);
        alEdT.add(rep1Lig2);
        alEdT.add(rep1Lig3);

        alEdT.add(rep2Lig1);
        alEdT.add(rep2Lig2);
        alEdT.add(rep2Lig3);

        alEdT.add(rep3Lig1);
        alEdT.add(rep3Lig2);
        alEdT.add(rep3Lig3);


        ArrayList<Integer> alChRes = new ArrayList<>();
        alChRes.add(chiffre1_Lig1);
        alChRes.add(chiffre1_Lig2);
        alChRes.add(chiffre1_Lig3);

        alChRes.add(chiffre2_Lig1);
        alChRes.add(chiffre2_Lig2);
        alChRes.add(chiffre2_Lig3);

        alChRes.add(chiffre3_Lig1);
        alChRes.add(chiffre3_Lig2);
        alChRes.add(chiffre3_Lig3);


        boolean bOk = false;

        while ( !bOk )
        {
            int choix = (int) (Math.random() * 9) + 1;
            if ( alEdT.get(choix-1).getText().toString().equals("") )
            {
                alEdT.get(choix-1).setText("" + alChRes.get(choix-1));
                alEdT.get(choix-1).setTextColor(getColor(R.color.colorRed ));
                bOk = true;
                cptAide ++;
            }

        }
    }

    public void validerReponse( View v )
    {

        EditText rep1Lig1 = (EditText) findViewById(R.id.chiffreLig1Col1);
        EditText rep2Lig1 = (EditText) findViewById(R.id.chiffreLig1Col2);
        EditText rep3Lig1 = (EditText) findViewById(R.id.chiffreLig1Col3);

        EditText rep1Lig2 = (EditText) findViewById(R.id.chiffreLig2Col1);
        EditText rep2Lig2 = (EditText) findViewById(R.id.chiffreLig2Col2);
        EditText rep3Lig2 = (EditText) findViewById(R.id.chiffreLig2Col3);

        EditText rep1Lig3 = (EditText) findViewById(R.id.chiffreLig3Col1);
        EditText rep2Lig3 = (EditText) findViewById(R.id.chiffreLig3Col2);
        EditText rep3Lig3 = (EditText) findViewById(R.id.chiffreLig3Col3);

        ArrayList<EditText> alEdT = new ArrayList<>();
        alEdT.add(rep1Lig1);
        alEdT.add(rep1Lig2);
        alEdT.add(rep1Lig3);

        alEdT.add(rep2Lig1);
        alEdT.add(rep2Lig2);
        alEdT.add(rep2Lig3);

        alEdT.add(rep3Lig1);
        alEdT.add(rep3Lig2);
        alEdT.add(rep3Lig3);

        boolean carreRemplis = false;
        for ( EditText ed : alEdT)
        {
            if ( !ed.getText().toString().equals("") )
            {
                carreRemplis = true;
            }
            else
            {
                carreRemplis = false;
                break;
            }
        }

        if ( carreRemplis)
        {
            if ( Integer.parseInt(rep1Lig1.getText().toString()) + Integer.parseInt(rep2Lig1.getText().toString()) + Integer.parseInt(rep3Lig1.getText().toString()) == resLig1 &&
                 Integer.parseInt(rep1Lig2.getText().toString()) + Integer.parseInt(rep2Lig2.getText().toString()) + Integer.parseInt(rep3Lig2.getText().toString()) == resLig2 &&
                 Integer.parseInt(rep1Lig3.getText().toString()) + Integer.parseInt(rep2Lig3.getText().toString()) + Integer.parseInt(rep3Lig3.getText().toString()) == resLig3 &&
                 Integer.parseInt(rep1Lig1.getText().toString()) + Integer.parseInt(rep1Lig2.getText().toString()) + Integer.parseInt(rep1Lig3.getText().toString()) == resCol1 &&
                 Integer.parseInt(rep2Lig1.getText().toString()) + Integer.parseInt(rep2Lig2.getText().toString()) + Integer.parseInt(rep2Lig3.getText().toString()) == resCol2 &&
                 Integer.parseInt(rep3Lig1.getText().toString()) + Integer.parseInt(rep3Lig2.getText().toString()) + Integer.parseInt(rep3Lig3.getText().toString()) == resCol3) {
                gagner = true;}

            if ( gagner )
            {
                messageGagner();
                Chronometer chrono = (Chronometer) findViewById(R.id.chrono);
                chrono.stop();
                calculerScore();
            }
            else messageErreur();

        }
        else messageCarrePasRemplis();


    }

    public void messageCarrePasRemplis()
    {
        Toast toast = Toast.makeText( this, "Le carrée n'est pas remplis!", Toast.LENGTH_LONG);
        toast.show();
    }
    
    public void messageGagner()
    {
        Toast toast = Toast.makeText(this, "Tu as gagné! Bravo", Toast.LENGTH_LONG);
        toast.show();
    }

    public void messageErreur()
    {
        Toast toast = Toast.makeText( this, "Dommage... Mais tu va réussir en persévérant!", Toast.LENGTH_LONG);
        toast.show();
    }

    public void regleJeux ( View v )
    {
        String url = "https://fr.wikipedia.org/wiki/Carr%C3%A9_magique_(math%C3%A9matiques)";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void recommencer ( View v )
    {
        creationOperation();
    }

    public void quitterApp ( View v )
    {
        System.exit(0);
    }

    public void onSaveInstanceState( Bundle bagOfData)
    {
        bagOfData.putInt( "lig1Col1", chiffre1_Lig1 );
        bagOfData.putInt( "lig1Col2", chiffre2_Lig1 );
        bagOfData.putInt( "lig1Col3", chiffre3_Lig1 );

        bagOfData.putInt( "lig2Col1", chiffre1_Lig2 );
        bagOfData.putInt( "lig2Col2", chiffre2_Lig2 );
        bagOfData.putInt( "lig2Col3", chiffre3_Lig2 );

        bagOfData.putInt( "lig3Col1", chiffre1_Lig3 );
        bagOfData.putInt( "lig3Col2", chiffre2_Lig3 );
        bagOfData.putInt( "lig3Col3", chiffre3_Lig3 );

        bagOfData.putInt( "niveau", niveau);
        bagOfData.putInt( "cptAide", cptAide);

        Chronometer chrono = (Chronometer) findViewById(R.id.chrono);

        chrono.stop();
        time = SystemClock.elapsedRealtime() -  chrono.getBase();

        bagOfData.putLong("timeChrono", time);

        super.onSaveInstanceState(bagOfData);
    }

    public void onRestoreInstanceState( Bundle bagOfData)
    {
        super.onRestoreInstanceState(bagOfData);

        time = bagOfData.getLong("timeChrono");
        cptAide = bagOfData.getInt("cptAide");

        Chronometer chrono = (Chronometer) findViewById(R.id.chrono);
        chrono.setBase(SystemClock.elapsedRealtime() - time);
        chrono.start();

        EditText rep1Lig1 = (EditText) findViewById(R.id.chiffreLig1Col1);
        EditText rep2Lig1 = (EditText) findViewById(R.id.chiffreLig1Col2);
        EditText rep3Lig1 = (EditText) findViewById(R.id.chiffreLig1Col3);

        EditText rep1Lig2 = (EditText) findViewById(R.id.chiffreLig2Col1);
        EditText rep2Lig2 = (EditText) findViewById(R.id.chiffreLig2Col2);
        EditText rep3Lig2 = (EditText) findViewById(R.id.chiffreLig2Col3);

        EditText rep1Lig3 = (EditText) findViewById(R.id.chiffreLig3Col1);
        EditText rep2Lig3 = (EditText) findViewById(R.id.chiffreLig3Col2);
        EditText rep3Lig3 = (EditText) findViewById(R.id.chiffreLig3Col3);


        chiffre1_Lig1 = bagOfData.getInt("lig1Col1");
        chiffre2_Lig1 = bagOfData.getInt("lig1Col2");
        chiffre3_Lig1 = bagOfData.getInt("lig1Col3");

        chiffre1_Lig2 = bagOfData.getInt("lig2Col1");
        chiffre2_Lig2 = bagOfData.getInt("lig2Col2");
        chiffre3_Lig2 = bagOfData.getInt("lig2Col3");

        chiffre1_Lig3 = bagOfData.getInt("lig3Col1");
        chiffre2_Lig3 = bagOfData.getInt("lig3Col2");
        chiffre3_Lig3 = bagOfData.getInt("lig3Col3");

        niveau = bagOfData.getInt("niveau");


        TextView ligne1 = (TextView) findViewById(R.id.resLig1);
        TextView ligne2 = (TextView) findViewById(R.id.resLig2);
        TextView ligne3 = (TextView) findViewById(R.id.resLig3);

        TextView colonne1 = (TextView) findViewById(R.id.resCol1);
        TextView colonne2 = (TextView) findViewById(R.id.resCol2);
        TextView colonne3 = (TextView) findViewById(R.id.resCol3);


        resLig1 = chiffre1_Lig1 + chiffre2_Lig1 + chiffre3_Lig1;
        resLig2 = chiffre1_Lig2 + chiffre2_Lig2 + chiffre3_Lig2;
        resLig3 = chiffre1_Lig3 + chiffre2_Lig3 + chiffre3_Lig3;

        resCol1 = chiffre1_Lig1 + chiffre1_Lig2 + chiffre1_Lig3;
        resCol2 = chiffre2_Lig1 + chiffre2_Lig2 + chiffre2_Lig3;
        resCol3 = chiffre3_Lig1 + chiffre3_Lig2 + chiffre3_Lig3;


        ligne1.setText("" + resLig1);
        ligne2.setText("" + resLig2);
        ligne3.setText("" + resLig3);

        colonne1.setText("" + resCol1);
        colonne2.setText("" + resCol2);
        colonne3.setText("" + resCol3);


    }

}
