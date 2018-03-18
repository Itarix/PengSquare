package cous.q.carremagique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ChoixNiveau extends AppCompatActivity
{

    int niveauChoix;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_niveau);
        creerListeNiveau();
    }

    public void creerListeNiveau ()
    {
        Spinner spin = (Spinner) findViewById(R.id.niveau);

        String [] tab = new String [9];

        for ( int i =0; i < 9; i ++)
        {
            tab[i] = String.valueOf(i+1);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChoixNiveau.this, android.R.layout.simple_spinner_dropdown_item, tab);
        spin.setAdapter(adapter);
    }

    public void creerNiveau( View v )
    {
        Spinner spin = (Spinner) findViewById(R.id.niveau);
        niveauChoix = Integer.parseInt( (String) spin.getSelectedItem() );

        Intent intent = new Intent(ChoixNiveau.this, MainActivity.class);
        intent.putExtra("niveauChoisis",niveauChoix);
        startActivity(intent);
    }
}
